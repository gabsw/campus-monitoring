package ies.grupo33.CampusMonitoring.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ies.grupo33.CampusMonitoring.Model.Local;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocalRepository extends JpaRepository<Local, String> {

	List<Local> findByUsername(String username);

}
