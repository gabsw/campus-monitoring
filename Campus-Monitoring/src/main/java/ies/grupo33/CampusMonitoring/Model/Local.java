package ies.grupo33.CampusMonitoring.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "local")
public class Local {

	
	private String name;
    private long max_temp_limit;
    private long min_temp_limit;
    private long max_hum_limit;
    private long min_hum_limit;
    private long max_co2_limit;
    private long min_co2_limit;
    
 
    public Local() {
  
    }
    
    public Local(String name, long max_temp_limit, long min_temp_limit, long max_hum_limit, long min_hum_limit, long max_co2_limit, long min_co2_limit) {
        this.name = name;
        this.max_temp_limit = max_temp_limit;
        this.min_temp_limit = min_temp_limit;
        this.max_hum_limit = max_hum_limit;
        this.min_hum_limit = max_hum_limit;
        this.max_co2_limit = max_co2_limit;
        this.min_co2_limit = min_co2_limit;
    }

    @Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "max_temp_limit")
	public long getMax_temp_limit() {
		return max_temp_limit;
	}

	public void setMax_temp_limit(long max_temp_limit) {
		this.max_temp_limit = max_temp_limit;
	}

	@Column(name = "min_temp_limit")
	public long getMin_temp_limit() {
		return min_temp_limit;
	}

	public void setMin_temp_limit(long min_temp_limit) {
		this.min_temp_limit = min_temp_limit;
	}

	@Column(name = "max_hum_limit")
	public long getMax_hum_limit() {
		return max_hum_limit;
	}

	public void setMax_hum_limit(long max_hum_limit) {
		this.max_hum_limit = max_hum_limit;
	}

	@Column(name = "min_hum_limit")
	public long getMin_hum_limit() {
		return min_hum_limit;
	}

	public void setMin_hum_limit(long min_hum_limit) {
		this.min_hum_limit = min_hum_limit;
	}

	@Column(name = "max_co2_limit")
	public long getMax_co2_limit() {
		return max_co2_limit;
	}

	public void setMax_co2_limit(long max_co2_limit) {
		this.max_co2_limit = max_co2_limit;
	}

	@Column(name = "min_co2_limit")
	public long getMin_co2_limit() {
		return min_co2_limit;
	}

	public void setMin_co2_limit(long min_co2_limit) {
		this.min_co2_limit = min_co2_limit;
	}

	@Override
	public String toString() {
		return "Local [name=" + name + ", max_temp_limit=" + max_temp_limit + ", min_temp_limit=" + min_temp_limit
				+ ", max_hum_limit=" + max_hum_limit + ", min_hum_limit=" + min_hum_limit + ", max_co2_limit="
				+ max_co2_limit + ", min_co2_limit=" + min_co2_limit + "]";
	}
    
    
}
