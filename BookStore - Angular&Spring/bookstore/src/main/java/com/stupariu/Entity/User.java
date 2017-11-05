package com.stupariu.Entity;

import javax.xml.bind.annotation.XmlAccessOrder;
import javax.xml.bind.annotation.XmlAccessorOrder;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.stupariu.Enums.UserType;
import com.sun.xml.internal.txw2.annotation.XmlElement;

@XmlType(propOrder={"id","username", "password","userType"})
@XmlAccessorOrder(XmlAccessOrder.ALPHABETICAL)
@XmlRootElement(name = "User")
public class User {
	
	private int id;
	private String username;
	private String password;
	private UserType userType;
	
	public User(){
		
	}

	public User(int id,String username, String password, UserType userType) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.userType = userType;
	}

	
	
	public int getId() {
		return id;
	}

	@XmlElement
	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	@XmlElement
	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	@XmlElement
	public void setPassword(String password) {
		this.password = password;
	}

	public UserType getUserType() {
		return userType;
	}

	@XmlElement
	public void setUserType(UserType userType) {
		this.userType = userType;
	}
	
	
	
}
