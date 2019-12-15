package ies.grupo33.CampusMonitoring.Representations;

import ies.grupo33.CampusMonitoring.Model.UniversalAlarm;

import java.time.LocalDateTime;

public class UniversalAlarmsRepresentation {

    private String localName;
    private LocalDateTime startDate;
    private String violationParameter;
    private String violationType;
    private double violationValue;
    private boolean ongoingStatus;
    private LocalDateTime endDate;
    private boolean notificationSent;

    public UniversalAlarmsRepresentation(UniversalAlarm universalAlarm) {
        this.localName = universalAlarm.getUniversalAlarmPK().getLocalName();
        this.startDate = universalAlarm.getUniversalAlarmPK().getStartDateTime();
        this.violationParameter = universalAlarm.getUniversalAlarmPK().getViolationParameter();
        this.violationType = universalAlarm.getViolationType();
        this.violationValue = universalAlarm.getViolationValue();
        this.ongoingStatus = universalAlarm.isOngoingStatus();
        this.endDate = universalAlarm.getEndDateTime();
        this.notificationSent = universalAlarm.isNotificationSent();
    }

    public String getLocalName() {
        return localName;
    }

    public void setLocalName(String localName) {
        this.localName = localName;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
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

    public boolean isOngoingStatus() {
        return ongoingStatus;
    }

    public void setOngoingStatus(boolean ongoingStatus) {
        this.ongoingStatus = ongoingStatus;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public boolean isNotificationSent() {
        return notificationSent;
    }

    public void setNotificationSent(boolean notificationSent) {
        this.notificationSent = notificationSent;
    }
}
