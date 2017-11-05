package task;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Task implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3824434013038640857L;
	private String taskName;
	private String taskDescription;
	private String author;
	private TaskStatus status;
	private int priority;
	private Date dateCreated;
	private Date deadline;
	private List<String> comments = new ArrayList<String>();
	private String workingPerson;

	public Task() {

	}

	/**
	 * Task constructor
	 * 
	 * @param taskName
	 * @param taskDescription
	 * @param author
	 * @param status
	 * @param priority
	 *            [1 - 5]
	 * @param deadYear
	 *            type String "2017"
	 * @param deadMonth
	 *            type String "9"
	 * @param deadDay
	 *            type String "21"
	 */
	public Task(String taskName, String taskDescription, String author, TaskStatus status, int priority,
			String deadYear, String deadMonth, String deadDay, String wp) {
		setTaskName(taskName);
		setTaskDescription(taskDescription);
		setAuthor(author);
		setStatus(status);
		setPriority(priority);
		setDateCreated(new Date());
		setWorkingPers(wp);
		comments.add("AUTO-GENERATED :: Task created by " + author + " on : "
				+ new SimpleDateFormat("dd/mm/yyyy").format(getDateCreated()));

		int year = Integer.parseInt(deadYear);
		int month = Integer.parseInt(deadMonth) - 1;
		int day = Integer.parseInt(deadDay);
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month);
		cal.set(Calendar.DAY_OF_MONTH, day);
		deadline = cal.getTime();
		setDeadline(deadline);

	}

	public void setWorkingPers(String name) {
		this.workingPerson = name;
	}

	public String getWorkingPers() {
		return this.workingPerson;
	}

	public long getDaysToDeadline() {
		deadline = getDeadline();
		Date curDate = new Date();
		TimeUnit timeUnit = TimeUnit.DAYS;
		long diffInMillies = deadline.getTime() - curDate.getTime();
		return timeUnit.convert(diffInMillies, TimeUnit.MILLISECONDS);
	}

	/**
	 * Adds a comment to a task.
	 * 
	 * @param comment
	 *            = the comment to be added
	 */
	public void addComment(String comment) {
		comments.add(comment);
	}

	/**
	 * 
	 * @return The task name
	 */
	public String getTaskName() {
		return taskName;
	}

	/**
	 * Sets the task's name
	 * 
	 * @param taskName
	 *            the name to be set
	 */
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	/**
	 * 
	 * @return The description of the task
	 */
	public String getTaskDescription() {
		return taskDescription;
	}

	/**
	 * Set new description to the task
	 * 
	 * @param taskDescription
	 *            the new description to be set.
	 */
	public void setTaskDescription(String taskDescription) {
		this.taskDescription = taskDescription;
	}

	/**
	 * 
	 * @return Task's status
	 */
	public TaskStatus getStatus() {
		return status;
	}

	/**
	 * Sets the task's status.
	 * 
	 * @param status
	 *            The new status
	 */
	public void setStatus(TaskStatus status) {
		this.status = status;
	}

	/**
	 * 
	 * @return task priority
	 */
	public int getPriority() {
		return priority;
	}

	/**
	 * Sets the new priority.
	 * 
	 * @param priority
	 *            = The new priority.
	 */
	public void setPriority(int priority) {
		this.priority = priority;
	}
	/**
	 * 
	 * @return The date at which the task was created.
	 */
	public Date getDateCreated() {
		return dateCreated;
	}

	/**
	 * Sets a new date
	 * @param date = the new Date.
	 */
	public void setDateCreated(Date date) {
		this.dateCreated = date;
	}

	/**
	 * 
	 * @return The deadline for the task.
	 */
	public Date getDeadline() {
		return deadline;
	}
	
	/**
	 * Set new deadline.
	 * @param deadline = the new deadline.
	 */
	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	/**
	 * 
	 * @return The list of comments for the task.
	 */
	public List<String> getComments() {
		return comments;
	}

	/**
	 * Set a new list of comments
	 * @param comments = the list of comments.
	 */
	public void setComments(List<String> comments) {
		this.comments = comments;
	}

	/**
	 * 
	 * @return the author of the task.
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * Set new author.
	 * @param author the new author.
	 */
	public void setAuthor(String author) {
		this.author = author;
	}
	
	/**
	 * This function adds extra time to a task
	 * @param time (in days) to be added to the deadline.
	 */
	public void addTime(int time) {
		// TODO Auto-generated method stub
		Calendar cal = Calendar.getInstance();
		cal.setTime(deadline);
		cal.add(Calendar.DATE, time);
		deadline = cal.getTime();
		setDeadline(deadline);
		System.out.println(deadline);
	}

}
