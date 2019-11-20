#!/usr/bin/env python

import time
import pika
import json
from datetime import datetime
import random


def get_rabbit_conn_and_channel(host, queue='task_queue'):
    connection_ = pika.BlockingConnection(
        pika.ConnectionParameters(host=host))
    channel_ = connection_.channel()

    channel_.queue_declare(queue=queue, durable=True)
    return connection_, channel_


def close_rabbit_connection(connection_):
    connection_.close()


def send_message(channel_, message, queue='task_queue'):
    channel_.queue_declare(queue='task_queue')

    channel_.basic_publish(exchange='', routing_key=queue, body=json.dumps(message),
                           properties=pika.BasicProperties(delivery_mode=2))  # make message persistent


# This will be the real sensor
sensor_id = 1

# Fake data that is 25% higher
factor_sensor_2 = 1.25

# Fake data that is 25% lower
factor_sensor_3 = 0.75

# Rabbit domain will need to be changed after setup
rabbit_domain = "localhost"
connection, channel = get_rabbit_conn_and_channel(rabbit_domain)


try:
    while True:
            temperature = random.randrange(-10, 20, 1)
            pressure = random.randrange(500, 1500, 1)
            humidity = random.randrange(0, 100, 1)
            co2 = random.randrange(0, 1000, 1)

            date = datetime.now()

            weather_reading_1 = {"Temperature": temperature,
                                 "Pressure": pressure,
                                 "Humidity": humidity,
                                 "CO2": co2,
                                 "Date": str(date),
                                 "Sensor_id": sensor_id}

            weather_reading_2 = {"Temperature": temperature * factor_sensor_2,
                                 "Pressure": pressure * factor_sensor_2,
                                 "Humidity": humidity * factor_sensor_2,
                                 "CO2": co2 * factor_sensor_2,
                                 "Date": str(date),
                                 "Sensor_id": 2}

            weather_reading_3 = {"Temperature": temperature * factor_sensor_3,
                                 "Pressure": pressure * factor_sensor_3,
                                 "Humidity": humidity * factor_sensor_3,
                                 "CO2": co2 * factor_sensor_3,
                                 "Date": str(date),
                                 "Sensor_id": 3}

            if not connection.is_open:
                connection, channel = get_rabbit_conn_and_channel(rabbit_domain)

            send_message(channel, weather_reading_1)
            send_message(channel, weather_reading_2)
            send_message(channel, weather_reading_3)

            # Offset between data
            time.sleep(1)

except KeyboardInterrupt:
    pass
