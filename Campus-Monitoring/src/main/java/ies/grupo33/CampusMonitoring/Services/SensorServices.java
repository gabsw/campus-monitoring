package ies.grupo33.CampusMonitoring.Services;

import ies.grupo33.CampusMonitoring.Exception.ForbiddenUserException;
import ies.grupo33.CampusMonitoring.Exception.SensorNotFoundException;
import ies.grupo33.CampusMonitoring.Exception.UserNotFoundException;
import ies.grupo33.CampusMonitoring.Model.Sensor;
import ies.grupo33.CampusMonitoring.Model.User;
import ies.grupo33.CampusMonitoring.Repository.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Transactional
@Service
public class SensorServices {

    @Autowired
    private SensorRepository sensorRepository;

    @Autowired
    private UserServices userServices;

    public List<Sensor> getSensors(String username) throws ForbiddenUserException, UserNotFoundException {
        User currentUser = userServices.findUserByUsername(username);

        userServices.checkIfUserIsAdmin(currentUser);
        return sensorRepository.findAll();
    }

    public Sensor getSensor(Long id, String username) throws SensorNotFoundException, ForbiddenUserException, UserNotFoundException {

        User currentUser = userServices.findUserByUsername(username);

        userServices.checkIfUserIsAdmin(currentUser);

        Optional<Sensor> sensor = sensorRepository.findById(id);

        if (sensor.isPresent()) {
            return sensor.get();
        } else {
            throw new SensorNotFoundException("Sensor not found " + id);
        }
    }

}
