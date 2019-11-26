package ies.grupo33.CampusMonitoring.Model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.data.annotation.Id;

@Entity
@Table(name = "alarm")
public class Alarm {
 
	@EmbeddedId
	private AlarmPK AlarmPK;
	@Column(name = "parameter_exceeded")
    private String parameter_exceeded;
	@Column(name = "value_exceeded")
    private double value_exceeded;
 
    public Alarm() {
  
    }
    
    public Alarm(AlarmPK AlarmPK, String parameter_exceeded, double value_exceeded) {
        this.AlarmPK = AlarmPK;
        this.parameter_exceeded = parameter_exceeded;
        this.value_exceeded = value_exceeded;
    }
    

    
	public AlarmPK getAlarmPK() {
		return AlarmPK;
	}

	public void setAlarmPK(AlarmPK alarmPK) {
		AlarmPK = alarmPK;
	}

	public String getParameter_exceeded() {
		return parameter_exceeded;
	}

	public void setParameter_exceeded(String parameter) {
		parameter_exceeded = parameter;
	}

	
	public double getValue_exceeded() {
		return value_exceeded;
	}

	public void setValue_exceeded(double value) {
		value_exceeded = value;
	}

	@Override
	public String toString() {
		return "Alarm [AlarmPK=" + AlarmPK + ", parameter_exceeded=" + parameter_exceeded + ", value_exceeded="
				+ value_exceeded + "]";
	}

	
	
	
}
