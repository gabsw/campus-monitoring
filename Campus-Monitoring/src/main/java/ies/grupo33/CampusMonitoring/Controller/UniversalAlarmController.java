package ies.grupo33.CampusMonitoring.Controller;

import ies.grupo33.CampusMonitoring.DTO.UniversalAlarmDTO;
import ies.grupo33.CampusMonitoring.Services.UniversalAlarmServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("universal-alarm")
public class UniversalAlarmController {
    @Autowired
    private UniversalAlarmServices universalAlarmServices;

    @GetMapping("/local-name/{localName}/all")
    public Page<UniversalAlarmDTO> getUniversalAlarms(@PathVariable String localName,
                                                      @RequestParam(name = "start_date", required = false)
                                                      @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                              LocalDate startDate,
                                                      @RequestParam(name = "end_date", required = false)
                                                      @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                              LocalDate endDate,
                                                      Pageable pageable) {

        if (startDate == null || endDate == null) {
            return universalAlarmServices.getUniversalAlarmDTO(localName, pageable);
        } else {
            return universalAlarmServices.getUniversalAlarmDTO(localName, startDate, endDate, pageable);
        }

    }

    @GetMapping("/local-name/{localName}/open")
    public Page<UniversalAlarmDTO> getOpenUniversalAlarms(@PathVariable String localName,
                                                          @RequestParam(name = "start_date", required = false)
                                                          @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                                  LocalDate startDate,
                                                          @RequestParam(name = "end_date", required = false)
                                                          @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                                  LocalDate endDate,
                                                          Pageable pageable) {

        if (startDate == null || endDate == null) {
            return universalAlarmServices.getOpenUniversalAlarmDTO(localName, pageable);
        } else {
            return universalAlarmServices.getOpenUniversalAlarmDTO(localName, startDate, endDate, pageable);
        }
    }

    @GetMapping("/local-name/{localName}/closed")
    public Page<UniversalAlarmDTO> getClosedUniversalAlarms(@PathVariable String localName,
                                                          @RequestParam(name = "start_date", required = false)
                                                          @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                                  LocalDate startDate,
                                                          @RequestParam(name = "end_date", required = false)
                                                          @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                                  LocalDate endDate,
                                                            Pageable pageable) {

        if (startDate == null || endDate == null) {
            return universalAlarmServices.getClosedUniversalAlarmDTO(localName, pageable);
        } else {
            return universalAlarmServices.getClosedUniversalAlarmDTO(localName, startDate, endDate, pageable);
        }
    }
}