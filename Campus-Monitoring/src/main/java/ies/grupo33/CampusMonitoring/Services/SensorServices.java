package ies.grupo33.CampusMonitoring.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ies.grupo33.CampusMonitoring.Model.Sensor;
import ies.grupo33.CampusMonitoring.Repository.SensorRepository;


@Service
public class SensorServices {
	
	@Autowired
	private SensorRepository sensorRepository;
	
	public List<Sensor> getSensors() {
		return sensorRepository.findAll();
	}

}
