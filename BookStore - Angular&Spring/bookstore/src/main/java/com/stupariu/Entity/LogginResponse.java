package com.stupariu.Entity;

import com.stupariu.Enums.UserType;

public class LogginResponse {

	private String username;
	private UserType userType;
	
	public LogginResponse(){
		
	}

	public LogginResponse(String username, UserType userType) {
		super();
		this.username = username;
		this.userType = userType;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	
	
	
}
