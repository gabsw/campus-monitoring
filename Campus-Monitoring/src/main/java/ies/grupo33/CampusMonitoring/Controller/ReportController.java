package ies.grupo33.CampusMonitoring.Controller;


import ies.grupo33.CampusMonitoring.DTO.ReportDTO;
import ies.grupo33.CampusMonitoring.Services.ReportServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("report")
public class ReportController {
    @Autowired
    private ReportServices reportServices;

    @GetMapping("/local-name/{localName}")
    public ReportDTO getReport(@PathVariable String localName,
                               @RequestParam(name = "start_date")
                               @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                       LocalDate startDate,
                               @RequestParam(name = "end_date")
                               @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                       LocalDate endDate) {
        return reportServices.buildReport(localName, startDate, endDate);
    }
}
