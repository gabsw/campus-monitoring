package ies.grupo33.CampusMonitoring.Stats;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDate;

@Embeddable
public class WeatherStatsPK implements Serializable {
    @Column(name = "localName", nullable = false)
    private String localName;
    @Column(name = "date", nullable = false)
    private LocalDate date;

    public WeatherStatsPK(String localName, LocalDate date) {
        this.localName = localName;
        this.date = date;
    }

    public String getLocalName() {
        return localName;
    }

    public void setLocalName(String localName) {
        this.localName = localName;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "WeatherStatsPK{" +
                "localName='" + localName + '\'' +
                ", date=" + date +
                '}';
    }
}
