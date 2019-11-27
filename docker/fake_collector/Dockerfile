FROM python:3

RUN pip install --no-cache-dir pika
COPY . .

CMD [ "python", "./generate_fake_data.py" ]