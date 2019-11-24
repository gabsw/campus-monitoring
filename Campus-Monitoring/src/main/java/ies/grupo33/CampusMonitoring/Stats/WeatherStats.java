package ies.grupo33.CampusMonitoring.Stats;

import java.time.LocalDateTime;

public class WeatherStats {
	
	private LocalDateTime dateTime;
	private double tempMax;
	private double tempMin;
	private double tempAvg;
	private double humMax;
	private double humMin;
	private double humAvg;
	private double co2Max;
	private double co2Min;
	private double co2Avg;
	private String localName;
	
	public WeatherStats() {
		
	};

	public WeatherStats(LocalDateTime dateTime, double tempMax, double tempMin, double tempAvg, double humMax, double humMin, double humAvg, double co2Max, double co2Min, double co2Avg, String localName) {
		this.dateTime = dateTime;
		this.tempMax = tempMax;
		this.tempMin = tempMin;
		this.tempAvg = tempAvg;
		this.humMax = humMax;
		this.humMin = humMin;
		this.humAvg = humAvg;
		this.co2Max = co2Max;
		this.co2Min = co2Min;
		this.co2Avg = co2Avg;
		this.localName = localName;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	public double getTempMax() {
		return tempMax;
	}

	public void setTempMax(double tempMax) {
		this.tempMax = tempMax;
	}

	public double getTempMin() {
		return tempMin;
	}

	public void setTempMin(double tempMin) {
		this.tempMin = tempMin;
	}

	public double getTempAvg() {
		return tempAvg;
	}

	public void setTempAvg(double tempAvg) {
		this.tempAvg = tempAvg;
	}

	public double getHumMax() {
		return humMax;
	}

	public void setHumMax(double humMax) {
		this.humMax = humMax;
	}

	public double getHumMin() {
		return humMin;
	}

	public void setHumMin(double humMin) {
		this.humMin = humMin;
	}

	public double getHumAvg() {
		return humAvg;
	}

	public void setHumAvg(double humAvg) {
		this.humAvg = humAvg;
	}

	public double getCo2Max() {
		return co2Max;
	}

	public void setCo2Max(double co2Max) {
		this.co2Max = co2Max;
	}

	public double getCo2Min() {
		return co2Min;
	}

	public void setCo2Min(double co2Min) {
		this.co2Min = co2Min;
	}

	public double getCo2Avg() {
		return co2Avg;
	}

	public void setCo2Avg(double co2Avg) {
		this.co2Avg = co2Avg;
	}

	public String getLocalName() {
		return localName;
	}

	public void setLocalName(String localName) {
		this.localName = localName;
	}
}
