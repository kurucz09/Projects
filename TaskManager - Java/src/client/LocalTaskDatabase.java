package client;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import task.Task;
import task.TaskStatus;
import team.Account;

/**
 * This class stores all the personal tasks 
 * @author Alex & Bogdan
 *
 */
public class LocalTaskDatabase extends Observable implements Serializable {
	
	
	private static final long serialVersionUID = 5764535879070088561L;
	public static LocalTaskDatabase LocalTaskDatabaseInstance = new LocalTaskDatabase();
	private List <LocalTask> allLocalTasks = new ArrayList<LocalTask>();

	public LocalTaskDatabase(){
		
	}
	
	/**
	 * Add a new user in the database
	 * @param ac = The account which will be added. The add of a new user is made by creating a personal task of which working person is the 
	 * user given as parameter
	 */
	public void addUser(Account ac){
		LocalTask lt = new LocalTask();
		lt.setUser(ac);
		allLocalTasks.add(lt);
		setChanged();
		notifyObservers();
	}
	
	/**
	 * This method adds to a specific user a new task. The user is searched on the username ( Unique). 
	 * @param username = The user to which we will add the task
	 * @param task = Task to be added
	 */
	public void addTask(String username, Task task){
		for (LocalTask lt : allLocalTasks){
			if (lt.getUser().getUserName().contentEquals(username)){
				lt.getLocalTasks().add(task);
				setChanged();
				notifyObservers();
			}
		}
	}
	
	/**
	 * This method returns a list of tasks for the given user
	 * @param username The user of whom tasks we want
	 * @return The list of personal tasks
	 */
	public List<Task> getUserLocalTasks (String username){
		for(LocalTask lt : allLocalTasks){
			if (lt.getUser().getUserName().contentEquals(username)){
				return lt.getLocalTasks();
			}
		}
		return null;
	}
	
	/**
	 * This method returns the whole list of tasks
	 * @return List of tasks of type LocaTasks.
	 */
	public List <LocalTask> getAllLocalTasks() {
		return allLocalTasks;
	}
	
	/**
	 * Sets the list of local tasks
	 * @param allLocalTasks the tasks to be set .
	 */
	public void setAllLocalTasks(List <LocalTask> allLocalTasks) {
		this.allLocalTasks = allLocalTasks;
	}
	
	/**
	 * Return the instance of this LocalTaskDatabase
	 * @return The instance of LocalTaskDatabase
	 */
	public static LocalTaskDatabase getInstance(){
		return LocalTaskDatabaseInstance;
	}
	
	/**
	 * Prints the local tasks database.
	 */
	public void printLocalDatabase(){
		for (LocalTask lt : allLocalTasks){
			System.out.println("USER :: " + lt.getUser().getUserName());
			for (Task t : lt.getLocalTasks()){
				System.out.println("TASK :: " + t.getTaskName());
			}
		}
	}

	/**
	 * This method performs the action of quitting a task
	 * The new status of the task will be CANCELED
	 * @param task The task to be quit
	 * @param user The user from which the task will be quit
	 */
	public void quit(String task, String user) {
		for (LocalTask lt : allLocalTasks){
			if(lt.getUser().getUserName().contentEquals(user)){
				for (Task t : lt.getLocalTasks()){
					if(t.getTaskName().contentEquals(task)){
						t.setStatus(TaskStatus.CANCELED);
					}
				}
			}
		}
		
	}

	/**
	 * In this method the information about the task is updated
	 * @param taskSelected which task to be updated
	 * @param newTitle the new title of the task
	 * @param newDescription new description of the task
	 * @param priority the priority for the task
	 * @param logged_in_user the user of which task will be updated
	 */
	public void update(String taskSelected, String newTitle, String newDescription, String priority, String logged_in_user) {
		for (LocalTask lt : allLocalTasks){
			if(lt.getUser().getUserName().contentEquals(logged_in_user)){
				for (Task t : lt.getLocalTasks()){
					if(t.getTaskName().contentEquals(taskSelected)){
						t.setTaskName(newTitle);
						t.setTaskDescription(newDescription);
						t.setPriority(Integer.parseInt(priority));
					}
				}
			}
		}
	}

	/**
	 * This method adds extra time to a task
	 * @param taskSelected the task to which the time will be added
	 * @param logged_in_user the user of which task suffers modifications
	 * @param input how much time is added ( in days).
	 */
	public void addTime(String taskSelected, String logged_in_user, String input) {
		
		for (LocalTask lt : allLocalTasks){
			if(lt.getUser().getUserName().contentEquals(logged_in_user)){
				for (Task t : lt.getLocalTasks()){
					if(t.getTaskName().contentEquals(taskSelected)){
						t.addTime(Integer.parseInt(input));
					}
				}
			}
		}
	}

	/**
	 * This functions modify the status of the task from working to FINISHED
	 * @param taskSelected the task to be modified 
	 * @param logged_in_user the user of whom task is modified
	 */
	public void submit(String taskSelected, String logged_in_user) {
		for (LocalTask lt : allLocalTasks){
			if(lt.getUser().getUserName().contentEquals(logged_in_user)){
				for (Task t : lt.getLocalTasks()){
					if(t.getTaskName().contentEquals(taskSelected)){
						t.setStatus(TaskStatus.FINISHED);
						
					}
				}
			}
		}

	}
	/**
	 * This function adds a comment to a task
	 * @param logged_in_user the user of whom task is modified
	 * @param taskSelected the tasks to which the comment will be added.
	 * @param input The text to be added as comment.
	 */
	public void addComment(String logged_in_user, String taskSelected, String input) {
		for (LocalTask lt : allLocalTasks){
			if(lt.getUser().getUserName().contentEquals(logged_in_user)){
				for (Task t : lt.getLocalTasks()){
					if(t.getTaskName().contentEquals(taskSelected)){
						t.addComment(input);
					}
				}
			}
		}
	}
}
