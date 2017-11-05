package technic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import team.TeamMember;

/**
 * This class acts like a database for holding the RegisteredUsers.
 * 
 * @author Alex
 *
 */
public class RegisteredUser extends Observable implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -150478941117032472L;
	public static RegisteredUser registeredUserInstance = new RegisteredUser();
	private List<TeamMember> allMembers = new ArrayList<TeamMember>();

	public RegisteredUser() {

	}

	/**
	 * Add a new user to database
	 * 
	 * @param newMember
	 *            the member to be added.
	 */
	public void addUser(TeamMember newMember) {
		allMembers.add(newMember);
		setChanged();
		notifyObservers();
	}

	public List<TeamMember> getMembers() {
		return this.allMembers;
	}

	public void setMembers(List<TeamMember> lst) {
		this.allMembers = lst;
	}

	public TeamMember getUser() {
		return null;
	}

	/**
	 * Returns a the user with his username provided as argument.
	 * 
	 * @param usrN
	 *            the user name.
	 * @return the user if exists , null if not.
	 */
	public TeamMember getUserByName(String usrN) {
		for (TeamMember t : allMembers) {
			if (t.getAccount().getUserName().contentEquals(usrN)) {
				return t;
			}
		}
		return null;
	}

	/**
	 * Checks only for the existance of a specific user.
	 * 
	 * @param username
	 *            the username to be checked
	 * @return true if user exists.
	 */
	public boolean searchUsername(String username) {
		boolean usernameExists = false;
		for (int i = 0; i < allMembers.size(); i++) {
			if (username.contentEquals(allMembers.get(i).getAccount().getUserName())) {
				usernameExists = true;
			}
		}
		return usernameExists;
	}

	/**
	 * Searches for a email given as parameter.
	 * 
	 * @param email
	 *            the email to be searched.
	 * @return True if the email exists.
	 */
	public boolean searchEmail(String email) {
		boolean emailExists = false;
		for (int i = 0; i < allMembers.size(); i++) {
			if (email.contentEquals(allMembers.get(i).getEmail())) {
				emailExists = true;
			}
		}
		return emailExists;
	}

	/**
	 * Searches an account based on username and password
	 * @param username the username to be searched
	 * @param password the password.
	 * @return The member if exists, otherwise null.
	 */
	public TeamMember searchAccount(String username, String password) {
		TeamMember foundMember = null;
		for (TeamMember t : allMembers) {
			if (t.getAccount().getPassword().contentEquals(password)
					&& t.getAccount().getUserName().contentEquals(username)) {
				foundMember = t;
			}
		}
		return foundMember;
	}
	
	/**
	 * Searches an account based on username
	 * @param username the username to be searched
	 * @return The member if exists, otherwise null.
	 */
	public TeamMember searchAccount(String username) {
		TeamMember foundMember = null;
		for (TeamMember t : allMembers) {
			if (t.getAccount().getUserName().contentEquals(username)) {
				foundMember = t;
			}
		}
		return foundMember;
	}

	/**
	 * Prints in console all the members.
	 */
	public void printAllMembers() {
		System.out.println("THERE ARE : " + allMembers.size());
		for (int i = 0; i < allMembers.size(); i++) {
			System.out.println("User :: " + allMembers.get(i).getAccount().getUserName() + " Pass :: "
					+ allMembers.get(i).getAccount().getPassword());
		}
	}

	public static RegisteredUser getInstance() {
		return registeredUserInstance;
	}

}
