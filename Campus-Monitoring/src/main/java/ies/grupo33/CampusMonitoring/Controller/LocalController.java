package ies.grupo33.CampusMonitoring.Controller;

import ies.grupo33.CampusMonitoring.DTO.ReportDTO;
import ies.grupo33.CampusMonitoring.DTO.WeatherReadingDto;
import ies.grupo33.CampusMonitoring.Exception.*;
import ies.grupo33.CampusMonitoring.Model.Local;
import ies.grupo33.CampusMonitoring.Model.Review;
import ies.grupo33.CampusMonitoring.Representations.UniversalAlarmsRepresentation;
import ies.grupo33.CampusMonitoring.Services.*;
import ies.grupo33.CampusMonitoring.security.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public List<Local> getLocals(HttpServletRequest request) throws LoginRequiredException {
        SecurityUtils.getUserIdentity(request.getSession());
        return localServices.getAllLocals();
    }

    // end points dealing with reviews
    @GetMapping("/{localName}/reviews")
    public Page<Review> getLocalReviews(@PathVariable String localName, Pageable pageable, HttpServletRequest request)
            throws ForbiddenUserException, LocalNotFoundException, UserNotFoundException, LoginRequiredException {
        String username = SecurityUtils.getUserIdentity(request.getSession());

        return reviewServices.getReviewByLocal(localName, pageable, username);
    }

    @PostMapping("/{localName}/reviews")
    public ResponseEntity<Review> addReviewToLocal(@PathVariable String localName, @Valid @RequestBody Review review, HttpServletRequest request)
            throws ForbiddenUserException, LocalNotFoundException, UserNotFoundException, LoginRequiredException,
            MismatchedReviewException {
        String username = SecurityUtils.getUserIdentity(request.getSession());
        Review persistedReview = reviewServices.addReviewToLocal(localName, review, username);
        return new ResponseEntity<>(persistedReview, HttpStatus.CREATED);
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
        String username = SecurityUtils.getUserIdentity(request.getSession());
        return reportServices.buildReport(localName, startDate, endDate, username);
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
        String username = SecurityUtils.getUserIdentity(request.getSession());

        if (startDate == null || endDate == null) {
            return universalAlarmServices.getUniversalAlarm(localName, pageable, username).map(UniversalAlarmsRepresentation::new);
        } else {
            return universalAlarmServices.getUniversalAlarm(localName, startDate, endDate, pageable, username).map(UniversalAlarmsRepresentation::new);
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
        String username = SecurityUtils.getUserIdentity(request.getSession());
        if (startDate == null || endDate == null) {
            return universalAlarmServices.getOpenUniversalAlarm(localName, pageable, username).map(UniversalAlarmsRepresentation::new);
        } else {
            return universalAlarmServices.getOpenUniversalAlarm(localName, startDate, endDate, pageable, username).map(UniversalAlarmsRepresentation::new);
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
        String username = SecurityUtils.getUserIdentity(request.getSession());
        if (startDate == null || endDate == null) {
            return universalAlarmServices.getClosedUniversalAlarm(localName, pageable, username).map(UniversalAlarmsRepresentation::new);
        } else {
            return universalAlarmServices.getClosedUniversalAlarm(localName, startDate, endDate, pageable, username).map(UniversalAlarmsRepresentation::new);
        }
    }

    // end points for weather readings
    @GetMapping("/{localName}/weather-readings/latest")
    public List<WeatherReadingDto> getLatestWeatherReadingByLocal(@PathVariable String localName, @RequestParam(name="limit", required=false) Integer limit, HttpServletRequest request)
            throws ForbiddenUserException, LocalNotFoundException, UserNotFoundException, LoginRequiredException, WeatherReadingNotFoundException {
        String username = SecurityUtils.getUserIdentity(request.getSession());
        if (limit == null) {
            return Collections.singletonList(weatherServices.getMostRecentWeatherReadingByLocal(localName, username));
        }

        return weatherServices.getWeatherReadingByLocalLimit(localName, limit, username);

    }

    @GetMapping("/{localName}/weather-readings")
    public List<WeatherReadingDto> getWeatherReadingByLocal(@PathVariable String localName,
                                                            @RequestParam(name="start_date", required=false)@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
                                                            @RequestParam(name="end_date", required=false)@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate, HttpServletRequest request)
            throws ForbiddenUserException, LocalNotFoundException, UserNotFoundException, LoginRequiredException {
        String username = SecurityUtils.getUserIdentity(request.getSession());
        if (startDate==null ||endDate==null) {
            return weatherServices.getWeatherReadingsByLocal(localName, username);
        }
        else {
            return weatherServices.getWeatherReadingByLocalAndDate(localName, startDate, endDate, username);
        }
    }
}
