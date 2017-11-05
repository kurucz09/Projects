package com.stupariu.DAO;

import java.util.Collection;

import com.stupariu.Entity.User;

public interface UserDAO {

	Collection <User> getAllUsers();
	
	User getUserById(int id);
	
	User getUserByUsername(String username);
	
	void updateUser(User user);
	
	void deleteUser(String id);
	
	void insertUser(User user);

	User checkUsernameAndPassword(String username, String password);
	
}
