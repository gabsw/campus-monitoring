package ies.grupo33.CampusMonitoring.Services;

import ies.grupo33.CampusMonitoring.Repository.WeatherStatsRepository;
import ies.grupo33.CampusMonitoring.Model.WeatherStats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


@Service
public class WeatherStatsServices {

    @Autowired
    private WeatherStatsRepository weatherStatsRepository;

    public List<WeatherStats> getWeatherStats(String localName, LocalDate timeStart, LocalDate timeEnd) {
        if (timeStart == null || timeEnd == null) {
            throw new IllegalArgumentException("Time range is not defined.");
        } else if (localName == null) {
            throw new IllegalArgumentException("Local name is not defined.");
        } else {
            return weatherStatsRepository.findByWeatherStatsPKLocalNameAndWeatherStatsPKDateBetweenOrderByWeatherStatsPKDateAsc(localName, timeStart, timeEnd);
        }
    }

    public List<WeatherStats> getWeatherStats(String localName) {
        if (localName == null) {
            throw new IllegalArgumentException("Local name is not defined.");
        } else {
            return weatherStatsRepository.findByWeatherStatsPKLocalNameOrderByWeatherStatsPKDateAsc(localName);
        }
    }
}
