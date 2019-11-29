package ies.grupo33.CampusMonitoring.Services;

import ies.grupo33.CampusMonitoring.DTO.UniversalAlarmDTO;
import ies.grupo33.CampusMonitoring.Repository.UniversalAlarmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class UniversalAlarmServices {

    @Autowired
    private UniversalAlarmRepository universalAlarmRepository;

    public List<UniversalAlarmDTO> getUniversalAlarmDTO(String localName) {
        if (localName == null) {
            throw new IllegalArgumentException("Local name is not defined.");
        } else {

            return universalAlarmRepository.fetchUniversalAlarmDTOByLocal(localName);
        }
    }

    public List<UniversalAlarmDTO> getUniversalAlarmDTO(String localName, LocalDate timeStart, LocalDate timeEnd) {
        if (timeStart == null || timeEnd == null) {
            throw new IllegalArgumentException("Time range is not defined.");
        } else if (localName == null) {
            throw new IllegalArgumentException("Local name is not defined.");
        } else {
            return universalAlarmRepository.fetchUniversalAlarmDTOByLocalAndTimeRange(localName, timeStart, timeEnd);
        }
    }


    // Add pagination

    // Retrieve all open alarms
    public List<UniversalAlarmDTO> getOpenUniversalAlarmDTO(String localName) {
        if (localName == null) {
            throw new IllegalArgumentException("Local name is not defined.");
        } else {

            return universalAlarmRepository.fetchUniversalAlarmDTOByLocalAndStatus(localName, true);
        }
    }

    public List<UniversalAlarmDTO> getOpenUniversalAlarmDTO(String localName, LocalDate timeStart, LocalDate timeEnd) {
        if (timeStart == null || timeEnd == null) {
            throw new IllegalArgumentException("Time range is not defined.");
        } else if (localName == null) {
            throw new IllegalArgumentException("Local name is not defined.");
        } else {
            return universalAlarmRepository.fetchOpenUniversalAlarmDTOByLocalAndTimeRangeAndStatus(localName, timeStart, timeEnd, true);
        }
    }

    // Retrieve all closed alarms
    public List<UniversalAlarmDTO> getClosedUniversalAlarmDTO(String localName) {
        if (localName == null) {
            throw new IllegalArgumentException("Local name is not defined.");
        } else {

            return universalAlarmRepository.fetchUniversalAlarmDTOByLocalAndStatus(localName, false);
        }
    }

    public List<UniversalAlarmDTO> getClosedUniversalAlarmDTO(String localName, LocalDate timeStart,
                                                              LocalDate timeEnd) {
        if (timeStart == null || timeEnd == null) {
            throw new IllegalArgumentException("Time range is not defined.");
        } else if (localName == null) {
            throw new IllegalArgumentException("Local name is not defined.");
        } else {
            return universalAlarmRepository.fetchOpenUniversalAlarmDTOByLocalAndTimeRangeAndStatus(localName, timeStart, timeEnd, false);
        }
    }

}
