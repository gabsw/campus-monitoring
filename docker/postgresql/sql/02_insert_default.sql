INSERT INTO campus_monitoring.LOCAL (name, max_temp_limit, min_temp_limit, max_hum_limit, min_hum_limit, max_co2_limit)
VALUES
('Refeitório de Santiago', 25.00, 15.00, 60, 40, 1000),
('Refeitório do Castro', 26.00, 16.00, 65, 45, 1000),
('Cafetaria da ESAN', 24.00, 14.00, 70, 35, 1000);

INSERT INTO campus_monitoring.SENSOR (id, local_name)
VALUES
(1, 'Refeitório de Santiago'),
(2, 'Refeitório do Castro'),
(3, 'Cafetaria da ESAN');



