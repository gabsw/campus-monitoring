#!/usr/bin/env python3

import bme680
import time
import pika
import json
from datetime import datetime
from subprocess import PIPE, Popen


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
    print(message)


def get_cpu_temperature():
    process = Popen(['vcgencmd', 'measure_temp'], stdout=PIPE)
    output, _error = process.communicate()
    return float(output[output.index(b'=') + 1:output.rindex(b"'")])


try:
    sensor = bme680.BME680(bme680.I2C_ADDR_PRIMARY)
except IOError:
    sensor = bme680.BME680(bme680.I2C_ADDR_SECONDARY)

# These oversampling settings can be tweaked to
# change the balance between accuracy and noise in
# the data.

sensor.set_humidity_oversample(bme680.OS_2X)
sensor.set_temperature_oversample(bme680.OS_8X)
sensor.set_filter(bme680.FILTER_SIZE_3)
sensor.set_gas_status(bme680.ENABLE_GAS_MEAS)

sensor.set_gas_heater_temperature(320)
sensor.set_gas_heater_duration(150)
sensor.select_gas_heater_profile(0)

# Up to 10 heater profiles can be configured, each
# with their own temperature and duration.

# This will be the real sensor
sensor_id = 4

# Rabbit domain will need to be changed after setup
rabbit_domain = "deti-engsoft-12.ua.pt"
#rabbit_domain = "192.168.1.216"
connection, channel = get_rabbit_conn_and_channel(rabbit_domain, "ies_grupo33", "rabbitmq_grupo33")

factor = 2.0  # Smaller numbers adjust temp down, vice versa
smooth_size = 10  # Dampens jitter due to rapid CPU temp changes
cpu_temps = [0]

try:
    while True:
        try:
            if sensor.get_sensor_data():

                temperature = sensor.data.temperature
                humidity = sensor.data.humidity
                date = datetime.now()
                co2 = None

                if sensor.data.heat_stable:
                    co2 = sensor.data.gas_resistance / 250

                cpu_temp = get_cpu_temperature()
                cpu_temps.append(cpu_temp)
                if len(cpu_temps) > smooth_size:
                    cpu_temps = cpu_temps[1:]

                smoothed_cpu_temp = sum(cpu_temps) / float(len(cpu_temps))
                temperature = temperature - ((smoothed_cpu_temp - temperature) / factor)

                weather_reading = {"Temperature": temperature,
                                   "Humidity": humidity,
                                   "CO2": co2,
                                   "Date": str(date),
                                   "Sensor_id": sensor_id}

                if connection is None or not connection.is_open:
                    connection, channel = get_rabbit_conn_and_channel(rabbit_domain)

                send_message(channel, weather_reading)

                # Offset between data
                time.sleep(60)
        except Exception as ex:
            print(ex)
            time.sleep(300)
except KeyboardInterrupt:
    pass