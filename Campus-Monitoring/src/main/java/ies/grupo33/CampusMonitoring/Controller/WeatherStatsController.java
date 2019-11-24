package ies.grupo33.CampusMonitoring.Controller;

import ies.grupo33.CampusMonitoring.Services.WeatherStatsServices;
import ies.grupo33.CampusMonitoring.Stats.WeatherStats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@RestController
public class WeatherStatsController {
    @Autowired
    private WeatherStatsServices weatherStatsServices;

    // just an example
    @GetMapping("/stats")
    public List<WeatherStats> getWeatherStats() {
        LocalDate before = LocalDate.now();
        LocalDate today = LocalDate.now();

        return weatherStatsServices.getWeatherStats("Cafetaria da ESAN", before, today);
    }
}