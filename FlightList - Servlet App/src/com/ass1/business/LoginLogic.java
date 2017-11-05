package com.ass1.business;

import java.util.List;

import org.hibernate.cfg.Configuration;

import com.ass1.da.UserDAO;
import com.ass1.da.UserEntity;

public class LoginLogic {
	
	private UserDAO userDAO;
	
	public LoginLogic(){
		userDAO = new UserDAO(new Configuration().configure().buildSessionFactory());
	}
	
	public UserEntity registerUser(UserEntity user){
		return userDAO.addUser(user);
	}

	public UserEntity logIn(String username, String password) {
		// TODO Auto-generated method stub
		if (username == null || password == null) {
			return null;
		}
		List<UserEntity> users= userDAO.getAllUsers();
		if(users == null){
			return null;
		}
		for(UserEntity user : users){
			if(user.getUsername().contentEquals(username)){
				if(user.getPassword().contentEquals(password)){
					return user;
				}
			}
		}
		return null;
	}
}
