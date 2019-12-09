CREATE SCHEMA campus_monitoring;

-----------------------------------------------------------
--------------------------TABLES---------------------------
-----------------------------------------------------------

CREATE TABLE campus_monitoring.LOCAL(
	name		    		VARCHAR(100) 		NOT NULL,
	max_temp_limit			NUMERIC (4, 2) 		NOT NULL,
	min_temp_limit			NUMERIC (4, 2) 		NOT NULL,
	max_hum_limit			NUMERIC (4, 1) 		NOT NULL    CHECK (max_hum_limit >= 0.0),
	min_hum_limit			NUMERIC (4, 1) 		NOT NULL    CHECK (min_hum_limit >= 0.0),
	max_co2_limit		    NUMERIC (5, 1) 		    	    CHECK (max_co2_limit >= 0.0),
	PRIMARY KEY(name)
);

CREATE TABLE campus_monitoring.SENSOR(
	id		        SERIAL,
	local_name    	VARCHAR(100) 		NOT NULL,
	hardware    	VARCHAR(500) 		NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (local_name) REFERENCES campus_monitoring.LOCAL(name) ON UPDATE CASCADE
);

CREATE TABLE campus_monitoring.WEATHER_READING(
	temperature		NUMERIC (4, 2) 	NOT NULL,
	humidity		NUMERIC (4, 1) 	NOT NULL    CHECK (humidity >= 0.0),
	co2		        NUMERIC (5, 1) 		        CHECK (co2 >= 0.0),
	date_time     	TIMESTAMP     	NOT NULL,
	sensor_id     	INT           	NOT NULL,
	PRIMARY KEY (date_time, sensor_id),
	FOREIGN KEY (sensor_id) REFERENCES campus_monitoring.SENSOR(id)
);

CREATE TABLE campus_monitoring.USERS(
	username		    	VARCHAR(30) 		NOT NULL,
	name		    		VARCHAR(100) 		NOT NULL,
	email		    		VARCHAR(256) 		NOT NULL,
	admin				BOOLEAN			NOT NULL,
	PRIMARY KEY(username)
);

CREATE TABLE campus_monitoring.USERS_LOCAL(
	username		    	VARCHAR(30) 		NOT NULL,
	local_name		    	VARCHAR(100) 		NOT NULL,
	PRIMARY KEY(username, local_name),
	FOREIGN KEY(username) REFERENCES campus_monitoring.USERS(username) ON UPDATE CASCADE,
	FOREIGN KEY(local_name) REFERENCES campus_monitoring.LOCAL(name) ON UPDATE CASCADE
);

CREATE TABLE campus_monitoring.UNIVERSAL_ALARM(
	start_date_time     	TIMESTAMP     	NOT NULL,
	end_date_time     	TIMESTAMP,
	local_name		VARCHAR(100) 	NOT NULL,
	violation_type		VARCHAR(50)	NOT NULL,
	violation_parameter	VARCHAR(50)	NOT NULL,
	violation_value		NUMERIC (5, 1)	NOT NULL,
	ongoing_status		BOOLEAN		NOT NULL,
	notification_sent	BOOLEAN		NOT NULL,
	PRIMARY KEY (start_date_time, local_name, violation_parameter),
	FOREIGN KEY (local_name) REFERENCES campus_monitoring.LOCAL(name) ON UPDATE CASCADE
);

CREATE TABLE campus_monitoring.REVIEW(
	id			SERIAL		NOT NULL,
	username		VARCHAR(30)	NOT NULL,
	date_time     		TIMESTAMP	NOT NULL,
	local_name    		VARCHAR(100) 	NOT NULL,
	rating			INT		NOT NULL	CHECK(rating >= 1 and rating <= 5),
	content			VARCHAR(1000)	NOT NULL,
	PRIMARY KEY(id),
	FOREIGN KEY(username) REFERENCES campus_monitoring.USERS(username) ON UPDATE CASCADE,
	FOREIGN KEY(local_name) REFERENCES campus_monitoring.LOCAL(name) ON UPDATE CASCADE
);
