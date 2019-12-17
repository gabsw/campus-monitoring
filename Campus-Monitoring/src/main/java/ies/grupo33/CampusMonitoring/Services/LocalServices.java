package ies.grupo33.CampusMonitoring.Services;

import ies.grupo33.CampusMonitoring.Exception.UserNotFoundException;
import ies.grupo33.CampusMonitoring.Model.Local;
import ies.grupo33.CampusMonitoring.Model.User;
import ies.grupo33.CampusMonitoring.Repository.LocalRepository;
import ies.grupo33.CampusMonitoring.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocalServices {

    @Autowired
    private LocalRepository localRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Local> getAllLocals() {
        return localRepository.findAll();
    }

    public List<Local> getLocalsByUser(String username) throws UserNotFoundException {
        if (username == null) {
            throw new IllegalArgumentException("Username is not defined.");
        }

        Optional<User> opt_user = userRepository.findById(username);
        if (!opt_user.isPresent()) {
            throw new UserNotFoundException("User not found " + username);
        }

        return localRepository.findByUsername(username);

    }


}
