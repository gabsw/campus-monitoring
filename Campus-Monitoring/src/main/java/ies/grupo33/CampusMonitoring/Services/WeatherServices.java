package ies.grupo33.CampusMonitoring.Services;

import java.time.LocalDateTime;
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

@Service
public class WeatherServices {
	
	
	@Autowired
	private WeatherRepository weatherRepository;
	
	
	
	public List<WeatherReading> getWeatherReadings(){
		return weatherRepository.findAll();
	}
	
	public List<WeatherReading> getWeatherReadingsByDate(LocalDateTime dateInit, LocalDateTime dateFin){
		List<WeatherReading> list = weatherRepository.findAll();
		for (WeatherReading w : list) {
			if (dateInit!=null && w.getWeatherReadingPK().getDateTime().compareTo(dateInit)<0) {
				list.remove(w);
			}
			else if(dateFin!=null && w.getWeatherReadingPK().getDateTime().compareTo(dateFin)>0) {
				list.remove(w);
			}
		}
		list.sort(new Comparator<WeatherReading>() {
		    @Override
		    public int compare(WeatherReading w1, WeatherReading w2) {
		        return w1.getWeatherReadingPK().getDateTime().compareTo(w2.getWeatherReadingPK().getDateTime());
		     }});
		return list;
	}
	
	@SuppressWarnings("deprecation")
	public List<WeatherReading> getWeatherReadingBySensor(long sensorId) {
		if (new Long(sensorId) == null) {
			return weatherRepository.findAll();
		}
		List<WeatherReading> weather= weatherRepository.findBySensorId(sensorId);
		
		return weather;
	}
	
	@SuppressWarnings("deprecation")
	public List<WeatherReading> getWeatherReadingBySensorAndDate(long sensorId, LocalDateTime dateInit, LocalDateTime dateFin) {
		List<WeatherReading> list;
		if (new Long(sensorId)== null) {
			list = weatherRepository.findAll();
		}
		else {
			list = weatherRepository.findBySensorId(sensorId);
		}
		for (WeatherReading w : list) {
			if (dateInit!=null && w.getWeatherReadingPK().getDateTime().compareTo(dateInit)<0) {
				list.remove(w);
			}
			else if(dateFin!=null && w.getWeatherReadingPK().getDateTime().compareTo(dateFin)>0) {
				list.remove(w);
			}
		}
		list.sort(new Comparator<WeatherReading>() {
		    @Override
		    public int compare(WeatherReading w1, WeatherReading w2) {
		        return w1.getWeatherReadingPK().getDateTime().compareTo(w2.getWeatherReadingPK().getDateTime());
		     }});
		return list;		
	}
	
	
}
