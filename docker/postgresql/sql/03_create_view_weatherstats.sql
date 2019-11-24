CREATE VIEW weather_stats_view AS
SELECT DATE(date_time),
MAX(temperature) AS tempMax,
MIN(temperature) AS tempMin,
ROUND(AVG(temperature), 2) AS tempAvg,
MAX(humidity) AS humMax,
MIN(humidity) AS humMax,
ROUND(AVG(humidity), 2) AS humAvg,
MAX(co2) AS co2Max,
MIN(co2) AS co2Min,
ROUND(AVG(co2), 2) AS co2Avg,
name AS localName
FROM campus_monitoring.weather_reading AS wr
JOIN campus_monitoring.sensor AS se
ON wr.sensor_id = se.id
JOIN campus_monitoring.local AS lo
ON se.local_name = lo.name;


