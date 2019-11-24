CREATE VIEW campus_monitoring.weather_stats_view AS
SELECT DATE(date_time) as "date",
name AS local_name,
MAX(temperature) AS temp_max,
MIN(temperature) AS temp_min,
ROUND(AVG(temperature), 2) AS temp_avg,
MAX(humidity) AS hum_max,
MIN(humidity) AS hum_min,
ROUND(AVG(humidity), 2) AS hum_avg,
MAX(co2) AS co2_max,
MIN(co2) AS co2_min,
ROUND(AVG(co2), 2) AS co2_avg
FROM campus_monitoring.weather_reading AS wr
JOIN campus_monitoring.sensor AS se
ON wr.sensor_id = se.id
JOIN campus_monitoring.local AS lo
ON se.local_name = lo.name
GROUP BY DATE(date_time), name;

