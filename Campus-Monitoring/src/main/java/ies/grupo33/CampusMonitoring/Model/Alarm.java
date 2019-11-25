package ies.grupo33.CampusMonitoring.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "alarm")
public class Alarm {
 
	private long sensor_id;
	private String date_time;
    private String parameter_exceeded;
    private double value_exceeded;
 
    public Alarm() {
  
    }
    
    public Alarm(String date_time, String parameter_exceeded, double value_exceeded) {
        this.date_time = date_time;
        this.parameter_exceeded = parameter_exceeded;
        this.value_exceeded = value_exceeded;
    }
    
    @Column(name = "sensor_id")
	public long getSensor_id() {
		return sensor_id;
	}

	public void setSensor_id(long id) {
		sensor_id = id;
	}
    
	@Column(name = "date_time")
	public String getDate_time() {
		return date_time;
	}

	public void setDate_time(String time) {
		date_time = time;
	}

    @Column(name = "parameter_exceeded")
	public String getParameter_exceeded() {
		return parameter_exceeded;
	}

	public void setParameter_exceeded(String parameter) {
		parameter_exceeded = parameter;
	}

	@Column(name = "value_exceeded")
	public double getValue_exceeded() {
		return value_exceeded;
	}

	public void setValue_exceeded(double value) {
		value_exceeded = value;
	}

	@Override
	public String toString() {
		return "Alarme [Sensor_id=" + sensor_id + ", Parameter_exceeded=" + parameter_exceeded + ", Date_time="
				+ date_time + ", Value_exceeded=" + value_exceeded + "]";
	}
}
