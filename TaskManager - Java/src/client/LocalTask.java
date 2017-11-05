package client;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import task.Task;
import team.Account;

/**
 * The purpose of this class is to map the personal agenda. 
 * As our application incorporates such a function we need a way to store the personal tasks
 * @author Alex & Bogdan
 *
 */
public class LocalTask implements Serializable {
	
	private static final long serialVersionUID = 3515849166847813102L;
	private Account user;
	private List<Task> localTasks = new ArrayList<Task>();
	
	/**
	 * Constructor ( used mainly for the Serializable)
	 */
	public LocalTask(){
		
	}
	
	/**
	 * Get the user of whom are the tasks
	 * @return The user of type Account
	 */
	public Account getUser() {
		return user;
	}

	/** 
	 * Set the person for the tasks
	 * @param user the account to which the tasks will be assigned
	 */
	public void setUser(Account user) {
		this.user = user;
	}
	
	/**
	 * Get the list of personal tasks
	 * @return List of tasks representing the logged in user's personal tasks
	 */
	public List<Task> getLocalTasks() {
		return localTasks;
	}

	/**
	 * Set users tasks
	 * @param localTasks = The list of tasks to be set.
	 */
	public void setLocalTasks(List<Task> localTasks) {
		this.localTasks = localTasks;
	}

}
