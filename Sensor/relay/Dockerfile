FROM python:3

RUN pip install --no-cache-dir pika flask
COPY . .

CMD [ "python", "./simulate_data.py" ]