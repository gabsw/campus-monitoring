package ies.grupo33.CampusMonitoring.Model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Embeddable
public class UniversalAlarmPK implements Serializable {
    @Column(name = "local_name", nullable = false)
    private String localName;
    @Column(name = "start_date_time", nullable = false)
    private LocalDateTime startDateTime;
    @Column(name = "violation_parameter", nullable = false)
    private String violationParameter;

    public UniversalAlarmPK(String localName, LocalDateTime startDateTime, String violationParameter) {
        this.localName = localName;
        this.startDateTime = startDateTime;
        this.violationParameter = violationParameter;
    }

    public UniversalAlarmPK() {

    }

    public String getLocalName() {
        return localName;
    }

    public void setLocalName(String localName) {
        this.localName = localName;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UniversalAlarmPK that = (UniversalAlarmPK) o;
        return localName.equals(that.localName) &&
                startDateTime.equals(that.startDateTime) &&
                violationParameter.equals(that.violationParameter);
    }

    @Override
    public int hashCode() {
        return Objects.hash(localName, startDateTime, violationParameter);
    }
}