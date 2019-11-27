package ies.grupo33.CampusMonitoring.Model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class WeatherReadingDto implements Serializable{
	
	private long sensorId;
	private LocalDateTime dateTime;
	private String localName;
	private double temperature;
	private double humidity;
	private double co2;
	
	
	public WeatherReadingDto(long sensorId, LocalDateTime dateTime, String localName, double temperature,
			double humidity, double co2) {
		this.sensorId = sensorId;
		this.dateTime = dateTime;
		this.localName = localName;
		this.temperature = temperature;
		this.humidity = humidity;
		this.co2 = co2;
	}


	public long getSensorId() {
		return sensorId;
	}


	public void setSensorId(long sensorId) {
		this.sensorId = sensorId;
	}


	public LocalDateTime getDateTime() {
		return dateTime;
	}


	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}


	public String getLocalName() {
		return localName;
	}


	public void setLocalName(String localName) {
		this.localName = localName;
	}


	public double getTemperature() {
		return temperature;
	}


	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}


	public double getHumidity() {
		return humidity;
	}


	public void setHumidity(double humidity) {
		this.humidity = humidity;
	}


	public double getCo2() {
		return co2;
	}


	public void setCo2(double co2) {
		this.co2 = co2;
	}
	
	
	

}
