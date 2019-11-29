package ies.grupo33.CampusMonitoring.Model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDateTime;

@Embeddable
public class UniversalAlarmPK implements Serializable {
    @Column(name = "sensor_id", nullable = false)
    private long sensorId;
    @Column(name = "date_time", nullable = false)
    private LocalDateTime dateTime;
    @Column(name = "violation_parameter", nullable = false)
    private String violationParameter;

    public UniversalAlarmPK(int sensorId, LocalDateTime dateTime, String violationParameter) {
        this.sensorId = sensorId;
        this.dateTime = dateTime;
        this.violationParameter = violationParameter;
    }

    public UniversalAlarmPK() {

    }

    public long getSensorId() {
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

    public String getViolationParameter() {
        return violationParameter;
    }

    public void setViolationParameter(String violationParameter) {
        this.violationParameter = violationParameter;
    }
}