package ies.grupo33.CampusMonitoring.Representations;

import ies.grupo33.CampusMonitoring.Model.WeatherStats;

import java.time.LocalDate;

public class WeatherStatsRepresentation {

    private String localName;
    private LocalDate date;
    private double tempMax;
    private double tempMin;
    private double tempAvg;
    private double humMax;
    private double humMin;
    private double humAvg;
    private double co2Max;
    private double co2Min;
    private double co2Avg;

    public WeatherStatsRepresentation(WeatherStats weatherStats) {

        this.localName = weatherStats.getWeatherStatsPK().getLocalName();
        this.date = weatherStats.getWeatherStatsPK().getDate();
        this.tempMax = weatherStats.getTempMax();
        this.tempMin = weatherStats.getTempMin();
        this.tempAvg = weatherStats.getTempAvg();
        this.humAvg = weatherStats.getHumAvg();
        this.humMax = weatherStats.getHumMax();
        this.humMin = weatherStats.getHumMin();
        this.co2Max = weatherStats.getCo2Max();
        this.co2Min = weatherStats.getCo2Min();
        this.co2Avg = weatherStats.getCo2Avg();

    }

    public String getLocalName() {
        return localName;
    }

    public void setLocalName(String localName) {
        this.localName = localName;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
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
}
