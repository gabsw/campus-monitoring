package ies.grupo33.CampusMonitoring.Controller;

import ies.grupo33.CampusMonitoring.Model.WeatherReading;
import ies.grupo33.CampusMonitoring.Services.WeatherServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("weather-reading")
public class WeatherController {

	@Autowired
	WeatherServices weatherServices;

	@GetMapping("/local/{localName}")
	public List<WeatherReading> getWeatherReading(@PathVariable String localName,
			@RequestParam(name="start_date", required=false)@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
			@RequestParam(name="end_date", required=false)@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate ) {
		if (startDate==null ||endDate==null) {
			return weatherServices.getWeatherReadingsByLocal(localName);
		}
		return weatherServices.getWeatherReadingByLocalAndDate(localName, startDate, endDate);
	}
}
