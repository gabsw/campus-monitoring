#!/usr/bin/env python

import bme680
import time
import pika
import json
from datetime import datetime


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


try:
    sensor = bme680.BME680(bme680.I2C_ADDR_PRIMARY)
except IOError:
    sensor = bme680.BME680(bme680.I2C_ADDR_SECONDARY)

# These oversampling settings can be tweaked to
# change the balance between accuracy and noise in
# the data.

sensor.set_humidity_oversample(bme680.OS_2X)
sensor.set_pressure_oversample(bme680.OS_4X)
sensor.set_temperature_oversample(bme680.OS_8X)
sensor.set_filter(bme680.FILTER_SIZE_3)
sensor.set_gas_status(bme680.ENABLE_GAS_MEAS)

sensor.set_gas_heater_temperature(320)
sensor.set_gas_heater_duration(150)
sensor.select_gas_heater_profile(0)

# Up to 10 heater profiles can be configured, each
# with their own temperature and duration.

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
        if sensor.get_sensor_data():

            temperature = sensor.data.temperature
            pressure = sensor.data.pressure
            humidity = sensor.data.humidity
            date = datetime.now()
            co2 = None

            if sensor.data.heat_stable:
                co2 = sensor.data.gas_resistance

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
