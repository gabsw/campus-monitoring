package ies.grupo33.CampusMonitoring.Repository;

import ies.grupo33.CampusMonitoring.DTO.UniversalAlarmDTO;
import ies.grupo33.CampusMonitoring.Model.UniversalAlarm;
import ies.grupo33.CampusMonitoring.Model.UniversalAlarmPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UniversalAlarmRepository extends JpaRepository<UniversalAlarm, UniversalAlarmPK> {

    @Query("SELECT new ies.grupo33.CampusMonitoring.DTO.UniversalAlarmDTO(" +
            "ua.universalAlarmPK.sensorId, " +
            "ua.universalAlarmPK.dateTime, " +
            "ua.universalAlarmPK.violationParameter, " +
            "ua.violationType, " +
            "ua.violationValue, " +
            "ua.status, " +
            "se.localName) " +
            "FROM UniversalAlarm AS ua " +
            "INNER JOIN Sensor AS se " +
            "ON ua.universalAlarmPK.sensorId = se.id " +
            "WHERE se.localName = ?1")
    List<UniversalAlarmDTO> fetchUniversalAlarmDTOByLocal(String localName);


    @Query("SELECT new ies.grupo33.CampusMonitoring.DTO.UniversalAlarmDTO(" +
            "ua.universalAlarmPK.sensorId, " +
            "ua.universalAlarmPK.dateTime, " +
            "ua.universalAlarmPK.violationParameter, " +
            "ua.violationType, " +
            "ua.violationValue, " +
            "ua.status, " +
            "se.localName) " +
            "FROM UniversalAlarm AS ua " +
            "INNER JOIN Sensor AS se " +
            "ON ua.universalAlarmPK.sensorId = se.id " +
            "WHERE ua.status = TRUE " +
            "AND se.localName = ?1")
    List<UniversalAlarmDTO> fetchOpenUniversalAlarmDTOByLocal(String localName);
}
