package ies.grupo33.CampusMonitoring.Controller;

import ies.grupo33.CampusMonitoring.DTO.ReportDTO;
import ies.grupo33.CampusMonitoring.DTO.WeatherReadingDto;
import ies.grupo33.CampusMonitoring.Exception.ForbiddenUserException;
import ies.grupo33.CampusMonitoring.Exception.LocalNotFoundException;
import ies.grupo33.CampusMonitoring.Exception.LoginRequiredException;
import ies.grupo33.CampusMonitoring.Exception.MismatchedReviewException;
import ies.grupo33.CampusMonitoring.Exception.UserCannotReviewException;
import ies.grupo33.CampusMonitoring.Exception.UserNotFoundException;
import ies.grupo33.CampusMonitoring.Exception.WeatherReadingNotFoundException;
import ies.grupo33.CampusMonitoring.Model.Local;
import ies.grupo33.CampusMonitoring.Model.Review;
import ies.grupo33.CampusMonitoring.Representations.UniversalAlarmsRepresentation;

import ies.grupo33.CampusMonitoring.Services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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
    public Page<Review> getLocalReviews(@PathVariable String localName, Pageable pageable, HttpServletRequest request)
            throws ForbiddenUserException, LocalNotFoundException, UserNotFoundException, LoginRequiredException {
        return reviewServices.getReviewByLocal(localName, pageable, request.getSession());
    }

    @PostMapping("/{localName}/reviews")
    public void addReviewToLocal(@PathVariable String localName, @Valid @RequestBody Review review, HttpServletRequest request)
            throws ForbiddenUserException, LocalNotFoundException, UserNotFoundException, LoginRequiredException,
            MismatchedReviewException {
        reviewServices.addReviewToLocal(localName, review, request.getSession());
    }

    // end points for reports
    @GetMapping("/{localName}/report")
    public ReportDTO getReport(@PathVariable String localName,
                               @RequestParam(name = "start_date")
                               @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                       LocalDate startDate,
                               @RequestParam(name = "end_date")
                               @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                       LocalDate endDate, HttpServletRequest request)
            throws ForbiddenUserException, LocalNotFoundException, UserNotFoundException, LoginRequiredException {
        return reportServices.buildReport(localName, startDate, endDate, request.getSession());
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
                                                                  Pageable pageable, HttpServletRequest request)
            throws ForbiddenUserException, LocalNotFoundException, UserNotFoundException, LoginRequiredException {

        if (startDate == null || endDate == null) {
            return universalAlarmServices.getUniversalAlarm(localName, pageable, request.getSession()).map(UniversalAlarmsRepresentation::new);
        } else {
            return universalAlarmServices.getUniversalAlarm(localName, startDate, endDate, pageable, request.getSession()).map(UniversalAlarmsRepresentation::new);
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
                                                       Pageable pageable, HttpServletRequest request)
            throws ForbiddenUserException, LocalNotFoundException, UserNotFoundException, LoginRequiredException {

        if (startDate == null || endDate == null) {
            return universalAlarmServices.getOpenUniversalAlarm(localName, pageable, request.getSession()).map(UniversalAlarmsRepresentation::new);
        } else {
            return universalAlarmServices.getOpenUniversalAlarm(localName, startDate, endDate, pageable, request.getSession()).map(UniversalAlarmsRepresentation::new);
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
                                                         Pageable pageable, HttpServletRequest request)
            throws ForbiddenUserException, LocalNotFoundException, UserNotFoundException, LoginRequiredException {

        if (startDate == null || endDate == null) {
            return universalAlarmServices.getClosedUniversalAlarm(localName, pageable, request.getSession()).map(UniversalAlarmsRepresentation::new);
        } else {
            return universalAlarmServices.getClosedUniversalAlarm(localName, startDate, endDate, pageable, request.getSession()).map(UniversalAlarmsRepresentation::new);
        }
    }

    // end points for weather readings
    @GetMapping("/{localName}/weather-readings/latest")
    public List<WeatherReadingDto> getLatestWeatherReadingByLocal(@PathVariable String localName, @RequestParam(name="limit", required=false) Integer limit, HttpServletRequest request)
            throws ForbiddenUserException, LocalNotFoundException, UserNotFoundException, LoginRequiredException, WeatherReadingNotFoundException {

        if (limit == null) {
            return Collections.singletonList(weatherServices.getMostRecentWeatherReadingByLocal(localName, request.getSession()));
        }

        return weatherServices.getWeatherReadingByLocalLimit(localName, limit, request.getSession());

    }

    @GetMapping("/{localName}/weather-readings")
    public List<WeatherReadingDto> getWeatherReadingByLocal(@PathVariable String localName,
                                                            @RequestParam(name="start_date", required=false)@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
                                                            @RequestParam(name="end_date", required=false)@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate, HttpServletRequest request)
            throws ForbiddenUserException, LocalNotFoundException, UserNotFoundException, LoginRequiredException {
        if (startDate==null ||endDate==null) {
            return weatherServices.getWeatherReadingsByLocal(localName, request.getSession());
        }
        else {
            return weatherServices.getWeatherReadingByLocalAndDate(localName, startDate, endDate, request.getSession());
        }
    }
}
