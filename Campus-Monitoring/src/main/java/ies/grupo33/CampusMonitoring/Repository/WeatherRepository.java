package ies.grupo33.CampusMonitoring.Repository;

import ies.grupo33.CampusMonitoring.Model.WeatherReading;
import ies.grupo33.CampusMonitoring.Model.WeatherReadingPK;
import ies.grupo33.CampusMonitoring.Stats.WeatherStats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface WeatherRepository extends JpaRepository<WeatherReading, WeatherReadingPK> {
    List<WeatherStats> computeWeatherStats(@Param("localName") String localName,
                                           @Param("timeStart") LocalDate timeStart,
                                           @Param("timeEnd") LocalDate timeEnd);
}