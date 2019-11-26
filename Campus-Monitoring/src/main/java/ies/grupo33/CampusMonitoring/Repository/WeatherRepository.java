package ies.grupo33.CampusMonitoring.Repository;

import ies.grupo33.CampusMonitoring.Model.WeatherReading;
import ies.grupo33.CampusMonitoring.Model.WeatherReadingPK;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface WeatherRepository extends JpaRepository<WeatherReading, WeatherReadingPK> {

	List<WeatherReading> findBySensorId(long sensorId);
}