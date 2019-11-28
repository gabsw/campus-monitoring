package ies.grupo33.CampusMonitoring.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import ies.grupo33.CampusMonitoring.Model.Alarm;
import ies.grupo33.CampusMonitoring.Model.AlarmPK;

public interface AlarmeRepository  extends JpaRepository<Alarm, AlarmPK>{

	List<Alarm> findByAlarmPKSensorId(long sensorId);

}
