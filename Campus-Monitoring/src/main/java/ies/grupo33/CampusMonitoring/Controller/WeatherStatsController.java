package ies.grupo33.CampusMonitoring.Controller;

import ies.grupo33.CampusMonitoring.Representations.WeatherStatsRepresentation;
import ies.grupo33.CampusMonitoring.Services.RepresentationAdapterService;
import ies.grupo33.CampusMonitoring.Services.WeatherStatsServices;
import ies.grupo33.CampusMonitoring.Model.WeatherStats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

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
                                                            LocalDate endDate) {

        if (startDate == null || endDate == null) {
            return representationAdapterService.convert(weatherStatsServices.getWeatherStats(localName));
        } else {
            return representationAdapterService.convert(weatherStatsServices.getWeatherStats(localName, startDate, endDate));
        }

    }
}