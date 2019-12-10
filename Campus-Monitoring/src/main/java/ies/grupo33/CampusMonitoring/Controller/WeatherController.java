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
import ies.grupo33.CampusMonitoring.Model.WeatherReadingDto;
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
	public WeatherReadingDto getLatestWeatherReadingByLocal(@PathVariable String localName, Pageable pageable){
		WeatherReading wr = weatherServices.getMostRecentWeatherReadingByLocal(localName);
		
		if(wr==null) {
			return null;
		}
		
		WeatherReadingDto wrdto = new WeatherReadingDto(wr.getWeatherReadingPK().getSensorId(), wr.getWeatherReadingPK().getDateTime(), localName, wr.getTemperature(),
				wr.getHumidity(), wr.getCo2());
		return wrdto;
	}
	
	@GetMapping("/latest/id/{id}")
	public WeatherReadingDto getLatestWeatherReadingBySensorId(@PathVariable String id, Pageable pageable){
		
		long sensor_id = Long.parseLong(id);
		
		WeatherReading wr = weatherServices.getMostRecentWeatherReadingBySensorId(sensor_id);
		
		if(wr==null) {
			return null;
		}
		
		Sensor s = sensorServices.getSensor(sensor_id);
		
		WeatherReadingDto wrdto = new WeatherReadingDto(wr.getWeatherReadingPK().getSensorId(), wr.getWeatherReadingPK().getDateTime(), s.getLocalName(), wr.getTemperature(),
				wr.getHumidity(), wr.getCo2());
		return wrdto;
	}
	
	@GetMapping("/local-name/{localName}")
	public List<WeatherReadingDto> getWeatherReadingByLocal(@PathVariable String localName,
			@RequestParam(name="start_date", required=false)@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
			@RequestParam(name="end_date", required=false)@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate ) {
		List<WeatherReading> l;
		if (startDate==null ||endDate==null) {
			l = weatherServices.getWeatherReadingsByLocal(localName);
		}
		else {
			l = weatherServices.getWeatherReadingByLocalAndDate(localName, startDate, endDate);
		}
		List<WeatherReadingDto> rl = new ArrayList<>();
		for(WeatherReading wr:l) {
			rl.add(new WeatherReadingDto(wr.getWeatherReadingPK().getSensorId(), wr.getWeatherReadingPK().getDateTime(), localName, wr.getTemperature(),
					wr.getHumidity(), wr.getCo2()));
		}
		return rl;
	}
	
	@GetMapping("/limit/local-name/{localName}")
	public List<WeatherReadingDto> getWeatherReadingByLocalLimit(@PathVariable String localName,
														@RequestParam(name="limit", required=true) String limit){
		List<WeatherReading> l;
		
		l = weatherServices.getWeatherReadingByLocalLimit(localName, limit);
		List<WeatherReadingDto> rl = new ArrayList<>();
		for(WeatherReading wr:l) {
			rl.add(new WeatherReadingDto(wr.getWeatherReadingPK().getSensorId(), wr.getWeatherReadingPK().getDateTime(), localName, wr.getTemperature(),
					wr.getHumidity(), wr.getCo2()));
		}
		return rl;
	}
	
	
	@GetMapping("/id/{id}")
	public List<WeatherReadingDto> getWeatherReadingBySensorId(@PathVariable String id,
			@RequestParam(name="start_date", required=false)@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
			@RequestParam(name="end_date", required=false)@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate,
			Pageable pageable) {
		long sensor_id = Long.parseLong(id);
		Page<WeatherReading> l;
		if (startDate==null ||endDate==null) {
			l = weatherServices.getWeatherReadingBySensor(sensor_id, pageable);
		}
		else {
			l = weatherServices.getWeatherReadingBySensorAndDate(sensor_id, startDate, endDate, pageable);
		}
		List<WeatherReadingDto> rl = new ArrayList<>();
		Sensor s = sensorServices.getSensor(sensor_id);
		if(l.getSize()==0 && s!=null) {
			return rl;
		}
		for(WeatherReading wr:l) {
			rl.add(new WeatherReadingDto(wr.getWeatherReadingPK().getSensorId(), wr.getWeatherReadingPK().getDateTime(), s.getLocalName(), wr.getTemperature(),
					wr.getHumidity(), wr.getCo2()));
		}
		return rl;
	}

												  
	
}