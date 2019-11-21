package ies.grupo33.CampusMonitoring.Stats;

import org.springframework.context.annotation.ComponentScan;

@ComponentScan
public class WeatherStats {
	
	private String day;
	private String month;
	private String year;
	private double tempMax;
	double tempMin;
	double tempAvg;
	double humMax;
	double humMin;
	double humAvg;
	double co2Max;
	double co2Min;
	double co2Avg;
	
	public WeatherStats();
	
	public String getDay() {
		return day;
	}
	public String getMonth() {
		return month;
	}
	public String getYear() {
		return year;
	}
	public double getTempMax() {
		return tempMax;
	}
	public double getTempMin() {
		return tempMin;
	}
	public double getTempAvg() {
		return tempAvg;
	}
	public double getHumMax() {
		return humMax;
	}
	public double getHumMin() {
		return humMin;
	}
	public double getHumAvg() {
		return humAvg;
	}
	public double getCo2Max() {
		return co2Max;
	}
	public double getCo2Min() {
		return co2Min;
	}
	public double getCo2Avg() {
		return co2Avg;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public void setTempMax(double tempMax) {
		this.tempMax = tempMax;
	}
	public void setTempMin(double tempMin) {
		this.tempMin = tempMin;
	}
	public void setTempAvg(double tempAvg) {
		this.tempAvg = tempAvg;
	}
	public void setHumMax(double humMax) {
		this.humMax = humMax;
	}
	public void setHumMin(double humMin) {
		this.humMin = humMin;
	}
	public void setHumAvg(double humAvg) {
		this.humAvg = humAvg;
	}
	public void setCo2Max(double co2Max) {
		this.co2Max = co2Max;
	}
	public void setCo2Min(double co2Min) {
		this.co2Min = co2Min;
	}
	public void setCo2Avg(double co2Avg) {
		this.co2Avg = co2Avg;
	}

}
