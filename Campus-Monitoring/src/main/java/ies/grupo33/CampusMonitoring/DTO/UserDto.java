package ies.grupo33.CampusMonitoring.DTO;

import java.io.Serializable;
import java.util.Collection;

import ies.grupo33.CampusMonitoring.Model.Local;

public class UserDto implements Serializable {
	
	private String username;
	private String email;
	private String name;
	private Collection<Local> locals;
	private boolean admin;
	
	
	public UserDto(String username, String email, String name, Collection<Local> locals, boolean admin) {
		this.username = username;
		this.email = email;
		this.name = name;
		this.locals = locals;
		this.admin = admin;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Collection<Local> getLocals() {
		return locals;
	}


	public void setLocals(Collection<Local> locals) {
		this.locals = locals;
	}


	public boolean isAdmin() {
		return admin;
	}


	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	

}
