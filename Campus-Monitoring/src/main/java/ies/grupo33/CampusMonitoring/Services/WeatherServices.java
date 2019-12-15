package ies.grupo33.CampusMonitoring.Services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ies.grupo33.CampusMonitoring.DTO.WeatherReadingDto;
import ies.grupo33.CampusMonitoring.Exception.LocalNotFoundException;
import ies.grupo33.CampusMonitoring.Exception.SensorNotFoundException;
import ies.grupo33.CampusMonitoring.Model.Sensor;
import ies.grupo33.CampusMonitoring.Model.WeatherReading;

import ies.grupo33.CampusMonitoring.Repository.WeatherRepository;

@Service
public class WeatherServices {


	@Autowired
	private WeatherRepository weatherRepository;
	
	@Autowired
	private SensorServices sensorServices;
	
	
	
	public Page<WeatherReading> getWeatherReadings(Pageable pageable){
		return weatherRepository.findAll(pageable);
	}
	

	public WeatherReadingDto getMostRecentWeatherReadingByLocal(String local) throws LocalNotFoundException{
		if (local ==null) {
			throw new IllegalArgumentException("Local is not defined.");
		}
		Optional<WeatherReading> wr = weatherRepository.findByLocalNameOrderByWeatherReadingPKDateTimeDescFirst(local);

		if (wr.isPresent()) {
			WeatherReading weatherReading = wr.get();
			WeatherReadingDto wrdto = new WeatherReadingDto(weatherReading.getWeatherReadingPK().getSensorId(), weatherReading.getWeatherReadingPK().getDateTime(), local, weatherReading.getTemperature(),
					weatherReading.getHumidity(), weatherReading.getCo2());
			return wrdto;
		} else {
			throw new LocalNotFoundException("Local not found "+local);
		}
	}


	public WeatherReadingDto getMostRecentWeatherReadingBySensorId(Long sensorId) throws SensorNotFoundException{

		Optional<WeatherReading> wr = weatherRepository.findFirstByWeatherReadingPKSensorIdOrderByWeatherReadingPKDateTimeDesc(sensorId);

		if (wr.isPresent()) {
			WeatherReading weatherReading = wr.get();
			Sensor s = sensorServices.getSensor(sensorId);

			WeatherReadingDto wrdto = new WeatherReadingDto(weatherReading.getWeatherReadingPK().getSensorId(), weatherReading.getWeatherReadingPK().getDateTime(), s.getLocalName(), weatherReading.getTemperature(),
					weatherReading.getHumidity(), weatherReading.getCo2());
			return wrdto;
		} else {
			throw new SensorNotFoundException("Sensor not found "+sensorId);
		}
	}


	public List<WeatherReadingDto> getWeatherReadingsByLocal(String local) throws LocalNotFoundException{
		if (local ==null) {
			throw new IllegalArgumentException("Local is not defined.");
		}
		List<WeatherReading> l = weatherRepository.findByLocalNameOrderByWeatherReadingPKDateTimeAsc(local);

		List<WeatherReadingDto> rl = new ArrayList<>();
		for(WeatherReading wr:l) {
			rl.add(new WeatherReadingDto(wr.getWeatherReadingPK().getSensorId(), wr.getWeatherReadingPK().getDateTime(), local, wr.getTemperature(),
					wr.getHumidity(), wr.getCo2()));
		}
		return rl;
		
	}
	
	
	public List<WeatherReadingDto> getWeatherReadingBySensor(long sensorId, Pageable pageable) throws SensorNotFoundException {
		
		Page<WeatherReading> l= weatherRepository.findByWeatherReadingPKSensorIdOrderByWeatherReadingPKDateTimeAsc(sensorId, pageable);
		
		List<WeatherReadingDto> rl = new ArrayList<>();
		Sensor s = sensorServices.getSensor(sensorId);
		if(l.getSize()==0 && s!=null) {
			return rl;
		}
		for(WeatherReading wr:l) {
			rl.add(new WeatherReadingDto(wr.getWeatherReadingPK().getSensorId(), wr.getWeatherReadingPK().getDateTime(), s.getLocalName(), wr.getTemperature(),
					wr.getHumidity(), wr.getCo2()));
		}
		return rl;
	}

	public List<WeatherReadingDto> getWeatherReadingBySensorAndDate(long sensorId, LocalDateTime dateInit, LocalDateTime dateFin, Pageable pageable) throws SensorNotFoundException {
		Page<WeatherReading> l;

		if (dateInit ==null || dateFin==null) {
			throw new IllegalArgumentException("Date interval is not defined.");
		}
		
		l = weatherRepository.findByWeatherReadingPKSensorIdAndWeatherReadingPKDateTimeBetweenOrderByWeatherReadingPKDateTimeAsc(sensorId, dateInit, dateFin, pageable);
		
		List<WeatherReadingDto> rl = new ArrayList<>();
		Sensor s = sensorServices.getSensor(sensorId);
		if(l.getSize()==0 && s!=null) {
			return rl;
		}

		for(WeatherReading wr:l) {
			rl.add(new WeatherReadingDto(wr.getWeatherReadingPK().getSensorId(), wr.getWeatherReadingPK().getDateTime(), s.getLocalName(), wr.getTemperature(),
					wr.getHumidity(), wr.getCo2()));
		}
		return rl;
	
	}
	

	public List<WeatherReadingDto> getWeatherReadingByLocalAndDate(String local, LocalDateTime dateInit, LocalDateTime dateFin) {
		List<WeatherReading> l;
		if (dateInit ==null || dateFin==null) {
			throw new IllegalArgumentException("Date interval is not defined.");
		}
		if (local == null) {
			throw new IllegalArgumentException("Local is not defined.");
		}

		l = weatherRepository.findByLocalNameAndWeatherReadingPKDateTimeBetweenOrderByWeatherReadingPKDateTimeAsc(local, dateInit, dateFin);

		List<WeatherReadingDto> rl = new ArrayList<>();
		for(WeatherReading wr:l) {
			rl.add(new WeatherReadingDto(wr.getWeatherReadingPK().getSensorId(), wr.getWeatherReadingPK().getDateTime(), local, wr.getTemperature(),
					wr.getHumidity(), wr.getCo2()));
		}
		return rl;
	}


	public List<WeatherReadingDto> getWeatherReadingByLocalLimit(String local, int limit) {
		if (local == null) {
			throw new IllegalArgumentException("Local is not defined.");
		}

		List<WeatherReading> l = weatherRepository.findByLocalNameLimit(local, limit);

		List<WeatherReadingDto> rl = new ArrayList<>();
		for(WeatherReading wr:l) {
			rl.add(new WeatherReadingDto(wr.getWeatherReadingPK().getSensorId(), wr.getWeatherReadingPK().getDateTime(), local, wr.getTemperature(),
					wr.getHumidity(), wr.getCo2()));
		}
		return rl;
		
	}
	
	
	public List<WeatherReadingDto> getWeatherReadingByLocalLimit(String local, String limit) {
		if (local == null) {
            throw new IllegalArgumentException("Local is not defined.");
		}
		int limite = Integer.parseInt(limit);
		List<WeatherReading> l = weatherRepository.findByLocalNameLimit(local, limite);
		
		List<WeatherReadingDto> rl = new ArrayList<>();
		for(WeatherReading wr:l) {
			rl.add(new WeatherReadingDto(wr.getWeatherReadingPK().getSensorId(), wr.getWeatherReadingPK().getDateTime(), local, wr.getTemperature(),
					wr.getHumidity(), wr.getCo2()));
		}
		return rl;

	}


}