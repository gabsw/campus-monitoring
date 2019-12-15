package ies.grupo33.CampusMonitoring.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ies.grupo33.CampusMonitoring.Exception.SensorNotFoundException;
import ies.grupo33.CampusMonitoring.Model.Sensor;
import ies.grupo33.CampusMonitoring.Repository.SensorRepository;


@Service
public class SensorServices {
	
	@Autowired
	private SensorRepository sensorRepository;
	
	public List<Sensor> getSensors() {
		return sensorRepository.findAll();
	}
	
	public Sensor getSensor(long id) throws SensorNotFoundException{
		
		Optional<Sensor> sensor= sensorRepository.findById(id);
		
		if (sensor.isPresent()) {
			return sensor.get();
		} else {
			throw new SensorNotFoundException("Sensor not found "+id);
		}
	}

}
