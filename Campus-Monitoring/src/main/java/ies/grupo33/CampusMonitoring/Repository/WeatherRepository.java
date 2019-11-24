package ies.grupo33.CampusMonitoring.Repository;

import ies.grupo33.CampusMonitoring.Stats.WeatherStats;
import org.hibernate.annotations.NamedNativeQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import ies.grupo33.CampusMonitoring.Model.WeatherReading;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.SqlResultSetMapping;
import java.time.LocalDate;
import java.util.List;

public interface WeatherRepository extends JpaRepository<WeatherReading, Integer>{

    @SqlResultSetMapping(
            name="weatherStatsMapping",
            classes={
                    @ConstructorResult(
                            targetClass= WeatherStats.class,
                            columns={
                                    @ColumnResult(name="date"),
                                    @ColumnResult(name="tempMax"),
                                    @ColumnResult(name="tempMin"),
                                    @ColumnResult(name="tempAvg")
                                    @ColumnResult(name="humMax"),
                                    @ColumnResult(name="humMin"),
                                    @ColumnResult(name="humAvg"),
                                    @ColumnResult(name="co2Max"),
                                    @ColumnResult(name="co2Min"),
                                    @ColumnResult(name="co2Avg"),
                                    @ColumnResult(name="localName")
                            }
                    )
            }
    )
    @NamedNativeQuery(name="computeWeatherStats", query="SELECT MAX(temperature) AS tempMax, " +
            "FROM WEATHER_READING as wr " +
            "INNER JOIN SENSOR s ON wr.sensor_id = s.id " +
            "INNER JOIN LOCAL l ON s.local_name = l.name " +
            "WHERE l.name = :localName", resultSetMapping="weatherStatsMapping")
    public List<WeatherStats> computeWeatherStats(String localName, LocalDate timeStart, LocalDate timeEnd);
}