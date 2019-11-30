package ies.grupo33.CampusMonitoring.Model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "universal_alarm", schema = "campus_monitoring")
public class UniversalAlarm {
    @EmbeddedId
    private UniversalAlarmPK universalAlarmPK;
    @Column(name = "violation_type", nullable = false)
    private String violationType;
    @Column(name = "violation_value", nullable = false)
    private double violationValue;
    @Column(name = "ongoing_status", nullable = false)
    private boolean ongoingStatus;
    @Column(name = "end_date_time")
    private LocalDateTime endDateTime;
    @Column(name = "notification_sent", nullable = false)
    private boolean notificationSent;

    public UniversalAlarm() {
    }

    public UniversalAlarmPK getUniversalAlarmPK() {
        return universalAlarmPK;
    }

    public void setUniversalAlarmPK(UniversalAlarmPK universalAlarmPK) {
        this.universalAlarmPK = universalAlarmPK;
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

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(LocalDateTime endDateTime) {
        this.endDateTime = endDateTime;
    }

    public boolean isNotificationSent() {
        return notificationSent;
    }

    public void setNotificationSent(boolean notificationSent) {
        this.notificationSent = notificationSent;
    }
}