package com.stupariu.DAO;

import static com.stupariu.Constants.UsersURL;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.springframework.stereotype.Repository;

import com.stupariu.Entity.User;
import com.stupariu.Entity.UsersList;

@Repository("userXML")
public class UserDAO {

	public UserDAO() {

	}
	
	private void writeUsersToXML(UsersList usersList){
		try{
			File users = new File(UsersURL);
			JAXBContext jaxbContext = JAXBContext.newInstance(UsersList.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			jaxbMarshaller.marshal(usersList, users);
		}catch (JAXBException e) {
			e.printStackTrace();
		}
	}
	
	private UsersList readUsersFromXML(){
		UsersList usersList = new UsersList();
		try {

			File file = new File(UsersURL);
			JAXBContext jaxbContext = JAXBContext.newInstance(UsersList.class);

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			usersList = (UsersList) jaxbUnmarshaller.unmarshal(file);

		  } catch (JAXBException e) {
			e.printStackTrace();
		  }
		return usersList;
	}

	public UsersList getAllUsers() {
		UsersList usersList = readUsersFromXML();
		return usersList;
	}

	public User getUserByUsername(String username) {
		UsersList usersList = readUsersFromXML();
		for(User user : usersList.getUsers()){
			if(user.getUsername().contentEquals(username)){
				return user;
			}
		}
		return null;
	}

	public void updateUser(User newUser) {
		UsersList usersList = readUsersFromXML();
		for(User user : usersList.getUsers()){
			if(user.getId() == newUser.getId()){
				user.setUsername(newUser.getUsername());
				user.setPassword(newUser.getPassword());
				user.setUserType(newUser.getUserType());
			}
		}
		writeUsersToXML(usersList);
	}

	public void deleteUser(String username) {
		UsersList usersList = readUsersFromXML();
		User userToDelete = new User();
		for(User user : usersList.getUsers()){
			if(user.getUsername().contentEquals(username)){
				userToDelete = user;
			}
		}
		usersList.getUsers().remove(userToDelete);
		writeUsersToXML(usersList);
	}

	public void insertUser(User user) {
		UsersList usersList = readUsersFromXML();
		usersList.getUsers().add(user);
		writeUsersToXML(usersList);
	}

	public User checkUsernameAndPassword(String username) {
		return getUserByUsername(username);
	}
}
