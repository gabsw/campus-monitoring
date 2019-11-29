package ies.grupo33.CampusMonitoring.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.time.LocalDateTime;

// this annotation tells Jackson to only include values that are not null when transforming this object to json
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UniversalAlarmDTO implements Serializable {

    // From UniversalAlarmPK object
    private long sensorId;
    private LocalDateTime startDateTime;
    private String violationParameter;

    // From UniversalAlarm object
    private String violationType;
    private double violationValue;
    private boolean status;
    private LocalDateTime endDateTime;

    // Adds link to localName
    private String localName;

    public UniversalAlarmDTO(long sensorId,
                             LocalDateTime startDateTime,
                             String violationParameter,
                             String violationType,
                             double violationValue,
                             boolean status,
                             LocalDateTime endDateTime,
                             String localName) {
        this.sensorId = sensorId;
        this.startDateTime = startDateTime;
        this.violationParameter = violationParameter;
        this.violationType = violationType;
        this.violationValue = violationValue;
        this.status = status;
        this.endDateTime = endDateTime;
        this.localName = localName;
    }

    public UniversalAlarmDTO() {
    }

    public long getSensorId() {
        return sensorId;
    }

    public void setSensorId(long sensorId) {
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

    public String getViolationType() {
        return violationType;
    }

    public void setViolationType(String violationType) {
        this.violationType = violationType;
    }

    public double getViolationValue() {
        return violationValue;
    }

    public void setViolationValue(double violationValue) {
        this.violationValue = violationValue;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(LocalDateTime endDateTime) {
        this.endDateTime = endDateTime;
    }

    public String getLocalName() {
        return localName;
    }

    public void setLocalName(String localName) {
        this.localName = localName;
    }
}
