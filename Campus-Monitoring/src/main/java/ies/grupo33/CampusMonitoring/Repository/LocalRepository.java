package ies.grupo33.CampusMonitoring.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import ies.grupo33.CampusMonitoring.Model.Local;

public interface LocalRepository extends JpaRepository<Local, Long> {

	Optional<Local> findByName(String name);

}
