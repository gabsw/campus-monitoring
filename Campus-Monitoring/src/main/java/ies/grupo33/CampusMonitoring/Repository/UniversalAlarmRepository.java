package ies.grupo33.CampusMonitoring.Repository;

import ies.grupo33.CampusMonitoring.Model.UniversalAlarm;
import ies.grupo33.CampusMonitoring.Model.UniversalAlarmPK;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface UniversalAlarmRepository extends JpaRepository<UniversalAlarm, UniversalAlarmPK> {

    Page<UniversalAlarm> findByUniversalAlarmPKLocalName(String localName, Pageable pageable);

    Page<UniversalAlarm> findByUniversalAlarmPKLocalNameAndUniversalAlarmPKStartDateTimeBetween(String localName,
                                                                                                LocalDate timeStart,
                                                                                                LocalDate timeEnd,
                                                                                                Pageable pageable);

    Page<UniversalAlarm> findByUniversalAlarmPKLocalNameAndOngoingStatus(String localName, boolean ongoingStatus, Pageable pageable);

    Page<UniversalAlarm> findByUniversalAlarmPKLocalNameAndUniversalAlarmPKStartDateTimeBetweenAndOngoingStatus(String localName,
                                                                                                                LocalDate timeStart,
                                                                                                                LocalDate timeEnd,
                                                                                                                boolean ongoingStatus,
                                                                                                                Pageable pageable);
}
