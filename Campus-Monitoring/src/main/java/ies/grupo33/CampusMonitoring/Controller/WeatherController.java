package ies.grupo33.CampusMonitoring.Controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ies.grupo33.CampusMonitoring.Model.WeatherReading;
import ies.grupo33.CampusMonitoring.Model.Local;
import ies.grupo33.CampusMonitoring.Services.LocalServices;
import ies.grupo33.CampusMonitoring.Services.SensorServices;
import ies.grupo33.CampusMonitoring.Services.WeatherServices;

@RestController
@RequestMapping("weather-reading")
public class WeatherController {
	
	@Autowired
	WeatherServices weatherServices;
	
	
	@GetMapping("/local/{localName}")
	public List<WeatherReading> getWeatherReading(@PathVariable String localName,
			@RequestParam(name="start_date", required=false)@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDateTime startDate,
			@RequestParam(name="end_date", required=false)@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDateTime endDate ) {
		if (startDate==null ||endDate==null) {
			return weatherServices.getWeatherReadingsByLocal(localName);
		}
		
		return weatherServices.getWeatherReadingByLocalAndDate(localName, startDate, endDate);
	}
	
	
												  
	
}
