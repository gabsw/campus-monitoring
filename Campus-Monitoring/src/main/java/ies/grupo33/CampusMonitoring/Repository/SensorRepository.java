package ies.grupo33.CampusMonitoring.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ies.grupo33.CampusMonitoring.Model.Sensor;
import org.springframework.stereotype.Repository;

@Repository
public interface SensorRepository extends JpaRepository<Sensor, Long>{
}
