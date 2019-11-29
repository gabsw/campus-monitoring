package ies.grupo33.CampusMonitoring.Controller;

import ies.grupo33.CampusMonitoring.DTO.UniversalAlarmDTO;
import ies.grupo33.CampusMonitoring.Model.UniversalAlarm;
import ies.grupo33.CampusMonitoring.Services.UniversalAlarmServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("universal-alarm")
public class UniversalAlarmController {

    @Autowired
    private UniversalAlarmServices universalAlarmServices;

    @GetMapping("/all")
    public List<UniversalAlarmDTO> getUniversalAlarms() {

        return universalAlarmServices.getUniversalAlarmDTO();

    }

    @GetMapping("/open")
    public List<UniversalAlarm> getOpenUniversalAlarms() {

        return universalAlarmServices.getOpenUniversalAlarms();

    }
}
