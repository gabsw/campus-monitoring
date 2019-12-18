To build the docker image:

docker build . -t sensor_relay

To run the image with the envfile

docker run --env-file envfile sensor_relay
