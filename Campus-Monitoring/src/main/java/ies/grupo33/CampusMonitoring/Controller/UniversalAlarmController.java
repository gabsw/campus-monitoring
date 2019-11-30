package ies.grupo33.CampusMonitoring.Controller;

import ies.grupo33.CampusMonitoring.DTO.UniversalAlarmDTO;
import ies.grupo33.CampusMonitoring.Services.UniversalAlarmServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("universal-alarm")
public class UniversalAlarmController {

    @Autowired
    private UniversalAlarmServices universalAlarmServices;

    @GetMapping("/local-name/{localName}/all")
    public List<UniversalAlarmDTO> getUniversalAlarms(@PathVariable String localName,
                                                      @RequestParam(name = "start_date", required = false)
                                                      @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                              LocalDate startDate,
                                                      @RequestParam(name = "end_date", required = false)
                                                      @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                              LocalDate endDate) {

        if (startDate == null || endDate == null) {
            return universalAlarmServices.getUniversalAlarmDTO(localName);
        } else {
            return universalAlarmServices.getUniversalAlarmDTO(localName, startDate, endDate);
        }

    }

    @GetMapping("/local-name/{localName}/open")
    public List<UniversalAlarmDTO> getOpenUniversalAlarms(@PathVariable String localName,
                                                          @RequestParam(name = "start_date", required = false)
                                                          @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                                  LocalDate startDate,
                                                          @RequestParam(name = "end_date", required = false)
                                                          @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                                  LocalDate endDate) {

        if (startDate == null || endDate == null) {
            return universalAlarmServices.getOpenUniversalAlarmDTO(localName);
        } else {
            return universalAlarmServices.getOpenUniversalAlarmDTO(localName, startDate, endDate);
        }
    }

    @GetMapping("/local-name/{localName}/closed")
    public List<UniversalAlarmDTO> getClosedUniversalAlarms(@PathVariable String localName,
                                                          @RequestParam(name = "start_date", required = false)
                                                          @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                                  LocalDate startDate,
                                                          @RequestParam(name = "end_date", required = false)
                                                          @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                                  LocalDate endDate) {

        if (startDate == null || endDate == null) {
            return universalAlarmServices.getClosedUniversalAlarmDTO(localName);
        } else {
            return universalAlarmServices.getClosedUniversalAlarmDTO(localName, startDate, endDate);
        }
    }
}