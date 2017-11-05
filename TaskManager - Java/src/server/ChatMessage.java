package server;

import java.io.Serializable;

import client.LocalTaskDatabase;
import task.Task;
import team.Team;
import team.TeamDatabase;
import team.TeamMember;
import technic.RegisteredUser;

/**
 * This class is our communication protocol First argument is the type of the
 * message an the others, depending on the type of the message is the
 * information For being clear and fast in programming the class has some
 * attributes in order to satisfy the communication neccessities.
 * 
 * @author Alex & Bogdan
 *
 */
public class ChatMessage implements Serializable {

	protected static final long serialVersionUID = 1112122200L;

	private MessageType type;
	private String message;
	private String message2;
	private String message3;
	private String message4;
	private String message5;
	private TeamMember teamMember;
	private Team team;
	private TeamDatabase teamDatabase;
	private LocalTaskDatabase localTaskDatabase;
	private RegisteredUser regUser;
	private Task task;

	private boolean not_status;

	public ChatMessage(MessageType type, TeamMember teamMember) {
		this.type = type;
		this.teamMember = teamMember;
	}

	public ChatMessage(MessageType type, String message) {
		this.type = type;
		this.message = message;
	}

	public ChatMessage(MessageType type, String message, String message2) {
		this.type = type;
		this.message = message;
		this.message2 = message2;
	}

	public ChatMessage(MessageType type, String message, String message2, String message3) {
		this.type = type;
		this.message = message;
		this.message2 = message2;
		this.message3 = message3;
	}

	public ChatMessage(MessageType type, Team team) {
		this.type = type;
		this.setTeam(team);
	}

	public ChatMessage(MessageType type, TeamDatabase td) {
		this.type = type;
		this.setTeamDatabase(td);
	}

	public ChatMessage(MessageType type, LocalTaskDatabase ltd) {
		this.type = type;
		this.setLocalTaskDatabase(ltd);
	}

	public ChatMessage(MessageType type, Task task, String message) {
		this.type = type;
		this.setTask(task);
		this.message = message;
	}

	public ChatMessage(MessageType type, RegisteredUser ru) {
		this.type = type;
		this.setRegisteredUser(ru);
	}

	public ChatMessage(MessageType updateTask, String taskSelected, String newTitle, String newDescription,
			String priority, String logged_in_user) {
		this.type = updateTask;
		this.message = taskSelected;
		this.message2 = newTitle;
		this.message3 = newDescription;
		this.message4 = priority;
		this.message5 = logged_in_user;
	}

	public ChatMessage(MessageType type, String txtId, String taskName, String taskAuthor, String taskStatus) {
		this.type = type;
		this.message = txtId;
		this.message2 = taskName;
		this.message3 = taskAuthor;
		this.message4 = taskStatus;
	}

	public ChatMessage(MessageType type, String s, Task task2) {
		this.type = type;
		this.task = task2;
		this.message = s;
	}

	public String getMessage3() {
		return this.message3;
	}

	public MessageType getType() {
		return type;
	}

	public String getMessage() {
		return message;
	}

	public String getMessage2() {
		return message2;
	}

	public TeamMember getTeamMember() {
		return teamMember;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public TeamDatabase getTeamDatabase() {
		return teamDatabase;
	}

	public void setTeamDatabase(TeamDatabase teamDatabase) {
		this.teamDatabase = teamDatabase;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public LocalTaskDatabase getLocalTaskDatabase() {
		return localTaskDatabase;
	}

	public void setLocalTaskDatabase(LocalTaskDatabase localTaskDatabase) {
		this.localTaskDatabase = localTaskDatabase;
	}

	public RegisteredUser getRegisteredUser() {
		return regUser;
	}

	public void setRegisteredUser(RegisteredUser ru) {
		this.regUser = ru;
	}

	public boolean isNot_status() {
		return not_status;
	}

	public void setNot_status(boolean not_status) {
		this.not_status = not_status;
	}

	public String getMessage4() {
		return message4;
	}

	public void setMessage4(String message4) {
		this.message4 = message4;
	}

	public String getMessage5() {
		return message5;
	}

	public void setMessage5(String message5) {
		this.message5 = message5;
	}

}
