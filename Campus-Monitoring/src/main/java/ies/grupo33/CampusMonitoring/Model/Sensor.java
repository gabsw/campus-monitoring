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
	private long id;
	@Column(name = "local_name", nullable = false)
	private String localName;
	
	public Sensor() {
	
	}
	
	public Sensor(long id, String localName) {
		this.id = id;
		this.localName = localName;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public String getLocalName() {
		return localName;
	}

	public void setLocalName(String localName) {
		this.localName = localName;
	}

	@Override
	public String toString() {
		return "Sensor [id=" + id + ", localName=" + localName + "]";
	}
}
