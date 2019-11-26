package ies.grupo33.CampusMonitoring.Model;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class AlarmPK implements Serializable{
	@Column(name = "sensor_id", nullable = false)
    private int sensorId;
    @Column(name = "date_time", nullable = false)
    private LocalDateTime dateTime;

    public AlarmPK(int sensorId, LocalDateTime dateTime) {
        this.sensorId = sensorId;
        this.dateTime = dateTime;
    }
    
    public int getSensorId() {
        return sensorId;
    }

    public void setSensorId(int sensorId) {
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
        return "AlarmPK{" +
                "sensorId=" + sensorId +
                ", dateTime=" + dateTime +
                '}';
    }
    
}
