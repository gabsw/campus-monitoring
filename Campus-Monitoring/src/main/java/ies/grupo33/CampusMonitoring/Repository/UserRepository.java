package ies.grupo33.CampusMonitoring.Repository;

import ies.grupo33.CampusMonitoring.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    List<User> findByAdmin(boolean admin);

    List<User> findByLocalsName(String localName);

    List<User> findByLocalsNameAndAdmin(String localName, boolean admin);

    Optional<User> findByLocalsNameAndUsername(String localName, String username);
}
