package com.stupariu.Entity;

import com.stupariu.enums.LogginState;

public class LoggedInUser {
	String username;
	String name;
	String email;
	LogginState logginState;
	public LoggedInUser(String username, String name, String email, LogginState logginState) {
		super();
		this.username = username;
		this.name = name;
		this.email = email;
		this.logginState = logginState;
	}
	public LoggedInUser() {
		// TODO Auto-generated constructor stub
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public LogginState getLogginState() {
		return logginState;
	}
	public void setLogginState(LogginState logginState) {
		this.logginState = logginState;
	}
	
	
	
	

	
}
