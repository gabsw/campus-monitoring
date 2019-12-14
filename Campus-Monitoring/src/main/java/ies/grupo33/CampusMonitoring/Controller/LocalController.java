package ies.grupo33.CampusMonitoring.Controller;

import ies.grupo33.CampusMonitoring.DTO.ReportDTO;
import ies.grupo33.CampusMonitoring.DTO.WeatherReadingDto;
import ies.grupo33.CampusMonitoring.Exception.MismatchedReviewException;
import ies.grupo33.CampusMonitoring.Exception.UserCannotReviewException;
import ies.grupo33.CampusMonitoring.Model.Local;
import ies.grupo33.CampusMonitoring.Model.Review;
import ies.grupo33.CampusMonitoring.Representations.UniversalAlarmsRepresentation;

import ies.grupo33.CampusMonitoring.Services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("local")
public class LocalController {
    @Autowired
    private LocalServices localServices;
    @Autowired
    private ReviewServices reviewServices;
    @Autowired
    private ReportServices reportServices;
    @Autowired
    private UniversalAlarmServices universalAlarmServices;
    @Autowired
    private WeatherServices weatherServices;
    @Autowired
    private RepresentationAdapterService representationAdapterService;

    @GetMapping("/")
    public List<Local> getLocals() {
        return localServices.getAllLocals();
    }

    // end points dealing with reviews
    @GetMapping("/{localName}/reviews")
    public Page<Review> getLocalReviews(@PathVariable String localName, Pageable pageable) {
        return reviewServices.getReviewByLocal(localName, pageable);
    }

    @PostMapping("/{localName}/reviews")
    public void addReviewToLocal(@PathVariable String localName, @Valid @RequestBody Review review) throws UserCannotReviewException, MismatchedReviewException {
        reviewServices.addReviewToLocal(localName, review);
    }

    // end points for reports
    @GetMapping("/{localName}/report")
    public ReportDTO getReport(@PathVariable String localName,
                               @RequestParam(name = "start_date")
                               @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                       LocalDate startDate,
                               @RequestParam(name = "end_date")
                               @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                       LocalDate endDate) {
        return reportServices.buildReport(localName, startDate, endDate);
    }

    // end points for alarms
    @GetMapping("/{localName}/alarms/all")
    public Page<UniversalAlarmsRepresentation> getUniversalAlarms(@PathVariable String localName,
                                                                  @RequestParam(name = "start_date", required = false)
                                                   @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                           LocalDate startDate,
                                                                  @RequestParam(name = "end_date", required = false)
                                                   @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                           LocalDate endDate,
                                                                  Pageable pageable) {

        if (startDate == null || endDate == null) {
            return universalAlarmServices.getUniversalAlarm(localName, pageable).map(UniversalAlarmsRepresentation::new);
        } else {
            return universalAlarmServices.getUniversalAlarm(localName, startDate, endDate, pageable).map(UniversalAlarmsRepresentation::new);
        }

    }

    @GetMapping("/{localName}/alarms/ongoing")
    public Page<UniversalAlarmsRepresentation> getOpenUniversalAlarms(@PathVariable String localName,
                                                       @RequestParam(name = "start_date", required = false)
                                                       @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                               LocalDate startDate,
                                                       @RequestParam(name = "end_date", required = false)
                                                       @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                               LocalDate endDate,
                                                       Pageable pageable) {

        if (startDate == null || endDate == null) {
            return universalAlarmServices.getOpenUniversalAlarm(localName, pageable).map(UniversalAlarmsRepresentation::new);
        } else {
            return universalAlarmServices.getOpenUniversalAlarm(localName, startDate, endDate, pageable).map(UniversalAlarmsRepresentation::new);
        }
    }

    @GetMapping("/{localName}/alarms/closed")
    public Page<UniversalAlarmsRepresentation> getClosedUniversalAlarms(@PathVariable String localName,
                                                         @RequestParam(name = "start_date", required = false)
                                                         @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                                 LocalDate startDate,
                                                         @RequestParam(name = "end_date", required = false)
                                                         @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                                 LocalDate endDate,
                                                         Pageable pageable) {

        if (startDate == null || endDate == null) {
            return universalAlarmServices.getClosedUniversalAlarm(localName, pageable).map(UniversalAlarmsRepresentation::new);
        } else {
            return universalAlarmServices.getClosedUniversalAlarm(localName, startDate, endDate, pageable).map(UniversalAlarmsRepresentation::new);
        }
    }

    // end points for weather readings
    @GetMapping("/{localName}/weather-readings/latest")
    public List<WeatherReadingDto> getLatestWeatherReadingByLocal(@PathVariable String localName, @RequestParam(name="limit", required=false) Integer limit){

        if (limit == null) {
            return Collections.singletonList(weatherServices.getMostRecentWeatherReadingByLocal(localName));
        }

        return weatherServices.getWeatherReadingByLocalLimit(localName, limit);

    }

    @GetMapping("/{localName}/weather-readings")
    public List<WeatherReadingDto> getWeatherReadingByLocal(@PathVariable String localName,
                                                            @RequestParam(name="start_date", required=false)@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
                                                            @RequestParam(name="end_date", required=false)@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
        List<WeatherReadingDto> l;
        if (startDate==null ||endDate==null) {
            return weatherServices.getWeatherReadingsByLocal(localName);
        }
        else {
            return weatherServices.getWeatherReadingByLocalAndDate(localName, startDate, endDate);
        }
    }
}
