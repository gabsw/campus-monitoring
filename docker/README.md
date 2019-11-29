# Instructions

## Running

To run, use `docker-compose up --build`. The build will force building the images.

## Application Dockerfile

The Dockerfile for the application is located in the root directory of the code (next to the project's pom.xml).

This is due to docker-compose not allowing the Dockerfile to be outside of the build context, despite docker allowing it.

## Connecting to the database

### OPTION 1

`psql postgres://ies_grupo33:db_grupo33@172.22.0.2:5432/db_campus_monitoring`

You can find the correct IP address by running `docker inspect ies_postgres`. The IP address can be found
under "IPAddress". It's usually near the end.

### OPTION 2

Run `docker exec -it ies_postgres bash`.

Then, run `psql -d db_campus_monitoring -U ies_grupo33 -W`.

You'll be prompted for a password. After that, you'll be connected to the database.

## Updating Postgresql Init Files

If you want to update the init sql files and you have already executed `docker-compose up --build`,
then you must delete the postgresql volume that holds the pgdata.

To do that:
1. Destroy the containers with `docker-compose rm`
2. Destroy the `docker_postgres_data` volume with `docker volume rm docker_postgres_data`

After that, you can simply run `docker-compose up` again.

Note that erasing the volume will remove all data.