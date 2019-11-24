package ies.grupo33.CampusMonitoring.Services;

import ies.grupo33.CampusMonitoring.Repository.WeatherRepository;
import ies.grupo33.CampusMonitoring.Stats.WeatherStats;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;

public class WeatherStatsServices {

    @Autowired
    private WeatherRepository weatherRepository;

    public List<WeatherStats> getWeatherStats(String localName, LocalDateTime timeStart, LocalDateTime timeEnd) {

        if (timeStart == null || timeEnd == null) {
            throw new IllegalArgumentException("Time range is not defined.");
        }

        else if (localName == null) {
            throw new IllegalArgumentException("Local name is not defined.");
        }

        else {
            List<WeatherStats> weatherStats = weatherRepository.computeWeatherStats(localName, timeStart, timeEnd);

            return weatherStats;
        }
    }
}
