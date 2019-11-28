package ies.grupo33.CampusMonitoring.Model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDateTime;

@Embeddable
public class AlarmPK implements Serializable {
    @Column(name = "sensor_id", nullable = false)
    private int sensorId;
    @Column(name = "date_time", nullable = false)
    private LocalDateTime dateTime;
    @Column(name = "violation_parameter", nullable = false)
    private String violation_parameter;

    public AlarmPK(int sensorId, LocalDateTime dateTime, String violation_parameter) {
        this.sensorId = sensorId;
        this.dateTime = dateTime;
        this.violation_parameter = violation_parameter;
    }

    public AlarmPK() {

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

    public String getViolation_parameter() {
        return violation_parameter;
    }

    public void setViolation_parameter(String violation_parameter) {
        this.violation_parameter = violation_parameter;
    }
}