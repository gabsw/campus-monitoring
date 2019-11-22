package ies.grupo33.CampusMonitoring.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.text.ParseException;

@Entity
@Table(name = "WEATHER_READING")
public class WeatherReading {
	
	private long sensor_id;
	//private Date date_time;
	private String date_time;
	private double temperature;
	private double humidity;
	private double co2;
	
	public WeatherReading(long id, String date_time, double temperature, double humidity, double co2) throws ParseException {
		this.sensor_id = id;
		//DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		//this.date_time = dateFormat.parse(date_time);
		this.date_time = date_time;
		this.temperature = temperature;
		this.humidity = humidity;
		this.co2 = co2;
	}

	@Column(name = "sensor_id", nullable = false)
	public long getSensor_id() {
		return sensor_id;
	}

	public void setSensor_id(long sensor_id) {
		this.sensor_id = sensor_id;
	}

	@Column(name = "date_time", nullable = false)
	public String getDate_time() {
		return date_time;
	}

	public void setDate_time(String date_time) {
		this.date_time = date_time;
	}

	@Column(name = "temperature", nullable = false)
	public double getTemperature() {
		return temperature;
	}

	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}

	@Column(name = "humidity", nullable = false)
	public double getHumidity() {
		return humidity;
	}

	public void setHumidity(double humidity) {
		this.humidity = humidity;
	}

	@Column(name = "co2", nullable = true)
	public double getCo2() {
		return co2;
	}

	public void setCo2(double co2) {
		this.co2 = co2;
	}

	@Override
	public String toString() {
		return "WeatherReading [sensor_id=" + sensor_id + ", date_time=" + date_time + ", temperature=" + temperature
				+ ", humidity=" + humidity + ", co2=" + co2 + "]";
	}
	
	
	
}
