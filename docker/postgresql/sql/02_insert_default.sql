INSERT INTO campus_monitoring.LOCAL (name, max_temp_limit, min_temp_limit, max_hum_limit, min_hum_limit, max_co2_limit)
VALUES
('Refeitório de Santiago', 25.00, 15.00, 60, 40, 1000),
('Refeitório do Crasto', 26.00, 16.00, 65, 45, 1000),
('Cafetaria da ESAN', 24.00, 14.00, 70, 35, 1000),
('IEETA', 25.00, 20.00, 60, 40, 1000);

INSERT INTO campus_monitoring.SENSOR (id, local_name, hardware)
VALUES
(1, 'Refeitório de Santiago', 'Sensor simulado'),
(2, 'Refeitório do Crasto', 'Sensor simulado'),
(3, 'Cafetaria da ESAN', 'Sensor simulado'),
(4, 'IEETA', 'Raspberry Pi B3 com sensor BME680');

INSERT INTO campus_monitoring.USERS (username, name, email, admin)
VALUES
('pedro_bastos', 'Pedro Ferreira Bastos', 'campus.monitoring.ies+pedrobastos@gmail.com', true),
('joao_marques', 'Joao Almeida Marques', 'campus.monitoring.ies+joaomarques@gmail.com', true),
('maria_cardoso', 'Maria Silva Cardoso', 'campus.monitoring.ies+mariacardoso@gmail.com', false),
('joana_martins', 'Joana Costa Martins', 'campus.monitoring.ies+joanamartins@gmail.com', false);


INSERT INTO campus_monitoring.USERS_LOCAL
VALUES
('pedro_bastos', 'Refeitório de Santiago'),
('pedro_bastos', 'Refeitório do Crasto'),
('pedro_bastos', 'Cafetaria da ESAN'),
('joao_marques', 'IEETA'),
('maria_cardoso', 'Cafetaria da ESAN'),
('joana_martins', 'Cafetaria da ESAN');
