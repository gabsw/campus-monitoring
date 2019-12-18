package ies.grupo33.CampusMonitoring.Repository;

import ies.grupo33.CampusMonitoring.DTO.WeatherReadingDto;
import ies.grupo33.CampusMonitoring.Model.WeatherReading;
import ies.grupo33.CampusMonitoring.Model.WeatherReadingPK;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface WeatherRepository extends JpaRepository<WeatherReading, WeatherReadingPK> {

	Page<WeatherReading> findByWeatherReadingPKSensorIdOrderByWeatherReadingPKDateTimeAsc(long sensorId, Pageable pageable);
	
	Optional<WeatherReading> findFirstByWeatherReadingPKSensorIdOrderByWeatherReadingPKDateTimeDesc(long sensorId);

	Page<WeatherReading> findByWeatherReadingPKSensorIdAndWeatherReadingPKDateTimeBetweenOrderByWeatherReadingPKDateTimeAsc(
			long sensorId, LocalDateTime dateInit, LocalDateTime dateFin, Pageable pageable);
	
	@Query(value = "Select temperature, humidity, co2, date_time, sensor_id from campus_monitoring.WEATHER_READING join campus_monitoring.SENSOR on campus_monitoring.WEATHER_READING.sensor_id=campus_monitoring.SENSOR.id where date_time>=?1 and date_time<=?2 order by date_time asc;",
			nativeQuery = true)
	List<WeatherReading> findByWeatherReadingPKDateTimeBetweenOrderByWeatherReadingPKDateTimeAsc(LocalDateTime dateInit, LocalDateTime dateFin);

	@Query(value = "Select temperature, humidity, co2, date_time, sensor_id from campus_monitoring.WEATHER_READING join campus_monitoring.SENSOR on campus_monitoring.WEATHER_READING.sensor_id=campus_monitoring.SENSOR.id where local_name=?1 order by date_time asc;",
			nativeQuery = true)
	List<WeatherReading> findByLocalNameOrderByWeatherReadingPKDateTimeAsc(String local);

	@Query(value = "Select temperature, humidity, co2, date_time, sensor_id from campus_monitoring.WEATHER_READING join campus_monitoring.SENSOR on campus_monitoring.WEATHER_READING.sensor_id=campus_monitoring.SENSOR.id where local_name=?1 and date_time>=?2 and date_time<=?3 order by date_time asc;",
			nativeQuery = true)
	List<WeatherReading> findByLocalNameAndWeatherReadingPKDateTimeBetweenOrderByWeatherReadingPKDateTimeAsc(String local,
																											 LocalDateTime dateInit, LocalDateTime dateFin);

	@Query(value = "Select temperature, humidity, co2, date_time, sensor_id from campus_monitoring.WEATHER_READING join campus_monitoring.SENSOR on campus_monitoring.WEATHER_READING.sensor_id=campus_monitoring.SENSOR.id where local_name=?1 order by date_time desc limit 1;",
			nativeQuery = true)
	Optional<WeatherReading> findByLocalNameOrderByWeatherReadingPKDateTimeDescFirst(String local);

	
	@Query(value = "Select temperature, humidity, co2, date_time, sensor_id from campus_monitoring.WEATHER_READING join campus_monitoring.SENSOR on campus_monitoring.WEATHER_READING.sensor_id=campus_monitoring.SENSOR.id where local_name=?1 order by date_time DESC limit ?2 ",
			nativeQuery = true)
	List<WeatherReading> findByLocalNameLimit(String local,int limit);


}