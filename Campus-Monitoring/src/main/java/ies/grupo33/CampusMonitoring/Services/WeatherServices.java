package ies.grupo33.CampusMonitoring.Services;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ies.grupo33.CampusMonitoring.Model.Sensor;
import ies.grupo33.CampusMonitoring.Model.WeatherReading;
import ies.grupo33.CampusMonitoring.Model.WeatherReadingDto;
import ies.grupo33.CampusMonitoring.Repository.SensorRepository;
import ies.grupo33.CampusMonitoring.Repository.WeatherRepository;

@Service
public class WeatherServices {
	
	
	@Autowired
	private WeatherRepository weatherRepository;
	
	
	
	public Page<WeatherReading> getWeatherReadings(Pageable pageable){
		return weatherRepository.findAll(pageable);
	}
	
	public WeatherReading getMostRecentWeatherReadingByLocal(String local){
		if (local ==null) {
            throw new IllegalArgumentException("Local is not defined.");
		}
		Optional<WeatherReading> wr = weatherRepository.findByLocalNameOrderByWeatherReadingPKDateTimeDescFirst(local);
		
		if (wr.isPresent()) {
			return wr.get();
		} else {
			return null;
		}
	}
	
	public WeatherReading getMostRecentWeatherReadingBySensorId(long sensorId){
		
		Optional<WeatherReading> wr = weatherRepository.findFirstByWeatherReadingPKSensorIdOrderByWeatherReadingPKDateTimeDesc(sensorId);
		
		if (wr.isPresent()) {
			return wr.get();
		} else {
			return null;
		}
	}
	
	public List<WeatherReading> getWeatherReadingsByLocal(String local){
		if (local ==null) {
            throw new IllegalArgumentException("Local is not defined.");
		}
		List<WeatherReading> list = weatherRepository.findByLocalNameOrderByWeatherReadingPKDateTimeAsc(local);
		
		return list;
	}
	
	
	public Page<WeatherReading> getWeatherReadingBySensor(long sensorId, Pageable pageable) {
		
		Page<WeatherReading> weather= weatherRepository.findByWeatherReadingPKSensorIdOrderByWeatherReadingPKDateTimeAsc(sensorId, pageable);
		
		return weather;
	}
	

	public Page<WeatherReading> getWeatherReadingBySensorAndDate(long sensorId, LocalDateTime dateInit, LocalDateTime dateFin, Pageable pageable) {
		Page<WeatherReading> list;
		if (dateInit ==null || dateFin==null) {
            throw new IllegalArgumentException("Date interval is not defined.");
		}
		
		list = weatherRepository.findByWeatherReadingPKSensorIdAndWeatherReadingPKDateTimeBetweenOrderByWeatherReadingPKDateTimeAsc(sensorId, dateInit, dateFin, pageable);
		
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
		
		list = weatherRepository.findByLocalNameAndWeatherReadingPKDateTimeBetweenOrderByWeatherReadingPKDateTimeAsc(local, dateInit, dateFin);
		
		return list;		
	}
	
	
	public List<WeatherReading> getWeatherReadingByLocalLimit(String local, String limit) {
		if (local == null) {
            throw new IllegalArgumentException("Local is not defined.");
		}
		int limite = Integer.parseInt(limit);
		List<WeatherReading> list = weatherRepository.findByLocalNameLimit(local, limite);
		
		return list;
		
	}
	
	
}