package ies.grupo33.CampusMonitoring.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ies.grupo33.CampusMonitoring.Model.Weather_Reading;

public interface WeatherRepository extends JpaRepository<Weather_Reading, Long>{
}
