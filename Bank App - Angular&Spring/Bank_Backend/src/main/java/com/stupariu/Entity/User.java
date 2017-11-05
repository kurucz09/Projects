package com.stupariu.Entity;

import com.stupariu.enums.LogginState;

public class User {
	
	private int id;
	private String name;
	private String username;
	private String email;
	private String password;
	private LogginState logginState;
	
	public User(int id, String name, String username, String email, String password, LogginState logginState) {
		this.id = id;
		this.name = name;
		this.username = username;
		this.email = email;
		this.password = password;
		this.logginState = logginState;
	}
	
	public User(){
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LogginState getLogginState() {
		return logginState;
	}

	public void setUserType(LogginState logginState) {
		this.logginState = logginState;
	}
	
	
	
}
