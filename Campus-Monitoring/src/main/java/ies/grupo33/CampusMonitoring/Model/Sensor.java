package ies.grupo33.CampusMonitoring.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SENSOR" , schema = "campus_monitoring")
public class Sensor {
	@Id
	@Column(name = "id", nullable = false)
	private Long id;
	@Column(name = "local_name", nullable = false)
	private String localName;
	@Column(name = "hardware", nullable = false)
	private String hardware;
	
	public Sensor() {
	
	}

	public Sensor(Long id, String localName, String hardware) {
		this.id = id;
		this.localName = localName;
		this.hardware = hardware;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getLocalName() {
		return localName;
	}

	public void setLocalName(String localName) {
		this.localName = localName;
	}

	public String getHardware() {
		return hardware;
	}

	public void setHardware(String hardware) {
		this.hardware = hardware;
	}

	@Override
	public String toString() {
		return "Sensor{" +
				"id=" + id +
				", localName='" + localName + '\'' +
				", hardware='" + hardware + '\'' +
				'}';
	}
}
