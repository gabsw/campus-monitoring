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


# This will be the real sensor
# sensor_id = 1

# Fake data that is 25% higher
factor_sensor_2 = 1.25

# Fake data that is 25% lower
factor_sensor_3 = 0.75

rabbit_host = get_env_var('RABBIT_HOST')
rabbit_username = get_env_var('RABBIT_USERNAME')
rabbit_password = get_env_var('RABBIT_PASSWORD')

# waiting for rabbit container to go up
time.sleep(30)

connection, channel = get_rabbit_conn_and_channel(rabbit_host, rabbit_username, rabbit_password)

min_temp, max_temp, temperature = -10, 30, 20
min_humidity, max_humidity, humidity = 0, 100, 50
min_co2, max_co2, co2 = 0, 1200, 500

try:
    while True:
            temperature = random_jitter(temperature, min_temp, max_temp, step=0.05)
            humidity = random_jitter(humidity, min_humidity, max_humidity)
            co2 = random_jitter(co2, min_co2, max_co2)

            date = datetime.now()

            weather_reading_1 = {"Temperature": temperature,
                                 "Humidity": humidity,
                                 "CO2": co2,
                                 "Date": str(date),
                                 "Sensor_id": 1}

            weather_reading_2 = {"Temperature": temperature * factor_sensor_2,
                                 "Humidity": humidity * factor_sensor_2,
                                 "CO2": co2 * factor_sensor_2,
                                 "Date": str(date),
                                 "Sensor_id": 2}

            weather_reading_3 = {"Temperature": temperature * factor_sensor_3,
                                 "Humidity": humidity * factor_sensor_3,
                                 "CO2": co2 * factor_sensor_3,
                                 "Date": str(date),
                                 "Sensor_id": 3}

            # just let the script die if there is no connection, it will just be restarted
            if not connection.is_open:
                print('Connection is closed. Terminating collector.')
                break

            send_message(channel, weather_reading_1)
            send_message(channel, weather_reading_2)
            send_message(channel, weather_reading_3)

            # Offset between data
            time.sleep(1)

except KeyboardInterrupt:
    pass
