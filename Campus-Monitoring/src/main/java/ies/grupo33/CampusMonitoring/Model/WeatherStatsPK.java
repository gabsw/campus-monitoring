package ies.grupo33.CampusMonitoring.Model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Embeddable
public class WeatherStatsPK implements Serializable {
    @Column(name = "local_name", nullable = false)
    private String localName;
    @Column(name = "date", nullable = false)
    private LocalDate date;

    public WeatherStatsPK() {
    }

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WeatherStatsPK that = (WeatherStatsPK) o;
        return localName.equals(that.localName) &&
                date.equals(that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(localName, date);
    }

    @Override
    public String toString() {
        return "WeatherStatsPK{" +
                "localName='" + localName + '\'' +
                ", date=" + date +
                '}';
    }
}
