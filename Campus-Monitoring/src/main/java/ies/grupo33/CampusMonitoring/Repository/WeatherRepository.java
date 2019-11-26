package ies.grupo33.CampusMonitoring.Repository;

import ies.grupo33.CampusMonitoring.Model.WeatherReading;
import ies.grupo33.CampusMonitoring.Model.WeatherReadingPK;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface WeatherRepository extends JpaRepository<WeatherReading, WeatherReadingPK> {

	List<WeatherReading> findByWeatherReadingPKSensorIdOrderByWeatherReadingPKDateTimeAsc(long sensorId);

	List<WeatherReading> findByWeatherReadingPKSensorIdAndWeatherReadingPKDateTimeBetweenOrderByWeatherReadingPKDateTimeAsc(
			long sensorId, LocalDateTime dateInit, LocalDateTime dateFin);
	
	List<WeatherReading> findByWeatherReadingPKDateTimeOrderByWeatherReadingPKDateTimeAsc(LocalDateTime dateInit, LocalDateTime dateFin);

	List<WeatherReading> findByLocalOrderByWeatherReadingPKDateTimeAsc(String local);

	List<WeatherReading> findByLocalAndWeatherReadingPKDateTimeBetweenOrderByWeatherReadingPKDateTimeAsc(String local,
			LocalDateTime dateInit, LocalDateTime dateFin);
}