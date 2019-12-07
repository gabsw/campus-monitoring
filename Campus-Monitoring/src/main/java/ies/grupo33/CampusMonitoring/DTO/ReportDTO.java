package ies.grupo33.CampusMonitoring.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;


public class ReportDTO implements Serializable {
    private String localName;
    private LocalDate startDate;
    private LocalDate endDate;
    private Double averageRating;
    private double averageAlarmsPerDay;
    private int numberOngoingAlarms;
    private int totalMaxTempAlarms;
    private int totalMinTempAlarms;
    private int totalMaxHumAlarms;
    private int totalMinHumAlarms;
    private int totalMaxCo2Alarms;

    public ReportDTO() {

    }

    public ReportDTO(String localName, LocalDate startDate, LocalDate endDate) {
        this.localName = localName;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getLocalName() {
        return localName;
    }

    public void setLocalName(String localName) {
        this.localName = localName;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(Double averageRating) {
        this.averageRating = averageRating;
    }

    public double getAverageAlarmsPerDay() {
        return averageAlarmsPerDay;
    }

    public void setAverageAlarmsPerDay(double averageAlarmsPerDay) {
        this.averageAlarmsPerDay = averageAlarmsPerDay;
    }

    public int getNumberOngoingAlarms() {
        return numberOngoingAlarms;
    }

    public void setNumberOngoingAlarms(int numberOngoingAlarms) {
        this.numberOngoingAlarms = numberOngoingAlarms;
    }

    public int getTotalMaxTempAlarms() {
        return totalMaxTempAlarms;
    }

    public void setTotalMaxTempAlarms(int totalMaxTempAlarms) {
        this.totalMaxTempAlarms = totalMaxTempAlarms;
    }

    public int getTotalMinTempAlarms() {
        return totalMinTempAlarms;
    }

    public void setTotalMinTempAlarms(int totalMinTempAlarms) {
        this.totalMinTempAlarms = totalMinTempAlarms;
    }

    public int getTotalMaxHumAlarms() {
        return totalMaxHumAlarms;
    }

    public void setTotalMaxHumAlarms(int totalMaxHumAlarms) {
        this.totalMaxHumAlarms = totalMaxHumAlarms;
    }

    public int getTotalMinHumAlarms() {
        return totalMinHumAlarms;
    }

    public void setTotalMinHumAlarms(int totalMinHumAlarms) {
        this.totalMinHumAlarms = totalMinHumAlarms;
    }

    public int getTotalMaxCo2Alarms() {
        return totalMaxCo2Alarms;
    }

    public void setTotalMaxCo2Alarms(int totalMaxCo2Alarms) {
        this.totalMaxCo2Alarms = totalMaxCo2Alarms;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReportDTO reportDTO = (ReportDTO) o;
        return localName.equals(reportDTO.localName) &&
                startDate.equals(reportDTO.startDate) &&
                endDate.equals(reportDTO.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(localName, startDate, endDate);
    }

    @Override
    public String toString() {
        return "ReportDTO{" +
                "localName='" + localName + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", averageRating=" + averageRating +
                ", averageAlarmsPerDay=" + averageAlarmsPerDay +
                ", numberOngoingAlarms=" + numberOngoingAlarms +
                ", totalMaxTempAlarms=" + totalMaxTempAlarms +
                ", totalMinTempAlarms=" + totalMinTempAlarms +
                ", totalMaxHumAlarms=" + totalMaxHumAlarms +
                ", totalMinHumAlarms=" + totalMinHumAlarms +
                ", totalMaxCo2Alarms=" + totalMaxCo2Alarms +
                '}';
    }
}
