package ies.grupo33.CampusMonitoring.Services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import ies.grupo33.CampusMonitoring.Exception.WeatherReadingNotFoundException;
import ies.grupo33.CampusMonitoring.Repository.LocalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ies.grupo33.CampusMonitoring.DTO.WeatherReadingDto;
import ies.grupo33.CampusMonitoring.Exception.ForbiddenUserException;
import ies.grupo33.CampusMonitoring.Exception.LocalNotFoundException;
import ies.grupo33.CampusMonitoring.Exception.LoginRequiredException;
import ies.grupo33.CampusMonitoring.Exception.SensorNotFoundException;
import ies.grupo33.CampusMonitoring.Exception.UserNotFoundException;
import ies.grupo33.CampusMonitoring.Model.Sensor;
import ies.grupo33.CampusMonitoring.Model.User;
import ies.grupo33.CampusMonitoring.Model.WeatherReading;

import ies.grupo33.CampusMonitoring.Repository.WeatherRepository;

@Service
public class WeatherServices {


	@Autowired
	private WeatherRepository weatherRepository;

	@Autowired
	private LocalRepository localRepository;
	
	@Autowired
	private SensorServices sensorServices;
	
	@Autowired
    private UserServices userServices;

	// TODO: Adds LocalNotFound to all the methods
	
	
	public Page<WeatherReading> getWeatherReadings(Pageable pageable, HttpSession session) throws UserNotFoundException, LoginRequiredException, ForbiddenUserException{
		User currentUser = userServices.findUserBySession(session);
		userServices.checkIfUserIsAdmin(currentUser);
		return weatherRepository.findAll(pageable);
	}
	

	public WeatherReadingDto getMostRecentWeatherReadingByLocal(String local, HttpSession session)
			throws LocalNotFoundException, WeatherReadingNotFoundException, UserNotFoundException, LoginRequiredException, ForbiddenUserException {
		
		User currentUser = userServices.findUserBySession(session);
		
		userServices.checkIfUserIsAtLocal(currentUser.getUsername(), local);
		
		
		if (local ==null) {
			throw new IllegalArgumentException("Local is not defined.");
		}

		if (!localRepository.findById(local).isPresent()) {
			throw new LocalNotFoundException("Local not found " + local);
		}

		Optional<WeatherReading> wr = weatherRepository.findByLocalNameOrderByWeatherReadingPKDateTimeDescFirst(local);

		if (wr.isPresent()) {
			WeatherReading weatherReading = wr.get();
			return new WeatherReadingDto(weatherReading.getWeatherReadingPK().getSensorId(), weatherReading.getWeatherReadingPK().getDateTime(), local, weatherReading.getTemperature(),
					weatherReading.getHumidity(), weatherReading.getCo2());
		} else {
			throw new WeatherReadingNotFoundException("Weather Readings not found for " + local);
		}
	}


	public WeatherReadingDto getMostRecentWeatherReadingBySensorId(Long sensorId, HttpSession session)
			throws SensorNotFoundException, WeatherReadingNotFoundException, UserNotFoundException, LoginRequiredException, ForbiddenUserException{

		Optional<WeatherReading> wr = weatherRepository.findFirstByWeatherReadingPKSensorIdOrderByWeatherReadingPKDateTimeDesc(sensorId);

		Sensor s = sensorServices.getSensor(sensorId, session);

		if (wr.isPresent()) {
			WeatherReading weatherReading = wr.get();

			return new WeatherReadingDto(weatherReading.getWeatherReadingPK().getSensorId(), weatherReading.getWeatherReadingPK().getDateTime(), s.getLocalName(), weatherReading.getTemperature(),
					weatherReading.getHumidity(), weatherReading.getCo2());
		} else {
			throw new WeatherReadingNotFoundException("Weather Readings not found for " + sensorId);
		}
	}


	public List<WeatherReadingDto> getWeatherReadingsByLocal(String local, HttpSession session) throws LocalNotFoundException, UserNotFoundException, LoginRequiredException, ForbiddenUserException{
		
		User currentUser = userServices.findUserBySession(session);
		
		userServices.checkIfUserIsAtLocal(currentUser.getUsername(), local);

		
		if (local ==null) {
			throw new IllegalArgumentException("Local is not defined.");
		}

		if (!localRepository.findById(local).isPresent()) {
			throw new LocalNotFoundException("Local not found " + local);
		}
		
		List<WeatherReading> l = weatherRepository.findByLocalNameOrderByWeatherReadingPKDateTimeAsc(local);

		List<WeatherReadingDto> rl = new ArrayList<>();
		for(WeatherReading wr:l) {
			rl.add(new WeatherReadingDto(wr.getWeatherReadingPK().getSensorId(), wr.getWeatherReadingPK().getDateTime(), local, wr.getTemperature(),
					wr.getHumidity(), wr.getCo2()));
		}
		return rl;
		
	}
	
	
	public List<WeatherReadingDto> getWeatherReadingBySensor(Long sensorId, Pageable pageable, HttpSession session) throws SensorNotFoundException, UserNotFoundException, LoginRequiredException, ForbiddenUserException {
		
		Page<WeatherReading> l= weatherRepository.findByWeatherReadingPKSensorIdOrderByWeatherReadingPKDateTimeAsc(sensorId, pageable);
		
		List<WeatherReadingDto> rl = new ArrayList<>();
		Sensor s = sensorServices.getSensor(sensorId, session);
		if(l.getSize()==0 && s!=null) {
			return rl;
		}
		for(WeatherReading wr:l) {
			rl.add(new WeatherReadingDto(wr.getWeatherReadingPK().getSensorId(), wr.getWeatherReadingPK().getDateTime(), s.getLocalName(), wr.getTemperature(),
					wr.getHumidity(), wr.getCo2()));
		}
		return rl;
	}

	public List<WeatherReadingDto> getWeatherReadingBySensorAndDate(Long sensorId, LocalDateTime dateInit, LocalDateTime dateFin, Pageable pageable, HttpSession session) throws SensorNotFoundException, UserNotFoundException, LoginRequiredException, ForbiddenUserException {
		Page<WeatherReading> l;

		if (dateInit ==null || dateFin==null) {
			throw new IllegalArgumentException("Date interval is not defined.");
		}
		
		l = weatherRepository.findByWeatherReadingPKSensorIdAndWeatherReadingPKDateTimeBetweenOrderByWeatherReadingPKDateTimeAsc(sensorId, dateInit, dateFin, pageable);
		
		List<WeatherReadingDto> rl = new ArrayList<>();
		Sensor s = sensorServices.getSensor(sensorId, session);
		if(l.getSize()==0 && s!=null) {
			return rl;
		}

		for(WeatherReading wr:l) {
			rl.add(new WeatherReadingDto(wr.getWeatherReadingPK().getSensorId(), wr.getWeatherReadingPK().getDateTime(), s.getLocalName(), wr.getTemperature(),
					wr.getHumidity(), wr.getCo2()));
		}
		return rl;
	
	}
	

	public List<WeatherReadingDto> getWeatherReadingByLocalAndDate(String local, LocalDateTime dateInit, LocalDateTime dateFin, HttpSession session) throws LocalNotFoundException, UserNotFoundException, LoginRequiredException, ForbiddenUserException{
		List<WeatherReading> l;
		if (dateInit ==null || dateFin==null) {
			throw new IllegalArgumentException("Date interval is not defined.");
		}
		if (local == null) {
			throw new IllegalArgumentException("Local is not defined.");
		}

		if (!localRepository.findById(local).isPresent()) {
			throw new LocalNotFoundException("Local not found " + local);
		}
		
		User currentUser = userServices.findUserBySession(session);
		
		userServices.checkIfUserIsAtLocal(currentUser.getUsername(), local);

		l = weatherRepository.findByLocalNameAndWeatherReadingPKDateTimeBetweenOrderByWeatherReadingPKDateTimeAsc(local, dateInit, dateFin);

		List<WeatherReadingDto> rl = new ArrayList<>();
		for(WeatherReading wr:l) {
			rl.add(new WeatherReadingDto(wr.getWeatherReadingPK().getSensorId(), wr.getWeatherReadingPK().getDateTime(), local, wr.getTemperature(),
					wr.getHumidity(), wr.getCo2()));
		}
		return rl;
	}


	public List<WeatherReadingDto> getWeatherReadingByLocalLimit(String local, int limit, HttpSession session) throws LocalNotFoundException, UserNotFoundException, LoginRequiredException, ForbiddenUserException {
		
		User currentUser = userServices.findUserBySession(session);
		
		userServices.checkIfUserIsAtLocal(currentUser.getUsername(), local);
		
		if (local == null) {
			throw new IllegalArgumentException("Local is not defined.");
		}

		if (!localRepository.findById(local).isPresent()) {
			throw new LocalNotFoundException("Local not found " + local);
		}

		List<WeatherReading> l = weatherRepository.findByLocalNameLimit(local, limit);

		List<WeatherReadingDto> rl = new ArrayList<>();
		for(WeatherReading wr:l) {
			rl.add(new WeatherReadingDto(wr.getWeatherReadingPK().getSensorId(), wr.getWeatherReadingPK().getDateTime(), local, wr.getTemperature(),
					wr.getHumidity(), wr.getCo2()));
		}
		return rl;
		
	}

}