package ies.grupo33.CampusMonitoring.Services;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ies.grupo33.CampusMonitoring.Model.Alarm;
import ies.grupo33.CampusMonitoring.Repository.AlarmeRepository;

@Service
public class AlarmeServices {

	@Autowired
	private AlarmeRepository alarmeRepository;
	
	public List<Alarm> getAllAlarms(){
		return alarmeRepository.findAll();
	}
	
	public List<Alarm> getAlarms(long sensorId) {
		
		List<Alarm> alarm= alarmeRepository.findByAlarmPKSensorId(sensorId);
		return alarm;
	}
	
}
