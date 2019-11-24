package ies.grupo33.CampusMonitoring.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "WEATHER_READING")
public class WeatherReading {

    private int sensorId;
    private LocalDateTime dateTime;
    private double temperature;
    private double humidity;
    private double co2;

    public WeatherReading() {
    }

    @Column(name = "sensor_id", nullable = false)
    public long getSensorId() {
        return sensorId;
    }

    public void setSensorId(int sensorId) {
        this.sensorId = sensorId;
    }

    @Column(name = "date_time", nullable = false)
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
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
        return "WeatherReading [sensorId=" + sensorId + ", date_time=" + dateTime + ", temperature=" + temperature
                + ", humidity=" + humidity + ", co2=" + co2 + "]";
    }



}