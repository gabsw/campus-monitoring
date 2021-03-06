package ies.grupo33.CampusMonitoring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@ComponentScan
public class CampusMonitoringApplication {

    public static void main(String[] args) {
        SpringApplication.run(CampusMonitoringApplication.class, args);
    }

}
