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
    @Column(name = "status", nullable = false)
    private boolean status;
    @Column(name = "end_date_time")
    private LocalDateTime endDateTime;

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
}