INSERT INTO campus_monitoring.LOCAL (name, max_temp_limit, min_temp_limit, max_hum_limit, min_hum_limit, max_co2_limit)
VALUES
('Refeitório de Santiago', 25.00, 15.00, 60, 40, 1000),
('Refeitório do Crasto', 26.00, 16.00, 65, 45, 1000),
('Cafetaria da ESAN', 24.00, 14.00, 70, 35, 1000), 
('IEETA', 25.00, 20.00, 60, 40, 1000), --
('Secretaria DETI', 27.00, 21.00, 50, 30, 900),
('Anfiteatro V DETI', 26.00, 19.00, 60, 30, 950),
('Escritorio Gabriela', 26.00, 18.00, 60, 30, 800);


INSERT INTO campus_monitoring.SENSOR (id, local_name, hardware)
VALUES
(1, 'Refeitório de Santiago', 'Sensor simulado'),
(2, 'Refeitório do Crasto', 'Sensor simulado'),
(3, 'Cafetaria da ESAN', 'Sensor simulado'),
(4, 'IEETA', 'Sensor simulado'), -- 
(5, 'Secretaria DETI', 'Sensor simulado'),
(6, 'Anfiteatro V DETI', 'Sensor simulado'),
(7, 'Escritorio Gabriela', 'Raspberry Pi B3 com sensor BME680');

INSERT INTO campus_monitoring.USERS (username, name, passwd, email, admin)
VALUES
('pedro_bastos', 'Pedro Ferreira Bastos', 'password', 'campus.monitoring.ies+pedrobastos@gmail.com', true),
('joao_marques', 'Joao Almeida Marques','password' , 'campus.monitoring.ies+joaomarques@gmail.com', true),
('maria_cardoso', 'Maria Silva Cardoso','password' , 'campus.monitoring.ies+mariacardoso@gmail.com', false),
('joana_martins', 'Joana Costa Martins','password' , 'campus.monitoring.ies+joanamartins@gmail.com', false), --
('tomas_neves', 'Tomas Henriques Neves','password' , 'campus.monitoring.ies+tomasneves@gmail.com', false),
('leandro_coelho', 'Leandro Lopes Coelho','password' , 'campus.monitoring.ies+leandrocoelho@gmail.com', false),
('miguel_vieira', 'Miguel Cunha Vieira','password' , 'campus.monitoring.ies+miguelvieira@gmail.com', false),
('cristina_miranda', 'Cristina Sampaio Miranda','password' , 'campus.monitoring.ies+cristinamiranda@gmail.com', false),
('nuno_gaio', 'Nuno Santos Gaio','password' , 'campus.monitoring.ies+nunogaio@gmail.com', false),
('samuel_rocha', 'Samuel Costa Rocha','password' , 'campus.monitoring.ies+samuelrocha@gmail.com', false),
('eduardo_lima', 'Eduardo Carvalho Lima','password' , 'campus.monitoring.ies+eduardolima@gmail.com', false),
('fernanda_oliveira', 'Fernanda Alves Oliveira','password' , 'campus.monitoring.ies+fernandaoliveira@gmail.com', false),
('marina_ribeiro', 'Marina Goncalves Ribeiro','password' , 'campus.monitoring.ies+marinaribeiro@gmail.com', false),
('vitoria_barbosa', 'Vitoria Pinto Barbosa','password' , 'campus.monitoring.ies+vitoriabarbosa@gmail.com', false),
('laura_melo', 'Laura Sousa Melo','password' , 'campus.monitoring.ies+lauramelo@gmail.com', false),
('bruna_sousa', 'Bruna Pereira Sousa','password' , 'campus.monitoring.ies+brunasousa@gmail.com', false),
('telmo_faria', 'Telmo Leal Faria','password' , 'campus.monitoring.ies+telmofaria@gmail.com', false),
('gabriela_santos', 'Ana Gabriela Loureiro dos Santos','password' , 'gabrielasantos91@ua.pt', true);


INSERT INTO campus_monitoring.USERS_LOCAL
VALUES
('pedro_bastos', 'Refeitório de Santiago'),
('pedro_bastos', 'Refeitório do Crasto'),
('pedro_bastos', 'Cafetaria da ESAN'),
('joao_marques', 'IEETA'),
('maria_cardoso', 'Cafetaria da ESAN'),
('joana_martins', 'Cafetaria da ESAN'), --
('joao_marques', 'Secretaria DETI'),
('joao_marques', 'Anfiteatro V DETI'),
('tomas_neves', 'Cafetaria da ESAN'),
('leandro_coelho', 'Cafetaria da ESAN'),
('cristina_miranda', 'Cafetaria da ESAN'),
('miguel_vieira', 'IEETA'),
('nuno_gaio', 'IEETA'),
('samuel_rocha', 'IEETA'),
('eduardo_lima', 'IEETA'),
('fernanda_oliveira', 'Refeitório de Santiago'),
('bruna_sousa', 'Refeitório de Santiago'),
('marina_ribeiro', 'Refeitório do Crasto'),
('telmo_faria', 'Refeitório do Crasto'),
('vitoria_barbosa', 'Secretaria DETI'),
('laura_melo', 'Anfiteatro V DETI'),
('gabriela_santos', 'Escritorio Gabriela');

INSERT INTO campus_monitoring.REVIEW (username, date_time, local_name, rating, content)
VALUES
('maria_cardoso', '2019-12-09 09:10:25', 'Cafetaria da ESAN', 1, 'Estou bastante insatisfeita com as condições da cafetaria e gostaria de ver uma intervenção imediata para equilibrar as temperaturas durante os piores meses de Inverno. Estar várias horas exposta ao frio tem vindo a afetar muito o meu problema de saúde.'),
('joana_martins', '2019-12-09 17:10:25', 'Cafetaria da ESAN', 3, 'Mediano. Não é suficiente para me sentir desconfortável, mas também não considero que a cafetaria seja um local propriamente agradável.'),
('tomas_neves', '2019-12-10 07:10:25', 'Cafetaria da ESAN', 2, 'Acho que a moral da equipa ficaria mais elevada se não tivéssemos de lidar com excesso de frio no Inverno num local onde passamos mais de 8h por dia.'),
('leandro_coelho', '2019-12-10 11:10:25', 'Cafetaria da ESAN', 1, 'É muito incomodativo passar tempo na cafetaria durante a etapa de limpeza ao final do dia, estamos muito expostos ao frio no Inverno e calor no Verão, atrasa ainda mais o trabalho que temos para fazer devido à desconcentração.'),
('cristina_miranda', '2019-12-10 14:10:25', 'Cafetaria da ESAN', 1, 'Já cheguei várias vezes resfriada a casa após o meu turno, a situação atual de entrada de frio é preocupante.'),
('fernanda_oliveira', '2019-12-10 07:10:25', 'Refeitório de Santiago', 3, 'No geral, considero-me satisfeita. A maioria dos dias é bastante agradável e os problemas que surgiram foram prontamente resolvidos.'),
('bruna_sousa', '2019-12-10 11:10:25', 'Refeitório de Santiago', 3, 'Nada a apontar a nível de anomalias. Tenho condições decentes para passar 8h no refeitório'),
('marina_ribeiro', '2019-12-10 07:10:25', 'Refeitório do Crasto', 4, 'Acima da média. O chefe de equipa é muito interventivo relativamente a chamar alguém quando o aquecimento se avaria, o que foi muito raro de acontecer nos 5 anos em que trabalho neste espaço.'),
('telmo_faria', '2019-12-10 11:10:25', 'Refeitório do Crasto', 5, 'Espaço ótimo. Tenho fibromialgia e o refeitório é um espaço convidativo, sinto que o clima se encontra num ponto ótimo e o meu chefe de equipa acomoda bastante as minhas dificuldades de gerir a doença com as expetativas do trabalho, muitas vezes indo falar para ajustarem o aquecedor e humidificador.'),
('nuno_gaio', '2019-12-10 07:10:25', 'IEETA', 4, 'Habitualmente satisfeito, tenho todas as condições para desenvolver o meu trabalho num lugar confortável.'),
('eduardo_lima', '2019-12-10 11:10:25', 'IEETA', 3, 'O espaço é satisfatório no geral, acho que há simplesmente algumas coisas a melhorar relativamente a humidade no pico do Inverno, de resto não tenho queixas.'),
('samuel_rocha', '2019-12-11 23:08:25', 'IEETA', 1, 'Encontro-me bastante preocupado com as notificações relativas ao aumento de CO2 no meu local de trabalho, seria de pensar que em 2019 já houvesse mais sensibilidade para a importância de lidar com estes assuntos. Será de esperar alguma intervenção?'),
('miguel_vieira', '2019-12-11 23:10:25', 'IEETA', 1, 'Vinha reportar uma situação que ocorreu no dia 11/12/2019 relativamente a níveis bastantes elevados de CO2. Não é aceitável para um espaço como este, ficámos a achar que poderia haver um possível incêndio, o que seria altamente desastroso com o equipamento que deixamos diariamente no open space.');


