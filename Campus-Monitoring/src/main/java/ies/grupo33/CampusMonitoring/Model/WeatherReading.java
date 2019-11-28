package ies.grupo33.CampusMonitoring.Model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "WEATHER_READING", schema = "campus_monitoring")
public class WeatherReading {
	@EmbeddedId
	private WeatherReadingPK weatherReadingPK;
	@Column(name = "temperature", nullable = false)
	private double temperature;
	@Column(name = "humidity", nullable = false)
	private double humidity;
	@Column(name = "co2", nullable = true)
	private double co2;

	public WeatherReading() {
	}

	public WeatherReadingPK getWeatherReadingPK() {
		return weatherReadingPK;
	}

	public void setWeatherReadingPK(WeatherReadingPK weatherReadingPK) {
		this.weatherReadingPK = weatherReadingPK;
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

	@Override
	public String toString() {
		return "WeatherReading{" +
				"weatherReadingPK=" + weatherReadingPK +
				", temperature=" + temperature +
				", humidity=" + humidity +
				", co2=" + co2 +
				'}';
	}
}