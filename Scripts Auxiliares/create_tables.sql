CREATE DATABASE campus_monitoring;
GO

USE campus_monitoring;
GO

CREATE SCHEMA campus_monitoring;
GO

-----------------------------------------------------------
--------------------------TABLES---------------------------
-----------------------------------------------------------

CREATE TABLE campus_monitoring.[LOCAL](
	name		    		VARCHAR(100) 		NOT NULL,
	PRIMARY KEY(name)
);
GO

CREATE TABLE campus_monitoring.SENSOR(
	id		        	SERIAL,
	local_name    	VARCHAR(100) 		NOT NULL,
	PRIMARY KEY(id),
	FOREIGN KEY (local_name) REFERENCES campus_monitoring.[LOCAL](name)
);
GO

CREATE TABLE campus_monitoring.WEATHER_READING(
	temperature			NUMERIC (4, 2) 	NOT NULL,
	pressure				NUMERIC (4, 0) 	NOT NULL,
	humidity				NUMERIC (4, 1) 	NOT NULL    CHECK (humidity >= 0),
	co2		        	NUMERIC (4, 0) 					    CHECK (co2 >= 0),
	date_time     	TIMESTAMP     	NOT NULL,
	sensor_id     	INT           	NOT NULL,
	PRIMARY KEY(date_time, sensor_id),
	FOREIGN KEY (sensor_id) REFERENCES campus_monitoring.SENSOR(id)
);
GO