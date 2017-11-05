package com.stupariu.Entity;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UsersList", propOrder = { "usersList" })
@XmlRootElement(name = "UsersList")
public class UsersList {
	
	@XmlElement(name = "User", required = true)
    private List<User> usersList = new ArrayList<User>();
	
	public UsersList(){
		
	}

	public List<User> getUsers() {
		return usersList;
	}

	public void setUsers(List<User> usersList) {
		this.usersList = usersList;
	}
	
	

}
