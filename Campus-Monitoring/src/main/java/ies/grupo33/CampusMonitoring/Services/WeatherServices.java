package ies.grupo33.CampusMonitoring.Services;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ies.grupo33.CampusMonitoring.Model.Sensor;
import ies.grupo33.CampusMonitoring.Model.WeatherReading;
import ies.grupo33.CampusMonitoring.Repository.SensorRepository;
import ies.grupo33.CampusMonitoring.Repository.WeatherRepository;

@Service
public class WeatherServices {
	
	
	@Autowired
	private WeatherRepository weatherRepository;
	
	
	
	public List<WeatherReading> getWeatherReadings(){
		return weatherRepository.findAll();
	}
	
	public List<WeatherReading> getWeatherReadingsByDate(LocalDateTime dateInit, LocalDateTime dateFin){
		if (dateInit ==null || dateFin==null) {
            throw new IllegalArgumentException("Date interval is not defined.");
		}
		List<WeatherReading> list = weatherRepository.findByWeatherReadingPKDateTimeOrderByWeatherReadingPKDateTimeAsc(dateInit, dateFin);
		
		return list;
	}
	
	public List<WeatherReading> getWeatherReadingsByLocal(String local){
		if (local ==null) {
            throw new IllegalArgumentException("Local is not defined.");
		}
		List<WeatherReading> list = weatherRepository.findByLocalOrderByWeatherReadingPKDateTimeAsc(local);
		
		return list;
	}
	
	
	public List<WeatherReading> getWeatherReadingBySensor(long sensorId) {
		
		List<WeatherReading> weather= weatherRepository.findByWeatherReadingPKSensorIdOrderByWeatherReadingPKDateTimeAsc(sensorId);
		
		return weather;
	}
	

	public List<WeatherReading> getWeatherReadingBySensorAndDate(long sensorId, LocalDateTime dateInit, LocalDateTime dateFin) {
		List<WeatherReading> list;
		if (dateInit ==null || dateFin==null) {
            throw new IllegalArgumentException("Date interval is not defined.");
		}
		
		list = weatherRepository.findByWeatherReadingPKSensorIdAndWeatherReadingPKDateTimeBetweenOrderByWeatherReadingPKDateTimeAsc(sensorId, dateInit, dateFin);
		
		return list;		
	}
	
	public List<WeatherReading> getWeatherReadingByLocalAndDate(String local, LocalDateTime dateInit, LocalDateTime dateFin) {
		List<WeatherReading> list;
		if (dateInit ==null || dateFin==null) {
            throw new IllegalArgumentException("Date interval is not defined.");
		}
		if (local == null) {
            throw new IllegalArgumentException("Local is not defined.");
		}
		
		list = weatherRepository.findByLocalAndWeatherReadingPKDateTimeBetweenOrderByWeatherReadingPKDateTimeAsc(local, dateInit, dateFin);
		
		return list;		
	}
	
	
}
