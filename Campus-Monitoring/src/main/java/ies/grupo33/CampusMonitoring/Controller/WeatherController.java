package ies.grupo33.CampusMonitoring.Controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ies.grupo33.CampusMonitoring.Model.WeatherReading;
import ies.grupo33.CampusMonitoring.DTO.WeatherReadingDto;
import ies.grupo33.CampusMonitoring.Exception.LocalNotFoundException;
import ies.grupo33.CampusMonitoring.Exception.SensorNotFoundException;
import ies.grupo33.CampusMonitoring.Model.Sensor;
import ies.grupo33.CampusMonitoring.Services.SensorServices;
import ies.grupo33.CampusMonitoring.Services.WeatherServices;

@RestController
@RequestMapping("weather-reading")
public class WeatherController {
	
	@Autowired
	WeatherServices weatherServices;
	
	@Autowired
	SensorServices sensorServices;
	
	@GetMapping("/latest/local-name/{localName}")
	public WeatherReadingDto getLatestWeatherReadingByLocal(@PathVariable String localName, Pageable pageable) throws LocalNotFoundException{
		WeatherReadingDto wr = weatherServices.getMostRecentWeatherReadingByLocal(localName);
		
		if(wr==null) {
			return null;
		}

		return wr;
	}
	
	@GetMapping("/latest/id/{id}")
	public WeatherReadingDto getLatestWeatherReadingBySensorId(@PathVariable String id, Pageable pageable) throws SensorNotFoundException{
		
		long sensor_id = Long.parseLong(id);
		
		WeatherReadingDto wr = weatherServices.getMostRecentWeatherReadingBySensorId(sensor_id);
		
		if(wr==null) {
			return null;
		}
		
		return wr;
	}
	
	@GetMapping("/local-name/{localName}")
	public List<WeatherReadingDto> getWeatherReadingByLocal(@PathVariable String localName,
			@RequestParam(name="start_date", required=false)@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
			@RequestParam(name="end_date", required=false)@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate ) throws LocalNotFoundException {
		List<WeatherReadingDto> l;
		if (startDate==null ||endDate==null) {
			l = weatherServices.getWeatherReadingsByLocal(localName);
		}
		else {
			l = weatherServices.getWeatherReadingByLocalAndDate(localName, startDate, endDate);
		}
		return l;
	}
	
	@GetMapping("/limit/local-name/{localName}")
	public List<WeatherReadingDto> getWeatherReadingByLocalLimit(@PathVariable String localName,
														@RequestParam(name="limit", required=true) String limit){
		List<WeatherReadingDto> l;
		
		l = weatherServices.getWeatherReadingByLocalLimit(localName, limit);
		
		return l;
	}
	
	
	@GetMapping("/id/{id}")
	public List<WeatherReadingDto> getWeatherReadingBySensorId(@PathVariable String id,
			@RequestParam(name="start_date", required=false)@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
			@RequestParam(name="end_date", required=false)@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate,
			Pageable pageable) throws SensorNotFoundException {
		long sensor_id = Long.parseLong(id);
		List<WeatherReadingDto> l;
		if (startDate==null ||endDate==null) {
			l = weatherServices.getWeatherReadingBySensor(sensor_id, pageable);
		}
		else {
			l = weatherServices.getWeatherReadingBySensorAndDate(sensor_id, startDate, endDate, pageable);
		}
		return l;
		
	}

												  
	
}