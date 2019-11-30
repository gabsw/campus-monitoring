package ies.grupo33.CampusMonitoring.Services;

import ies.grupo33.CampusMonitoring.DTO.UniversalAlarmDTO;
import ies.grupo33.CampusMonitoring.Repository.UniversalAlarmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class UniversalAlarmServices {

    @Autowired
    private UniversalAlarmRepository universalAlarmRepository;

    public Page<UniversalAlarmDTO> getUniversalAlarmDTO(String localName, Pageable pageable) {
        if (localName == null) {
            throw new IllegalArgumentException("Local name is not defined.");
        } else {
            return universalAlarmRepository.fetchUniversalAlarmDTOByLocal(localName, pageable);
        }
    }

    public Page<UniversalAlarmDTO> getUniversalAlarmDTO(String localName, LocalDate timeStart, LocalDate timeEnd, Pageable pageable) {
        if (timeStart == null || timeEnd == null) {
            throw new IllegalArgumentException("Time range is not defined.");
        } else if (localName == null) {
            throw new IllegalArgumentException("Local name is not defined.");
        } else {
            return universalAlarmRepository.fetchUniversalAlarmDTOByLocalAndTimeRange(localName, timeStart, timeEnd, pageable);
        }
    }


    // Add pagination

    // Retrieve all open alarms
    public Page<UniversalAlarmDTO> getOpenUniversalAlarmDTO(String localName, Pageable pageable) {
        if (localName == null) {
            throw new IllegalArgumentException("Local name is not defined.");
        } else {

            return universalAlarmRepository.fetchUniversalAlarmDTOByLocalAndStatus(localName, true, pageable);
        }
    }

    public Page<UniversalAlarmDTO> getOpenUniversalAlarmDTO(String localName, LocalDate timeStart, LocalDate timeEnd, Pageable pageable) {
        if (timeStart == null || timeEnd == null) {
            throw new IllegalArgumentException("Time range is not defined.");
        } else if (localName == null) {
            throw new IllegalArgumentException("Local name is not defined.");
        } else {
            return universalAlarmRepository.fetchOpenUniversalAlarmDTOByLocalAndTimeRangeAndStatus(localName, timeStart,
                                                                                                    timeEnd, true,
                                                                                                    pageable);
        }
    }

    // Retrieve all closed alarms
    public Page<UniversalAlarmDTO> getClosedUniversalAlarmDTO(String localName, Pageable pageable) {
        if (localName == null) {
            throw new IllegalArgumentException("Local name is not defined.");
        } else {

            return universalAlarmRepository.fetchUniversalAlarmDTOByLocalAndStatus(localName, false, pageable);
        }
    }

    public Page<UniversalAlarmDTO> getClosedUniversalAlarmDTO(String localName, LocalDate timeStart,
                                                              LocalDate timeEnd, Pageable pageable) {
        if (timeStart == null || timeEnd == null) {
            throw new IllegalArgumentException("Time range is not defined.");
        } else if (localName == null) {
            throw new IllegalArgumentException("Local name is not defined.");
        } else {
            return universalAlarmRepository.fetchOpenUniversalAlarmDTOByLocalAndTimeRangeAndStatus(localName, timeStart, timeEnd, false, pageable);
        }
    }

}
