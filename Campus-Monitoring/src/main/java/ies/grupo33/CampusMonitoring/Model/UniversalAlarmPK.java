package ies.grupo33.CampusMonitoring.Model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDateTime;

@Embeddable
public class UniversalAlarmPK implements Serializable {
    @Column(name = "sensor_id", nullable = false)
    private long sensorId;
    @Column(name = "start_date_time", nullable = false)
    private LocalDateTime startDateTime;
    @Column(name = "violation_parameter", nullable = false)
    private String violationParameter;

    public UniversalAlarmPK(int sensorId, LocalDateTime startDateTime, String violationParameter) {
        this.sensorId = sensorId;
        this.startDateTime = startDateTime;
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

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public String getViolationParameter() {
        return violationParameter;
    }

    public void setViolationParameter(String violationParameter) {
        this.violationParameter = violationParameter;
    }
}