package ies.grupo33.CampusMonitoring.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ies.grupo33.CampusMonitoring.Model.Alarm;

public interface AlarmeRepository  extends JpaRepository<Alarm, Long>{

}
