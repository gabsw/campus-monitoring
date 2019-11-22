package ies.grupo33.CampusMonitoring.Services;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ies.grupo33.CampusMonitoring.Model.Sensor;
import ies.grupo33.CampusMonitoring.Model.WeatherReading;
import ies.grupo33.CampusMonitoring.Repository.SensorRepository;
import ies.grupo33.CampusMonitoring.Repository.WeatherRepository;
import net.bytebuddy.asm.Advice.Local;

@Service
public class WeatherServices {
	
	@Autowired
	private SensorRepository sensorRepository;
	@Autowired
	private WeatherRepository weatherRepository;
	@Autowired
	private AlarmeRepository alarmeRepository;
	@Autowired
	private LocalRepository localRepository;
	
	
	public List<Local> getLocals(String name){
		if (name == null) {
			return LocalRepository.findAll();
		}
		Optional<Local> local = LocalRepository.findByName(name);
		
		if (local.isPresent()) {
			return Collections.singletonList(local.get());
		} else {
			return Collections.emptyList();
		}
	}
	
	@SuppressWarnings("deprecation") //Por causa da função new Long(sensorId) porque o long não pode ser comparado diretamente com o null
	public List<Alarm> getAlarms(long sensorId) {
		if (new Long(sensorId) == null) {
			return AlarmeRepository.findAll();
		}
		Optional<Alarm> alarm= AlarmeRepository.findBySensorId(sensorId);
		
		if (alarm.isPresent()) {
			return Collections.singletonList(alarm.get());
		} else {
			return Collections.emptyList();
		}
	}
	
	public List<Sensor> getSensors() {
		return SensorRepository.findAll();
	}
	
	@SuppressWarnings("deprecation")
	public List<WeatherReading> getWeatherReadingBySensor(long sensorId) {
		if (new Long(sensorId) == null) {
			return WeatherRepository.findAll();
		}
		Optional<WeatherReading> weather= WeatherRepository.findBySensorId(sensorId);
		
		if (alarm.isPresent()) {
			return Collections.singletonList(weather.get());
		} else {
			return Collections.emptyList();
		}
	}
	
	@SuppressWarnings("deprecation")
	public List<WeatherReading> getWeatherReadingBySensorAndDate(long sensorId, String dateInit, String dateFin) {
		List<WeatherReading> list;
		if (new Long(sensorId)== null) {
			list = WeatherRepository.findAll();
		}
		else {
			list = WeatherRepository.findBySensorId(sensorId);
		}
		for (WeatherReading w : list) {
			if (dateInit!=null && w.getDate_time().compareTo(dateInit)<0) {
				list.remove(w);
			}
			else if(dateFin!=null && w.getDate_time().compareTo(dateFin)>0) {
				list.remove(w);
			}
		}
		list.sort(new Comparator<WeatherReading>() {
		    @Override
		    public int compare(WeatherReading w1, WeatherReading w2) {
		        return w1.getDate_time().compareTo(w2.getDate_time());
		     }});
		return list;
		
		
	}
	
	
}
