package ies.grupo33.CampusMonitoring.Services;

import ies.grupo33.CampusMonitoring.DTO.UniversalAlarmDTO;
import ies.grupo33.CampusMonitoring.Model.UniversalAlarm;
import ies.grupo33.CampusMonitoring.Repository.UniversalAlarmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UniversalAlarmServices {

    @Autowired
    private UniversalAlarmRepository universalAlarmRepository;

    public List<UniversalAlarmDTO> getUniversalAlarmDTO() {

        return universalAlarmRepository.fetchUniversalAlarmDTO();
    }


    // Add pagination

    // Retrieve all open alarms
    public List<UniversalAlarm> getOpenUniversalAlarms() {

        return universalAlarmRepository.findByStatusTrue();
    }
}
