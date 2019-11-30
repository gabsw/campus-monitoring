package ies.grupo33.CampusMonitoring.Repository;

import ies.grupo33.CampusMonitoring.DTO.UniversalAlarmDTO;
import ies.grupo33.CampusMonitoring.Model.UniversalAlarm;
import ies.grupo33.CampusMonitoring.Model.UniversalAlarmPK;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface UniversalAlarmRepository extends JpaRepository<UniversalAlarm, UniversalAlarmPK> {

    @Query("SELECT new ies.grupo33.CampusMonitoring.DTO.UniversalAlarmDTO(" +
            "ua.universalAlarmPK.sensorId, " +
            "ua.universalAlarmPK.startDateTime, " +
            "ua.universalAlarmPK.violationParameter, " +
            "ua.violationType, " +
            "ua.violationValue, " +
            "ua.status, " +
            "ua.endDateTime, " +
            "se.localName) " +
            "FROM UniversalAlarm AS ua " +
            "INNER JOIN Sensor AS se " +
            "ON ua.universalAlarmPK.sensorId = se.id " +
            "WHERE se.localName = ?1")
    Page<UniversalAlarmDTO> fetchUniversalAlarmDTOByLocal(String localName, Pageable pageable);


    @Query("SELECT new ies.grupo33.CampusMonitoring.DTO.UniversalAlarmDTO(" +
            "ua.universalAlarmPK.sensorId, " +
            "ua.universalAlarmPK.startDateTime, " +
            "ua.universalAlarmPK.violationParameter, " +
            "ua.violationType, " +
            "ua.violationValue, " +
            "ua.status, " +
            "ua.endDateTime, " +
            "se.localName) " +
            "FROM UniversalAlarm AS ua " +
            "INNER JOIN Sensor AS se " +
            "ON ua.universalAlarmPK.sensorId = se.id " +
            "WHERE se.localName = ?1 " +
            "AND ua.universalAlarmPK.startDateTime BETWEEN " +
            "?2 AND ?3")
    Page<UniversalAlarmDTO> fetchUniversalAlarmDTOByLocalAndTimeRange(String localName,
                                                                      LocalDate timeStart,
                                                                      LocalDate timeEnd,
                                                                      Pageable pageable);


    @Query("SELECT new ies.grupo33.CampusMonitoring.DTO.UniversalAlarmDTO(" +
            "ua.universalAlarmPK.sensorId, " +
            "ua.universalAlarmPK.startDateTime, " +
            "ua.universalAlarmPK.violationParameter, " +
            "ua.violationType, " +
            "ua.violationValue, " +
            "ua.status, " +
            "ua.endDateTime, " +
            "se.localName) " +
            "FROM UniversalAlarm AS ua " +
            "INNER JOIN Sensor AS se " +
            "ON ua.universalAlarmPK.sensorId = se.id " +
            "WHERE ua.status = ?2 " +
            "AND se.localName = ?1")
    Page<UniversalAlarmDTO> fetchUniversalAlarmDTOByLocalAndStatus(String localName, Boolean status, Pageable pageable);


    @Query("SELECT new ies.grupo33.CampusMonitoring.DTO.UniversalAlarmDTO(" +
            "ua.universalAlarmPK.sensorId, " +
            "ua.universalAlarmPK.startDateTime, " +
            "ua.universalAlarmPK.violationParameter, " +
            "ua.violationType, " +
            "ua.violationValue, " +
            "ua.status, " +
            "ua.endDateTime, " +
            "se.localName) " +
            "FROM UniversalAlarm AS ua " +
            "INNER JOIN Sensor AS se " +
            "ON ua.universalAlarmPK.sensorId = se.id " +
            "WHERE ua.status = ?4 " +
            "AND se.localName = ?1" +
            "AND ua.universalAlarmPK.startDateTime BETWEEN " +
            "?2 AND ?3")
    Page<UniversalAlarmDTO> fetchOpenUniversalAlarmDTOByLocalAndTimeRangeAndStatus(String localName,
                                                                          LocalDate timeStart,
                                                                          LocalDate timeEnd,
                                                                          Boolean status,
                                                                                   Pageable pageable);
}
