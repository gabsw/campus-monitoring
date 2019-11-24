package ies.grupo33.CampusMonitoring.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ies.grupo33.CampusMonitoring.Model.WeatherReading;

public interface WeatherRepository extends JpaRepository<WeatherReading, Long>{
}