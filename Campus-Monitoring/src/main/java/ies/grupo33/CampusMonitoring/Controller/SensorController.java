package ies.grupo33.CampusMonitoring.Controller;

import ies.grupo33.CampusMonitoring.DTO.WeatherReadingDto;
import ies.grupo33.CampusMonitoring.Exception.*;
import ies.grupo33.CampusMonitoring.Model.Sensor;
import ies.grupo33.CampusMonitoring.Services.SensorServices;
import ies.grupo33.CampusMonitoring.Services.WeatherServices;
import ies.grupo33.CampusMonitoring.security.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("sensor")
public class SensorController {

    @Autowired
    private SensorServices sensorServices;
    @Autowired
    private WeatherServices weatherServices;

    @GetMapping("/")
    public List<Sensor> getSensors(HttpServletRequest request) throws LoginRequiredException, UserNotFoundException, ForbiddenUserException {
        String username = SecurityUtils.getUserIdentity(request.getSession());

        return sensorServices.getSensors(username);
    }

    @GetMapping("/{id}/weather-readings/latest")
    public WeatherReadingDto getLatestWeatherReadingBySensorId(@PathVariable Long id,
                                                               HttpServletRequest request)
            throws SensorNotFoundException, WeatherReadingNotFoundException, LoginRequiredException, UserNotFoundException, ForbiddenUserException {
        String username = SecurityUtils.getUserIdentity(request.getSession());
        return weatherServices.getMostRecentWeatherReadingBySensorId(id, username);
    }

    @GetMapping("/{id}/weather-readings")
    public List<WeatherReadingDto> getWeatherReadingBySensorId(@PathVariable Long id,
                                                               @RequestParam(name = "start_date", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
                                                               @RequestParam(name = "end_date", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate,
                                                               Pageable pageable,
                                                               HttpServletRequest request) throws SensorNotFoundException, LoginRequiredException, UserNotFoundException, ForbiddenUserException {
        String username = SecurityUtils.getUserIdentity(request.getSession());
        if (startDate == null || endDate == null) {
            return weatherServices.getWeatherReadingBySensor(id, pageable, username);
        } else {
            return weatherServices.getWeatherReadingBySensorAndDate(id, startDate, endDate, pageable, username);
        }
    }

}
