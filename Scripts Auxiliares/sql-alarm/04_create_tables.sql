-----------------------------------------------------------
--------------------------TABLES---------------------------
-----------------------------------------------------------

CREATE TABLE campus_monitoring.UNIVERSAL_ALARM(
	start_date_time     	TIMESTAMP     	NOT NULL,
	end_date_time     	TIMESTAMP,
	sensor_id     		INT           	NOT NULL,
	violation_type		VARCHAR(50)	NOT NULL,
	violation_parameter	VARCHAR(50)	NOT NULL,
	violation_value		NUMERIC (5, 1)	NOT NULL,
	status			BOOLEAN		NOT NULL,
	notification_sent	BOOLEAN		NOT NULL,
	PRIMARY KEY (start_date_time, sensor_id, violation_parameter),
	FOREIGN KEY (start_date_time, sensor_id) REFERENCES campus_monitoring.WEATHER_READING(date_time, sensor_id)
);









