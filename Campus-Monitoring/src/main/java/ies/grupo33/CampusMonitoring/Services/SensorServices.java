package ies.grupo33.CampusMonitoring.Services;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ies.grupo33.CampusMonitoring.Exception.ForbiddenUserException;
import ies.grupo33.CampusMonitoring.Exception.LoginRequiredException;
import ies.grupo33.CampusMonitoring.Exception.SensorNotFoundException;
import ies.grupo33.CampusMonitoring.Exception.UserNotFoundException;
import ies.grupo33.CampusMonitoring.Model.Sensor;
import ies.grupo33.CampusMonitoring.Model.User;
import ies.grupo33.CampusMonitoring.Repository.SensorRepository;


@Service
public class SensorServices {
	
	@Autowired
	private SensorRepository sensorRepository;
	
	@Autowired
    private UserServices userServices;
	
	public List<Sensor> getSensors(HttpSession session) throws UserNotFoundException, LoginRequiredException, ForbiddenUserException {
		User currentUser = userServices.findUserBySession(session);
    	
    	userServices.checkIfUserIsAdmin(currentUser);
		return sensorRepository.findAll();
	}
	
	public Sensor getSensor(Long id, HttpSession session) throws SensorNotFoundException, UserNotFoundException, LoginRequiredException, ForbiddenUserException{
		
		User currentUser = userServices.findUserBySession(session);
    	
    	userServices.checkIfUserIsAdmin(currentUser);
		
		Optional<Sensor> sensor= sensorRepository.findById(id);
		
		if (sensor.isPresent()) {
			return sensor.get();
		} else {
			throw new SensorNotFoundException("Sensor not found "+id);
		}
	}

}
