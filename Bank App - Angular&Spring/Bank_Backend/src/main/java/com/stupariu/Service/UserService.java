package com.stupariu.Service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.stupariu.DAO.UserDAO;
import com.stupariu.Entity.LoggedInUser;
import com.stupariu.Entity.User;
import com.stupariu.enums.LogginState;

@Service
public class UserService {

	@Autowired
	@Qualifier("userSQL")
	private UserDAO userDAO;

	public Collection<User> getAllUsers() {
		return this.userDAO.getAllUsers();
	}

	public User getUserById(int id) {
		return this.userDAO.getUserById(id);
	}

	public User getUserByUsername(String username) {
		return this.userDAO.getUserByUsername(username);
	}

	public void updateUser(User user) {
		this.userDAO.updateUser(user);
	}

	public void deleteUser(String id) {
		this.userDAO.deleteUser(id);
	}

	public void insertUser(User user) {
		this.userDAO.insertUser(user);
	}

	
	public LoggedInUser checkUsernameAndPassword(String username, String password) {

		User user = this.userDAO.checkUsernameAndPassword(username, password);
		LoggedInUser loggedInUser = new LoggedInUser();
		if (user.equals(null)) {
			loggedInUser.setName("not_found");
			loggedInUser.setEmail("not_found");
			loggedInUser.setUsername("not_found");
			loggedInUser.setLogginState(LogginState.notLogged);
		} else if(!user.equals(null)){
			loggedInUser.setName(user.getName());
			loggedInUser.setEmail(user.getEmail());
			loggedInUser.setUsername(user.getUsername());
			loggedInUser.setLogginState(user.getLogginState());
			System.out.println(user.getLogginState());
		}
		return loggedInUser;
	}

}
