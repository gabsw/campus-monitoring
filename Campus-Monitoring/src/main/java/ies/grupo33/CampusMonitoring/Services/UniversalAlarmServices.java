package ies.grupo33.CampusMonitoring.Services;

import ies.grupo33.CampusMonitoring.DTO.UniversalAlarmDTO;
import ies.grupo33.CampusMonitoring.Repository.UniversalAlarmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UniversalAlarmServices {

    @Autowired
    private UniversalAlarmRepository universalAlarmRepository;

    public List<UniversalAlarmDTO> getUniversalAlarmDTO(String localName) {

        return universalAlarmRepository.fetchUniversalAlarmDTOByLocal(localName);
    }


    // Add pagination

    // Retrieve all open alarms
    public List<UniversalAlarmDTO> getOpenUniversalAlarmDTO(String localName) {

        return universalAlarmRepository.fetchOpenUniversalAlarmDTOByLocal(localName);
    }
}
