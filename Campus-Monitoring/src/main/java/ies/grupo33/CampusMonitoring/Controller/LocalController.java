package ies.grupo33.CampusMonitoring.Controller;

import ies.grupo33.CampusMonitoring.DTO.ReportDTO;
import ies.grupo33.CampusMonitoring.Exception.MismatchedReviewException;
import ies.grupo33.CampusMonitoring.Exception.UserCannotReviewException;
import ies.grupo33.CampusMonitoring.Model.Local;
import ies.grupo33.CampusMonitoring.Model.Review;
import ies.grupo33.CampusMonitoring.Model.UniversalAlarm;
import ies.grupo33.CampusMonitoring.Services.LocalServices;
import ies.grupo33.CampusMonitoring.Services.ReportServices;
import ies.grupo33.CampusMonitoring.Services.ReviewServices;
import ies.grupo33.CampusMonitoring.Services.UniversalAlarmServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;


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
    public Page<UniversalAlarm> getUniversalAlarms(@PathVariable String localName,
                                                   @RequestParam(name = "start_date", required = false)
                                                   @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                           LocalDate startDate,
                                                   @RequestParam(name = "end_date", required = false)
                                                   @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                           LocalDate endDate,
                                                   Pageable pageable) {

        if (startDate == null || endDate == null) {
            return universalAlarmServices.getUniversalAlarm(localName, pageable);
        } else {
            return universalAlarmServices.getUniversalAlarm(localName, startDate, endDate, pageable);
        }

    }

    @GetMapping("/{localName}/alarms/ongoing")
    public Page<UniversalAlarm> getOpenUniversalAlarms(@PathVariable String localName,
                                                       @RequestParam(name = "start_date", required = false)
                                                       @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                               LocalDate startDate,
                                                       @RequestParam(name = "end_date", required = false)
                                                       @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                               LocalDate endDate,
                                                       Pageable pageable) {

        if (startDate == null || endDate == null) {
            return universalAlarmServices.getOpenUniversalAlarm(localName, pageable);
        } else {
            return universalAlarmServices.getOpenUniversalAlarm(localName, startDate, endDate, pageable);
        }
    }

    @GetMapping("/{localName}/alarms/closed")
    public Page<UniversalAlarm> getClosedUniversalAlarms(@PathVariable String localName,
                                                         @RequestParam(name = "start_date", required = false)
                                                         @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                                 LocalDate startDate,
                                                         @RequestParam(name = "end_date", required = false)
                                                         @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                                 LocalDate endDate,
                                                         Pageable pageable) {

        if (startDate == null || endDate == null) {
            return universalAlarmServices.getClosedUniversalAlarm(localName, pageable);
        } else {
            return universalAlarmServices.getClosedUniversalAlarm(localName, startDate, endDate, pageable);
        }
    }
}
