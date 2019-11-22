package ies.grupo33.CampusMonitoring.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.text.ParseException;

@Entity
@Table(name = "Sensor")
public class Sensor {
	
	private long id;
	private String local_name;
	
	public Sensor(long id, String local_name) {
		this.id = id;
		this.local_name = local_name;
	}

	@Column(name = "id", nullable = false)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(name = "local_name", nullable = false)
	public String getLocal_name() {
		return local_name;
	}

	public void setLocal_name(String local_name) {
		this.local_name = local_name;
	}

	@Override
	public String toString() {
		return "Sensor [id=" + id + ", local_name=" + local_name + "]";
	}
	
	
}
