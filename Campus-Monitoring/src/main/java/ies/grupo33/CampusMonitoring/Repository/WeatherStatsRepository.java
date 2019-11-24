package ies.grupo33.CampusMonitoring.Repository;

import ies.grupo33.CampusMonitoring.Stats.WeatherStats;
import ies.grupo33.CampusMonitoring.Stats.WeatherStatsPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface WeatherStatsRepository extends JpaRepository<WeatherStats, WeatherStatsPK> {
    List<WeatherStats> findByWeatherStatsPKLocalName(String localName);

    List<WeatherStats> findByWeatherStatsPKLocalNameAndWeatherStatsPKDateBetween(String localName, LocalDate start, LocalDate end);
}
