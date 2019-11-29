package ies.grupo33.CampusMonitoring.Model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDateTime;

@Embeddable
public class WeatherReadingPK implements Serializable {
    @Column(name = "sensor_id", nullable = false)
    private long sensorId;
    @Column(name = "date_time", nullable = false)
    private LocalDateTime dateTime;

    public WeatherReadingPK(long sensorId, LocalDateTime dateTime) {
        this.sensorId = sensorId;
        this.dateTime = dateTime;
    }

    public WeatherReadingPK() {
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

    @Override
    public String toString() {
        return "WeatherReadingPK{" +
                "sensorId=" + sensorId +
                ", dateTime=" + dateTime +
                '}';
    }
}
