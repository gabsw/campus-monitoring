package ies.grupo33.CampusMonitoring.Services;

import ies.grupo33.CampusMonitoring.Model.User;
import ies.grupo33.CampusMonitoring.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServices {

    @Autowired
    private UserRepository userRepository;

    public List<User> getUsersByLocal(String local) {
        if (local == null) {
            throw new IllegalArgumentException("Local is not defined.");
        } else {
            return userRepository.findByLocalsName(local);
        }
    }

    public List<User> getUsersByAdminStatus(boolean isAdmin) {
        return userRepository.findByAdmin(isAdmin);
    }

    public List<String> retrieveEmailsForAlarmNotification(String local) {
        if (local == null) {
            throw new IllegalArgumentException("Local is not defined.");
        } else {

            List<User> usersByLocal = getUsersByLocal(local);

            return usersByLocal.stream()
                    .map(User::getEmail)
                    .collect(Collectors.toList());
        }
    }

    public List<User> getAdminByLocal(String local) {

        if (local == null) {
            throw new IllegalArgumentException("Local is not defined.");
        } else {
            return userRepository.findByLocalsNameAndAdmin(local, true);
        }

    }

    public List<User> getRegularUsersByLocal(String local) {

        if (local == null) {
            throw new IllegalArgumentException("Local is not defined.");
        } else {
            return userRepository.findByLocalsNameAndAdmin(local, false);
        }

    }
}
