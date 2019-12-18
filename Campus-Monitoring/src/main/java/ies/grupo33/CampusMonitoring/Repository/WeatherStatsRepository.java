package ies.grupo33.CampusMonitoring.Repository;

import ies.grupo33.CampusMonitoring.Model.WeatherStats;
import ies.grupo33.CampusMonitoring.Model.WeatherStatsPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface WeatherStatsRepository extends JpaRepository<WeatherStats, WeatherStatsPK> {
    List<WeatherStats> findByWeatherStatsPKLocalNameOrderByWeatherStatsPKDateAsc(String localName);

    List<WeatherStats> findByWeatherStatsPKLocalNameAndWeatherStatsPKDateBetweenOrderByWeatherStatsPKDateAsc(String localName,
                                                                                                             LocalDate start,
                                                                                                             LocalDate end);
}