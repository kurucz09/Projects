package com.stupariu.Service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.stupariu.DAO.UserDAO;
import com.stupariu.Entity.LogginResponse;
import com.stupariu.Entity.User;
import com.stupariu.Enums.UserType;

@Service
public class UserService {

	@Autowired
	@Qualifier("userXML")
	private UserDAO userDAO;

	public Collection<User> getAllUsers() {
		Collection<User> collect = this.userDAO.getAllUsers().getUsers();
		return collect;
	}

	public User getUserByUsername(String username) {
		return this.userDAO.getUserByUsername(username);
	}

	public void updateUser(User user) {
		this.userDAO.updateUser(user);
	}

	public void deleteUser(String username) {
		this.userDAO.deleteUser(username);
	}

	public void insertUser(User user) {
		this.userDAO.insertUser(user);
	}

	public LogginResponse checkUsernameAndPassword(String username, String password) {
		User user = this.userDAO.checkUsernameAndPassword(username);
		LogginResponse logResp = new LogginResponse ();
	
		if(user == null){
			logResp.setUsername("notFound");
			logResp.setUserType(UserType.notFound);
		} else if (user.getPassword().contentEquals(password)){
			if (user.getUserType().equals(UserType.administrator)) {
				logResp.setUsername(username);
				logResp.setUserType(UserType.administrator);
			} else if (user.getUserType().equals(UserType.regularUser)) {
				logResp.setUsername(username);
				logResp.setUserType(UserType.regularUser);
			} else {
				logResp.setUsername("notFound");
				logResp.setUserType(UserType.notFound);
			}
		} else {
			logResp.setUsername("notFound");
			logResp.setUserType(UserType.notFound);
		}
		return logResp;
		
	}

}
