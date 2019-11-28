package ies.grupo33.CampusMonitoring.Model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "UNIVERSAL_ALARM")
public class Alarm {
    @EmbeddedId
    private AlarmPK alarmPK;
    @Column(name = "violation_type", nullable = false)
    private String violation_type;
    @Column(name = "violation_value", nullable = false)
    private double violation_value;
    @Column(name = "status", nullable = false)
    private boolean status;

    public Alarm() {
    }

    public AlarmPK getAlarmPK() {
        return alarmPK;
    }

    public void setAlarmPK(AlarmPK alarmPK) {
        this.alarmPK = alarmPK;
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

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Alarm{" +
                "alarmPK=" + alarmPK +
                ", violation_type='" + violation_type + '\'' +
                ", violation_value=" + violation_value +
                ", status=" + status +
                '}';
    }
}