package ies.grupo33.CampusMonitoring.Controller;

import ies.grupo33.CampusMonitoring.DTO.UniversalAlarmDTO;
import ies.grupo33.CampusMonitoring.Services.UniversalAlarmServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("universal-alarm")
public class UniversalAlarmController {

    @Autowired
    private UniversalAlarmServices universalAlarmServices;

    @GetMapping("/all/{localName}")
    public List<UniversalAlarmDTO> getUniversalAlarms(@PathVariable String localName) {

        return universalAlarmServices.getUniversalAlarmDTO(localName);

    }
    
    @GetMapping("/open/{localName}")
    public List<UniversalAlarmDTO> getOpenUniversalAlarms(@PathVariable String localName) {

        return universalAlarmServices.getOpenUniversalAlarmDTO(localName);

    }
}
