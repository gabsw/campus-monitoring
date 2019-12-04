#!/usr/bin/env python

import os
import json
from datetime import datetime
import time

import pika
import psycopg2


def get_env_var(var_name):
    return os.environ[var_name]

# Change for postgres domain
def get_db_conn():
    conn = psycopg2.connect(user=get_env_var('DATABASE_USERNAME'),
                            password=get_env_var('DATABASE_PASSWORD'),
                            host=get_env_var('DATABASE_HOST'),
                            port=get_env_var('DATABASE_PORT'),
                            database=get_env_var('DATABASE_NAME'))

    return conn


db_conn = get_db_conn()


def write_to_db(temperature, humidity, co2, date, sensor_id):
    global db_conn

    sql = """INSERT INTO campus_monitoring.WEATHER_READING(temperature, humidity, co2, date_time, sensor_id)
             VALUES(%s, %s, %s, %s, %s)
             ON CONFLICT (date_time, sensor_id) DO NOTHING;"""

    # create a new cursor
    cur = db_conn.cursor()
    # execute the INSERT statement
    cur.execute(sql, [temperature, humidity, co2, date, sensor_id])
    # commit the changes to the database
    db_conn.commit()
    # close communication with the database
    cur.close()

def filter_reading(temperature, humidity, co2):
    if temperature < -100 or temperature > 100:
        return True
    if humidity > 100 or humidity < 0:
        return True
    if co2 < 0 or co2 > 9000:
        return True
    return False

def insert_weather_reading(weather_reading):
    """ insert a new weather reading into the weather table """
    temperature = weather_reading.get('Temperature')
    humidity = weather_reading.get('Humidity')
    co2 = weather_reading.get('CO2')
    sensor_id = weather_reading.get('Sensor_id')

    if filter_reading(temperature, humidity, co2):
        print("Filtering out sensor reading:", weather_reading)
        return

    date = weather_reading.get('Date')
    date = datetime.strptime(date, "%Y-%m-%d %H:%M:%S.%f")

    write_to_db(temperature, humidity, co2, date, sensor_id)
    


def callback(ch, method, properties, body):
    weather_reading = json.loads(body)
    insert_weather_reading(weather_reading)
    ch.basic_ack(delivery_tag=method.delivery_tag)


# https://pika.readthedocs.io/en/stable/examples/blocking_consume_recover_multiple_hosts.html

rabbit_host = get_env_var('RABBIT_HOST')
rabbit_username = get_env_var('RABBIT_USERNAME')
rabbit_password = get_env_var('RABBIT_PASSWORD')

while True:
    try:
        credentials = pika.PlainCredentials(rabbit_username, rabbit_password)
        parameters = pika.ConnectionParameters(rabbit_host, 5672, '/', credentials)
        connection = pika.BlockingConnection(parameters)

        channel = connection.channel()
        channel.queue_declare(queue='task_queue', durable=True)  # Avoid losing messages during crashes
        channel.basic_qos(prefetch_count=1)  # Do not give more than one message to a worker at a time
        channel.basic_consume(queue='task_queue', on_message_callback=callback)

        try:
            channel.start_consuming()
        except KeyboardInterrupt:
            channel.stop_consuming()
            connection.close()
            break
    except pika.exceptions.ConnectionClosedByBroker:
        # Uncomment this to make the example not attempt recovery
        # from server-initiated connection closure, including
        # when the node is stopped cleanly
        #
        # break
        time.sleep(5)
        continue
        # Do not recover on channel errors
    except pika.exceptions.AMQPChannelError as ex:
        print("Caught a channel error: {}, stopping.".format(ex))
        break
        # Recover on all other connection errors
    except pika.exceptions.AMQPConnectionError:
        print("Connection was closed, retrying.")
        time.sleep(5)
    except (Exception, psycopg2.DatabaseError) as error:
        time.sleep(5)
        print("Database error: ", error)
        print("Rethrowing error.")
        break
