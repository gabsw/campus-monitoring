package ies.grupo33.CampusMonitoring.Controller;

import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import ies.grupo33.CampusMonitoring.DTO.WeatherReadingDto;
import ies.grupo33.CampusMonitoring.Exception.ForbiddenUserException;
import ies.grupo33.CampusMonitoring.Exception.LoginRequiredException;
import ies.grupo33.CampusMonitoring.Exception.SensorNotFoundException;
import ies.grupo33.CampusMonitoring.Exception.UserNotFoundException;
import ies.grupo33.CampusMonitoring.Exception.WeatherReadingNotFoundException;
import ies.grupo33.CampusMonitoring.Services.WeatherServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import ies.grupo33.CampusMonitoring.Model.Sensor;
import ies.grupo33.CampusMonitoring.Services.SensorServices;

@CrossOrigin
@RestController
@RequestMapping("sensor")
public class SensorController {
	
	@Autowired
	private SensorServices sensorServices;
	@Autowired
	private WeatherServices weatherServices;
	
	@GetMapping("/")
	public List<Sensor> getSensors(HttpServletRequest request) throws UserNotFoundException, LoginRequiredException, ForbiddenUserException{
		return sensorServices.getSensors(request.getSession());
	}

	@GetMapping("/id/{id}/weather-readings/latest")
	public WeatherReadingDto getLatestWeatherReadingBySensorId(@PathVariable Long id, HttpServletRequest request)
			throws SensorNotFoundException, WeatherReadingNotFoundException, UserNotFoundException, LoginRequiredException, ForbiddenUserException {
		return weatherServices.getMostRecentWeatherReadingBySensorId(id, request.getSession());
	}

	@GetMapping("/id/{id}/weather-readings")
	public List<WeatherReadingDto> getWeatherReadingBySensorId(@PathVariable Long id,
															   @RequestParam(name="start_date", required=false)@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
															   @RequestParam(name="end_date", required=false)@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate,
															   Pageable pageable,
															   HttpServletRequest request) throws SensorNotFoundException, UserNotFoundException, LoginRequiredException, ForbiddenUserException {
		if (startDate==null ||endDate==null) {
			return weatherServices.getWeatherReadingBySensor(id, pageable, request.getSession());
		}
		else {
			return weatherServices.getWeatherReadingBySensorAndDate(id, startDate, endDate, pageable, request.getSession());
		}
	}

}
