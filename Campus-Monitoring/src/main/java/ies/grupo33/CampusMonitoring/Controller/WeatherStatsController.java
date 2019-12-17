package ies.grupo33.CampusMonitoring.Controller;

import ies.grupo33.CampusMonitoring.Exception.ForbiddenUserException;
import ies.grupo33.CampusMonitoring.Exception.LocalNotFoundException;
import ies.grupo33.CampusMonitoring.Exception.LoginRequiredException;
import ies.grupo33.CampusMonitoring.Exception.UserNotFoundException;
import ies.grupo33.CampusMonitoring.Representations.WeatherStatsRepresentation;
import ies.grupo33.CampusMonitoring.Services.RepresentationAdapterService;
import ies.grupo33.CampusMonitoring.Services.WeatherStatsServices;
import ies.grupo33.CampusMonitoring.security.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("weather-stats")
public class WeatherStatsController {
    @Autowired
    private WeatherStatsServices weatherStatsServices;

    @Autowired
    private RepresentationAdapterService representationAdapterService;

    @GetMapping("/{localName}")
    public List<WeatherStatsRepresentation> getWeatherStats(@PathVariable String localName,
                                                            @RequestParam(name = "start_date", required = false)
                                                            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                                    LocalDate startDate,
                                                            @RequestParam(name = "end_date", required = false)
                                                            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                                    LocalDate endDate, HttpServletRequest request)
            throws ForbiddenUserException, LocalNotFoundException, UserNotFoundException, LoginRequiredException {
        String username = SecurityUtils.getUserIdentity(request.getSession());
        if (startDate == null || endDate == null) {
            return representationAdapterService.convertWeatherStatsRep(weatherStatsServices.getWeatherStats(localName, username));
        } else {
            return representationAdapterService.convertWeatherStatsRep(weatherStatsServices.getWeatherStats(localName, startDate, endDate, username));
        }

    }
}