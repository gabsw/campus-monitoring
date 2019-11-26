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
	max_temp_limit			NUMERIC (4, 2) 		NOT NULL,
	min_temp_limit			NUMERIC (4, 2) 		NOT NULL,
	max_hum_limit			NUMERIC (4, 1) 		NOT NULL    CHECK (max_hum_limit >= 0.0),
	min_hum_limit			NUMERIC (4, 1) 		NOT NULL    CHECK (min_hum_limit >= 0.0),
	max_co2_limit		       	NUMERIC (5, 1) 		    	    CHECK (max_co2_limit >= 0.0),
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
	humidity			NUMERIC (4, 1) 	NOT NULL    CHECK (humidity >= 0.0),
	co2		        	NUMERIC (5, 1) 		    CHECK (co2 >= 0.0),
	date_time     	TIMESTAMP     	NOT NULL,
	sensor_id     	INT           	NOT NULL,
	PRIMARY KEY(date_time, sensor_id),
	FOREIGN KEY (sensor_id) REFERENCES campus_monitoring.SENSOR(id)
);
GO