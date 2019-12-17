#!/usr/bin/env python

import json
import os

import pika
from flask import Flask, request


def get_env_var(var_name):
    return os.environ[var_name]


def get_rabbit_conn_and_channel(host, username, password, queue='task_queue'):
    credentials = pika.PlainCredentials(username, password)
    parameters = pika.ConnectionParameters(host, 5672, '/', credentials)

    connection_ = pika.BlockingConnection(parameters)
    channel_ = connection_.channel()

    channel_.queue_declare(queue=queue, durable=True)

    return connection_, channel_


def send_message(channel_, message, queue='task_queue'):
    # channel_.queue_declare(queue='task_queue')

    channel_.basic_publish(exchange='', routing_key=queue, body=json.dumps(message),
                           properties=pika.BasicProperties(delivery_mode=2))  # make message persistent


rabbit_host = get_env_var('RABBIT_HOST')
rabbit_username = get_env_var('RABBIT_USERNAME')
rabbit_password = get_env_var('RABBIT_PASSWORD')

connection, channel = get_rabbit_conn_and_channel(rabbit_host, rabbit_username, rabbit_password)

app = Flask(__name__)


@app.route('/sensor', methods=['POST'])
def sensor():
    global connection, channel
    if not connection.is_open:
        connection, channel = get_rabbit_conn_and_channel(rabbit_host, rabbit_username, rabbit_password)

    message = request.json
    send_message(channel, message)

    return ('', 200)


if __name__ == '__main__':
    app.run(host='0.0.0.0')
