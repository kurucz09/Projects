package com.stupariu.Entity;

public class Log {
	private String id;
	private String username;
	private String activity;
	private String dateCreated;
	
	public Log(){
		
	}

	public Log(String id, String username, String activity, String dateCreated) {
		super();
		this.id = id;
		this.username = username;
		this.activity = activity;
		this.dateCreated = dateCreated;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getActivity() {
		return activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}

	public String getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}
	
	
}
