#!/usr/bin/env python

import os
import time
import json
from datetime import datetime
import random

import pika

def random_jitter(value, minimum, maximum, step=0.1):
    jitter = random.random() * step * 2 - step  # the jitter is in [-step, +step]
    new_value = value + jitter
    if new_value < minimum:
        return minimum
    elif new_value > maximum:
        return maximum
    else:
        return new_value

def get_rabbit_conn_and_channel(host, username, password, queue='task_queue'):
    credentials = pika.PlainCredentials(username, password)
    parameters = pika.ConnectionParameters(host, 5672, '/', credentials)

    connection_ = pika.BlockingConnection(parameters)
    channel_ = connection_.channel()

    channel_.queue_declare(queue=queue, durable=True)
    return connection_, channel_


def close_rabbit_connection(connection_):
    connection_.close()


def send_message(channel_, message, queue='task_queue'):
    #channel_.queue_declare(queue='task_queue')

    channel_.basic_publish(exchange='', routing_key=queue, body=json.dumps(message),
                           properties=pika.BasicProperties(delivery_mode=2))  # make message persistent

def get_env_var(var_name):
    return os.environ[var_name]

sensor_ids_str = get_env_var('SENSOR_IDS')
sensors = [{'Sensor_id': int(x), 'Temperature': 20.0, 'Humidity': 50.0, 'CO2': 600.0} for x in sensor_ids_str.split(',')]

rabbit_host = get_env_var('RABBIT_HOST')
rabbit_username = get_env_var('RABBIT_USERNAME')
rabbit_password = get_env_var('RABBIT_PASSWORD')

# waiting for rabbit container to go up
time.sleep(30)

connection, channel = get_rabbit_conn_and_channel(rabbit_host, rabbit_username, rabbit_password)

min_temp, max_temp = -10, 30
min_humidity, max_humidity = 0, 100
min_co2, max_co2 = 0, 1200

try:
    while True:
            for sensor in sensors:
                temperature = sensor['Temperature']
                humidity = sensor['Humidity']
                co2 = sensor['CO2']

                sensor['Temperature'] = random_jitter(temperature, min_temp, max_temp, step=0.05)
                sensor['Humidity'] = random_jitter(humidity, min_humidity, max_humidity)
                sensor['CO2'] = random_jitter(co2, min_co2, max_co2, step=1.0)
                sensor['Date'] = str(datetime.now())


                # just let the script die if there is no connection, it will just be restarted
                if not connection.is_open:
                    print('Connection is closed. Terminating collector.')
                    break

                send_message(channel, sensor)

            # Offset between data
            time.sleep(60)
except KeyboardInterrupt:
    pass
