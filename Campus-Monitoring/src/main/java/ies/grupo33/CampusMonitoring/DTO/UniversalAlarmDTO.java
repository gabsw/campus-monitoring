package ies.grupo33.CampusMonitoring.DTO;

import java.io.Serializable;
import java.time.LocalDateTime;

public class UniversalAlarmDTO implements Serializable {

    // From UniversalAlarmPK object
    private long sensorId;
    private LocalDateTime dateTime;
    private String violationParameter;

    // From UniversalAlarm object
    private String violationType;
    private double violationValue;
    private boolean status;

    // Adds link to localName
    private String localName;

    public UniversalAlarmDTO(long sensorId, LocalDateTime dateTime, String violationParameter, String violationType,
                             double violationValue, boolean status, String localName) {
        this.sensorId = sensorId;
        this.dateTime = dateTime;
        this.violationParameter = violationParameter;
        this.violationType = violationType;
        this.violationValue = violationValue;
        this.status = status;
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

    public String getLocalName() {
        return localName;
    }

    public void setLocalName(String localName) {
        this.localName = localName;
    }


}
