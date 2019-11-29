package ies.grupo33.CampusMonitoring.Representations;

import ies.grupo33.CampusMonitoring.Model.UniversalAlarm;

import java.time.LocalDateTime;

public class UniversalAlarmRepresentation {

    private String localName;
    private int sensorId;
    private LocalDateTime dateTime;
    private String violation_parameter;
    private String violation_type;
    private double violation_value;
    private boolean status;

    public UniversalAlarmRepresentation(UniversalAlarm universalAlarm) {
        this.sensorId = universalAlarm.getUniversalAlarmPK().getSensorId();
        this.dateTime = universalAlarm.getUniversalAlarmPK().getDateTime();
        this.violation_parameter = universalAlarm.getUniversalAlarmPK().getViolationParameter();
        this.violation_type = universalAlarm.getViolationType();
        this.violation_value = universalAlarm.getViolationValue();
        this.status = universalAlarm.getStatus();
    }

    public String getLocalName() {
        return localName;
    }

    public void setLocalName(String localName) {
        this.localName = localName;
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

    public String getViolation_type() {
        return violation_type;
    }

    public void setViolation_type(String violation_type) {
        this.violation_type = violation_type;
    }

    public double getViolation_value() {
        return violation_value;
    }

    public void setViolation_value(double violation_value) {
        this.violation_value = violation_value;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
