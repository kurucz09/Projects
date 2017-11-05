package team;

import java.io.Serializable;

public class Account implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3060086876834558488L;
	private String password;
	private String username;
	private int wrigths;
	
	public Account(){
		
	}
	
	public Account(String username,String password,int wrights){
		this.setWrigths(wrights);
		this.setPassword(password);
		this.setUserName(username);
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return username;
	}

	public void setUserName(String username) {
		this.username = username;
	}

	public int getWrigths() {
		return wrigths;
	}
	public void setWrigths(int wrights){
		this.wrigths = wrights;
	}
}
