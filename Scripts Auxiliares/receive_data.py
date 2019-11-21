#!/usr/bin/env python

import pika
import psycopg2
import json
from datetime import datetime


# Change for postgres domain
def get_db_conn():
    conn = psycopg2.connect(user="sysadmin",
                            password="pynative@#29",
                            host="127.0.0.1",
                            port="5432",
                            database="postgres_db")

    return conn


db_conn = get_db_conn()


def write_to_db(temperature, humidity, co2, date, sensor_id):
    global db_conn

    sql = """INSERT INTO weather(temperature, pressure, humidity, co2, date_time, sensor_id)
             VALUES(%s, %s, %s, %s, %s, %s);"""

    try:
        # create a new cursor
        cur = db_conn.cursor()
        # execute the INSERT statement
        cur.execute(sql, [temperature, humidity, co2, date, sensor_id])
        # commit the changes to the database
        db_conn.commit()
        # close communication with the database
        cur.close()
        return True
    except (Exception, psycopg2.DatabaseError) as error:
        return False


def insert_weather_reading(weather_reading):
    """ insert a new weather reading into the weather table """
    temperature = weather_reading.get('Temperature')
    humidity = weather_reading.get('Humidity')
    co2 = weather_reading.get('CO2')
    sensor_id = weather_reading.get('Sensor_id')

    date = weather_reading.get('Date')
    date = datetime.strptime(date, "%Y-%m-%d %H:%M:%S.%f")

    global db_conn
    while not write_to_db(temperature, humidity, co2, date, sensor_id):
        try:
            db_conn.close()
        except Exception:
            pass

        db_conn = get_db_conn()


def callback(ch, method, properties, body):
    weather_reading = json.loads(body)
    insert_weather_reading(weather_reading)
    ch.basic_ack(delivery_tag=method.delivery_tag)


# https://pika.readthedocs.io/en/stable/examples/blocking_consume_recover_multiple_hosts.html

rabbit_domain = 'localhost'
while True:
    try:
        connection = pika.BlockingConnection(pika.ConnectionParameters(host=rabbit_domain))
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
        continue
        # Do not recover on channel errors
    except pika.exceptions.AMQPChannelError as err:
        print("Caught a channel error: {}, stopping...".format(err))
        break
        # Recover on all other connection errors
    except pika.exceptions.AMQPConnectionError:
        print("Connection was closed, retrying...")
        continue
