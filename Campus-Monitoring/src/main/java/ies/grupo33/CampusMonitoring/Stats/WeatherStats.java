package ies.grupo33.CampusMonitoring.Stats;

import org.hibernate.annotations.Immutable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Immutable
@Table(name = "weather_stats_view", schema = "campus_monitoring")
public class WeatherStats {
    @EmbeddedId
    private WeatherStatsPK weatherStatsPK;
    @Column(name = "temp_max", nullable = false)
    private double tempMax;
    @Column(name = "temp_min", nullable = false)
    private double tempMin;
    @Column(name = "temp_avg", nullable = false)
    private double tempAvg;
    @Column(name = "hum_max", nullable = false)
    private double humMax;
    @Column(name = "hum_min", nullable = false)
    private double humMin;
    @Column(name = "hum_avg", nullable = false)
    private double humAvg;
    @Column(name = "co2_max")
    private double co2Max;
    @Column(name = "co2_min")
    private double co2Min;
    @Column(name = "co2_avg")
    private double co2Avg;

    public WeatherStats() {

    }

    public WeatherStatsPK getWeatherStatsPK() {
        return weatherStatsPK;
    }

    public void setWeatherStatsPK(WeatherStatsPK weatherStatsPK) {
        this.weatherStatsPK = weatherStatsPK;
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

    @Override
    public String toString() {
        return "WeatherStats{" +
                "weatherStatsPK=" + weatherStatsPK +
                ", tempMax=" + tempMax +
                ", tempMin=" + tempMin +
                ", tempAvg=" + tempAvg +
                ", humMax=" + humMax +
                ", humMin=" + humMin +
                ", humAvg=" + humAvg +
                ", co2Max=" + co2Max +
                ", co2Min=" + co2Min +
                ", co2Avg=" + co2Avg +
                '}';
    }
}
