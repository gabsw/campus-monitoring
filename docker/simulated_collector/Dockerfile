FROM python:3

RUN pip install --no-cache-dir pika
COPY . .

CMD [ "python", "./simulate_data.py" ]