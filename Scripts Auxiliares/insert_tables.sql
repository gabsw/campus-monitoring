USE campus_monitoring;

INSERT INTO campus_monitoring.LOCAL (name)
VALUES
('Refeitório de Santiago'),
('Refeitório do Castro'),
('Cafetaria da ESAN');

-- GO

INSERT INTO campus_monitoring.SENSOR (id, local_name)
VALUES
(1, 'Refeitório de Santiago'),
(2, 'Refeitório do Castro'),
(3, 'Cafetaria da ESAN');