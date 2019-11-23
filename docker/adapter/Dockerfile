FROM python:3

# install postgres client and psycopg2
RUN pip install --no-cache-dir pika psycopg2

COPY . .

CMD [ "python", "./adapter.py" ]