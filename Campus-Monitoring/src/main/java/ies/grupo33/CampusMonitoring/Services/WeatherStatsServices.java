package ies.grupo33.CampusMonitoring.Services;

import ies.grupo33.CampusMonitoring.Repository.WeatherRepository;
import ies.grupo33.CampusMonitoring.Stats.WeatherStats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


@Service
public class WeatherStatsServices {

    @Autowired
    private WeatherRepository weatherRepository;

    public List<WeatherStats> getWeatherStats(String localName, LocalDate timeStart, LocalDate timeEnd) {
        if (timeStart == null || timeEnd == null) {
            throw new IllegalArgumentException("Time range is not defined.");
        } else if (localName == null) {
            throw new IllegalArgumentException("Local name is not defined.");
        } else {
            return weatherRepository.computeWeatherStats(localName, timeStart, timeEnd);
        }
    }
}
