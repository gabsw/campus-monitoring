package ies.grupo33.CampusMonitoring.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ies.grupo33.CampusMonitoring.Model.Sensor;

public interface SensorRepository extends JpaRepository<Sensor, Long>{
}
