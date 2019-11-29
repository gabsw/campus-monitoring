CREATE FUNCTION log_universal_alarms()
  RETURNS trigger AS
$BODY$
DECLARE
       status_max_temp_sensor boolean;
	   status_min_temp_sensor boolean;
	   status_max_hum_sensor boolean;
	   status_min_hum_sensor boolean;
	   status_max_co2_sensor boolean;
	   max_temp 		NUMERIC (4, 2);
	   min_temp		NUMERIC (4, 2);
	   max_hum		NUMERIC (4, 1);
	   min_hum		NUMERIC (4, 1);
	   max_co2		NUMERIC (5, 1);
	   
BEGIN

	-- Retrieve limit conditions for sensor
	SELECT lo.max_temp_limit, lo.min_temp_limit, lo.max_hum_limit,
	lo.min_hum_limit, lo.max_co2_limit
	INTO max_temp, min_temp, max_hum, min_hum, max_co2
	FROM campus_monitoring.LOCAL AS lo
	JOIN campus_monitoring.SENSOR AS se
	ON lo.name = se.local_name
	WHERE se.id = NEW.sensor_id;
	
	-- Retrieve latest alarm statuses for sensor
	SELECT status
	INTO status_max_temp_sensor
    	FROM campus_monitoring.UNIVERSAL_ALARM AS ua
	WHERE ua.sensor_id = NEW.sensor_id
	AND ua.violation_type = 'max'
	AND ua.violation_parameter = 'temperature'
	ORDER BY start_date_time DESC 
	LIMIT 1;
	
	SELECT status
	INTO status_min_temp_sensor
    	FROM campus_monitoring.UNIVERSAL_ALARM AS ua
	WHERE ua.sensor_id = NEW.sensor_id
	AND ua.violation_type = 'min'
	AND ua.violation_parameter = 'temperature'
	ORDER BY start_date_time DESC 
	LIMIT 1;
	
	SELECT status
	INTO status_max_hum_sensor
    	FROM campus_monitoring.UNIVERSAL_ALARM AS ua
	WHERE ua.sensor_id = NEW.sensor_id
	AND ua.violation_type = 'max'
	AND ua.violation_parameter = 'humidity'
	ORDER BY start_date_time DESC 
	LIMIT 1;
	
	SELECT status
	INTO status_min_hum_sensor
    	FROM campus_monitoring.UNIVERSAL_ALARM AS ua
	WHERE ua.sensor_id = NEW.sensor_id
	AND ua.violation_type = 'min'
	AND ua.violation_parameter = 'humidity'
	ORDER BY start_date_time DESC 
	LIMIT 1;
	
	SELECT status
	INTO status_max_co2_sensor
    	FROM campus_monitoring.UNIVERSAL_ALARM AS ua
	WHERE ua.sensor_id = NEW.sensor_id
	AND ua.violation_type = 'max'
	AND ua.violation_parameter = 'co2'
	ORDER BY start_date_time DESC 
	LIMIT 1;
	
	-- Check conditions for insertion of new alarm
	IF (NEW.temperature > max_temp AND (status_max_temp_sensor IS NULL OR status_max_temp_sensor = FALSE)) THEN
		INSERT INTO campus_monitoring.UNIVERSAL_ALARM(start_date_time, end_date_time, sensor_id,violation_type, violation_parameter, violation_value, status)
       	VALUES(NEW.date_time, NULL, NEW.sensor_id, 'max', 'temperature', NEW.temperature, TRUE);
	END IF;
		
	IF (NEW.temperature < min_temp AND (status_min_temp_sensor IS NULL OR status_min_temp_sensor = FALSE)) THEN
		INSERT INTO campus_monitoring.UNIVERSAL_ALARM(start_date_time, end_date_time, sensor_id,violation_type, violation_parameter, violation_value, status)
       	VALUES(NEW.date_time, NULL, NEW.sensor_id, 'min', 'temperature', NEW.temperature, TRUE);
	END IF;
		
	IF (NEW.humidity > max_hum AND (status_max_hum_sensor IS NULL OR status_max_hum_sensor = FALSE)) THEN
		INSERT INTO campus_monitoring.UNIVERSAL_ALARM(start_date_time, end_date_time, sensor_id,violation_type, violation_parameter, violation_value, status)
       	VALUES(NEW.date_time, NULL, NEW.sensor_id, 'max', 'humidity', NEW.humidity, TRUE);
	END IF;
		
	IF (NEW.humidity < min_hum AND (status_min_hum_sensor IS NULL OR status_min_hum_sensor = FALSE)) THEN
		INSERT INTO campus_monitoring.UNIVERSAL_ALARM(start_date_time, end_date_time, sensor_id,violation_type, violation_parameter, violation_value, status)
       	VALUES(NEW.date_time, NULL, NEW.sensor_id, 'min', 'humidity', NEW.humidity, TRUE);
	END IF;
		
	IF (NEW.co2 > max_co2 AND (status_max_co2_sensor IS NULL OR status_max_co2_sensor = FALSE)) THEN
		INSERT INTO campus_monitoring.UNIVERSAL_ALARM(start_date_time, end_date_time, sensor_id,violation_type, violation_parameter, violation_value, status)
       	VALUES(NEW.date_time, NULL, NEW.sensor_id, 'max', 'co2', NEW.co2, TRUE);
	END IF;
	
	-- Check conditions for closing alarms
	IF (NEW.temperature <= max_temp AND status_max_temp_sensor = TRUE) THEN
		UPDATE campus_monitoring.UNIVERSAL_ALARM
		SET status = FALSE, end_date_time = now()
       	WHERE sensor_id = NEW.sensor_id 
		AND violation_type = 'max'
		AND violation_parameter = 'temperature'
		AND status = TRUE;
	END IF;
	
	IF (NEW.temperature >= min_temp AND status_min_temp_sensor = TRUE) THEN
		UPDATE campus_monitoring.UNIVERSAL_ALARM
		SET status = FALSE, end_date_time = now()
       	WHERE sensor_id = NEW.sensor_id 
		AND violation_type = 'min'
		AND violation_parameter = 'temperature'
		AND status = TRUE;
	END IF;
	
	IF (NEW.humidity <= max_hum AND status_max_hum_sensor = TRUE) THEN
		UPDATE campus_monitoring.UNIVERSAL_ALARM
		SET status = FALSE, end_date_time = now()
       	WHERE sensor_id = NEW.sensor_id 
		AND violation_type = 'max'
		AND violation_parameter = 'humidity'
		AND status = TRUE;
	END IF;
	
	IF (NEW.humidity >= min_hum AND status_min_hum_sensor = TRUE) THEN
		UPDATE campus_monitoring.UNIVERSAL_ALARM
		SET status = FALSE, end_date_time = now()
       	WHERE sensor_id = NEW.sensor_id 
		AND violation_type = 'min'
		AND violation_parameter = 'humidity'
		AND status = TRUE;
	END IF;
	
	IF (NEW.co2 <= max_co2 AND status_max_co2_sensor = TRUE) THEN
		UPDATE campus_monitoring.UNIVERSAL_ALARM
		SET status = FALSE, end_date_time = now()
       	WHERE sensor_id = NEW.sensor_id 
		AND violation_type = 'max'
		AND violation_parameter = 'co2'
		AND status = TRUE;
	END IF;
 
   RETURN NEW;
END;
$BODY$

LANGUAGE plpgsql VOLATILE -- Says the function is implemented in the plpgsql language; VOLATILE says the function has side effects.
COST 100; -- Estimated execution cost of the function.









CREATE TRIGGER universal_alarm
  AFTER INSERT
  ON campus_monitoring.WEATHER_READING
  FOR EACH ROW
  EXECUTE PROCEDURE log_universal_alarms();

