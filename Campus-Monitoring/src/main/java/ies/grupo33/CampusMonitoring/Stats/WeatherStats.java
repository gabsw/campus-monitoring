package ies.grupo33.CampusMonitoring.Stats;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@SqlResultSetMapping(
        name = "weatherStatsMapping",
        classes = {
                @ConstructorResult(
                        targetClass = WeatherStats.class,
                        columns = {
                                @ColumnResult(name = "date"),
                                @ColumnResult(name = "tempMax"),
                                @ColumnResult(name = "tempMin"),
                                @ColumnResult(name = "tempAvg"),
                                @ColumnResult(name = "humMax"),
                                @ColumnResult(name = "humMin"),
                                @ColumnResult(name = "humAvg"),
                                @ColumnResult(name = "co2Max"),
                                @ColumnResult(name = "co2Min"),
                                @ColumnResult(name = "co2Avg"),
                                @ColumnResult(name = "localName")
                        }
                )
        }
)
@NamedNativeQuery(name = "computeWeatherStats", query = "SELECT DATE(date_time), " +
        "MAX(temperature) AS tempMax, " +
        "MIN(temperature) AS tempMin, " +
        "ROUND(AVG(temperature), 2) AS tempAvg, " +
        "MAX(humidity) AS humMax, " +
        "MIN(humidity) AS humMax, " +
        "ROUND(AVG(humidity), 2) AS humAvg, " +
        "MAX(co2) AS co2Max, " +
        "MIN(co2) AS co2Min, " +
        "ROUND(AVG(co2), 2) AS co2Avg, " +
        "name AS localName " +
        "FROM campus_monitoring.weather_reading AS wr " +
        "JOIN campus_monitoring.sensor AS se " +
        "ON wr.sensor_id = se.id " +
        "JOIN campus_monitoring.local AS lo " +
        "ON se.local_name = lo.name " +
        "WHERE lo.name = :localName AND " +
        "date_time BETWEEN :timeStart AND :timeEnd " +
        "GROUP BY DATE(date_time), name", resultSetMapping = "weatherStatsMapping")
public class WeatherStats {
    @EmbeddedId
    private WeatherStatsPK weatherStatsPK;
    private double tempMax;
    private double tempMin;
    private double tempAvg;
    private double humMax;
    private double humMin;
    private double humAvg;
    private double co2Max;
    private double co2Min;
    private double co2Avg;

    public WeatherStats() {

    }

    public WeatherStats(LocalDate date, double tempMax, double tempMin, double tempAvg, double humMax, double humMin, double humAvg, double co2Max, double co2Min, double co2Avg, String localName) {
        this.weatherStatsPK = new WeatherStatsPK(localName, date);
        this.tempMax = tempMax;
        this.tempMin = tempMin;
        this.tempAvg = tempAvg;
        this.humMax = humMax;
        this.humMin = humMin;
        this.humAvg = humAvg;
        this.co2Max = co2Max;
        this.co2Min = co2Min;
        this.co2Avg = co2Avg;
    }

    public LocalDate getDate() {
        return this.weatherStatsPK.getDate();
    }

    public void setDateTime(LocalDate date) {
        this.weatherStatsPK.setDate(date);
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
        return this.weatherStatsPK.getLocalName();
    }

    public void setLocalName(String localName) {
        this.weatherStatsPK.setLocalName(localName);
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
