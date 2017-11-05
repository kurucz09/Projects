package team;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;

import task.Task;
import task.TaskStatus;

/**
 * This class acts like a database for storing the teams.
 * @author Bogdan
 *
 */
public class TeamDatabase extends Observable implements Serializable {
	
	private static final long serialVersionUID = -4064272723834364920L;
	public static TeamDatabase teamDatabaseInstance = new TeamDatabase();
	private List<Team> allTeams = new ArrayList<Team>();

	public TeamDatabase() {

	}

	/**
	 * Add a new team.
	 * @param newTeam the new team to be added.
	 */
	public void addteam(Team newTeam) {
		allTeams.add(newTeam);
		setChanged();
		notifyObservers();
	}
	
	/**
	 * Returns the first team of which name matches to the parameter.
	 * Pay attention, the team's name is not unique, if you want to get a specific team please use getTeamById.
	 * @param tmName the name of the team
	 * @return
	 */
	public Team getTeam(String tmName) {
		for (Team t : allTeams) {
			if (t.getTeamName().contentEquals(tmName))
				return t;
		}
		return null;
	}

	public List<Team> getTeams() {
		return this.allTeams;
	}

	public void setTeams(List<Team> lst) {
		this.allTeams = lst;
	}

	/**
	 * Get the team with the specified id.
	 * @param id the id of searched team.
	 * @return The team with that id.
	 */
	public Team getTeamById(int id) {
		for (Team t : allTeams) {
			if (t.getId() == id)
				return t;
		}
		return null;
	}

	/**
	 * Searches a team based on it's id and name
	 * @param name the name of the team
	 * @param id the id of the team
	 * @return true if the team exists.
	 */
	public boolean searchTeam(String name, int id) {
		boolean teamExists = false;
		for (Team t : allTeams) {
			if (t.getTeamId() == id && t.getTeamName().contentEquals(name)) {
				teamExists = true;
			}
		}
		return teamExists;
	}

	/**
	 * Deletes the team with the specified name and id.
	 * @param name the name of the team.
	 * @param id the id of the team.
	 * @return true if the team was found and deleted.
	 */
	public boolean deleteTeam(String name, int id) {
		boolean teamDeleted = false;
		for (Team t : allTeams) {
			if (t.getTeamId() == id && t.getTeamName().contentEquals(name)) {
				allTeams.remove(t);
				teamDeleted = true;
			}
		}
		return teamDeleted;
	}
	
	/**
	 * Prints in console all teams.
	 */
	public void printAllTeams() {
		System.out.println("THERE ARE : " + allTeams.size() + " teams");
		for (Team t : allTeams) {
			System.out.println("Team " + t.getTeamName() + " " + "ID " + t.getTeamId() + " " + "ADMIN " + t.getAdmin());
		}
	}

	/**
	 * Removes a user from the group specified by the id and the group name
	 * the need of using the Iterator appears as we make concurrent modifications on items
	 * @param id the id of the group
	 * @param user the user name which will be removed
	 * @param group the group's name
	 */
	public void remove(String id, String user, String group) {
		
		int newId = Integer.parseInt(id);
		Iterator<Team> ite = allTeams.iterator();
		while (ite.hasNext()) {
			Team groups = ite.next();
			if (groups.getId() == newId && groups.getTeamName().contentEquals(group)) {
				Iterator<TeamMember> ite2 = groups.getMembers().iterator();
				while (ite2.hasNext()) {
					TeamMember members = ite2.next();
					if (members.getAccount().getUserName().contentEquals(user)) {
						ite2.remove();
					}
				}

			}

		}
	}

	public static TeamDatabase getInstance() {
		return teamDatabaseInstance;
	}

	/**
	 * Searches for a user in the first team with that name.
	 * @param groupSelected the name of the team
	 * @param logged_in_user the user to be searched
	 * @return true is the user is in the team.
	 */
	public boolean searchUserInTeam(String groupSelected, String logged_in_user) {
		for (Team t : allTeams) {
			if (t.getId() == Integer.parseInt(groupSelected)) {
				for (TeamMember tm : t.getMembers()) {
					if (tm.getAccount().getUserName().contentEquals(logged_in_user)) {
						return true;
					}
				}
			}
		}
		return false;

	}

	/**
	 * Returns the task of which both the name and the author matches the parameters
	 * @param teamId team in which the task will be searched
	 * @param taskName the name of the task
	 * @param taskAuthor the author of the task.
	 * @return Task (if found).
	 */
	public Task getTask(String teamId, String taskName, String taskAuthor) {
		for (Team t : allTeams) {
			if (String.valueOf(t.getId()).contentEquals(teamId)) {
				for (Task ta : t.getTasks()) {
					if (ta.getTaskName().contentEquals(taskName)) {
						return ta;
					}
				}
			}
		}
		return null;
	}

	/**
	 * Adds a comment to a task.
	 * @param teamId the team in which the task will be searched
	 * @param taskName the task on which we place the comment
	 * @param taskAuthor the author of the task
	 * @param user the user which makes the comment.
	 * @param comment the comment which is made.
	 */
	public void addComment(String teamId, String taskName, String taskAuthor, String user, String comment) {
		for (Team t : allTeams) {
			if (String.valueOf(t.getId()).contentEquals(teamId)) {
				for (Task ta : t.getTasks()) {
					if (ta.getTaskName().contentEquals(taskName)) {
						ta.addComment(user + " : "+comment);
					}
				}
			}
		}
		
	}
	
	/**
	 * Changes the status of a task. Depending on the status we make extra computations.
	 * @param status the action which will be made (Submit,Quit,Resc,Take).
	 * @param taskSelected the name of the task
	 * @param logged_in_user the logged in user
	 * @param teamId the id of the team
	 * @param daysToAdd the days to be added( optional ,just for Resc Status).
	 */
	public void changeStatus(String status, String taskSelected, String logged_in_user, String teamId, String daysToAdd) {

		for (Team t : allTeams) {
			if (String.valueOf(t.getId()).contentEquals(teamId)) {

				for (Task ta : t.getTasks()) {
					if (ta.getTaskName().contentEquals(taskSelected) && ta.getWorkingPers().contentEquals(logged_in_user)) {	
						if(status.contentEquals("SUBMIT")){
							ta.setStatus(TaskStatus.FINISHED);
						}
						if(status.contentEquals("QUIT")){
							ta.setStatus(TaskStatus.WAITING_USER);
							ta.setWorkingPers("N?A");
						}
						if(status.contentEquals("RESC")){
		
							Calendar calendar = Calendar.getInstance();
					        calendar.setTime(ta.getDeadline());
					        calendar.add(Calendar.DAY_OF_YEAR, Integer.parseInt(daysToAdd));
					        Date deadline = calendar.getTime();
					        ta.setDeadline(deadline);
		
						}
						
					}
					if (ta.getTaskName().contentEquals(taskSelected)){
						if(status.contentEquals("TAKE")){
							ta.setStatus(TaskStatus.WORKING);
							ta.setWorkingPers(logged_in_user);
						}
					}
				}
			}
		}
		
		
	}
	
	/**
	 * Updates the name,description, priority of a specific task.
	 * @param teamId the team in which we search.
	 * @param taskSelected the name of the task.
	 * @param newName the new name.
	 * @param newDescription the new description.
	 * @param newPriority the new priority.
	 */
	public void updateTask(String teamId, String taskSelected, String newName, String newDescription, String newPriority) {
		for(Team t : allTeams){
			if(String.valueOf(t.getId()).contentEquals(teamId)){
				for(Task ta : t.getTasks()){
					if(ta.getTaskName().contentEquals(taskSelected)){
						if(!newName.contentEquals("")){
							ta.setTaskName(newName);
							System.out.println("UPDATED name WITH  " + newName);
							System.out.println(newName);
						}
						if(!newDescription.contentEquals("")){
							ta.setTaskDescription(newDescription);
							System.out.println("UPDATED comm WITH  " + newDescription);
							System.out.println(newDescription);
						}
						if(!newPriority.contentEquals("")){
							ta.setPriority(Integer.parseInt(newPriority));
							System.out.println("UPDATED WITH  " + newPriority);
							System.out.println(newPriority);
						}
					}
				}
			}
		}
	}

	/**
	 * Changes the admin for a team.
	 * @param id the id of the team
	 * @param newAdmin the name of the new admin.
	 */
	public void changeAdmin(String id, String newAdmin) {
		for (Team t : allTeams){
			if(String.valueOf(t.getId()).contentEquals(id)){
				t.setAdmin(newAdmin);
			}
		}
		
	}
	
	/**
	 * Deletes the group with the id given as parameter.
	 * @param id the id of the group.
	 */
	public void deleteGroup(String id) {
		Team tt = new Team();
		for(Team t : allTeams){
			if(String.valueOf(t.getId()).contentEquals(id)){
				tt = t;
			}
		}
		System.out.println("ASTA 1 " + allTeams.size());
		allTeams.remove(tt);
		System.out.println("ASTA 2 " + allTeams.size());
	}
	
	/**
	 * Edits the information about the group.
	 * @param id the group on which changes will be made.
	 * @param name the new name.
	 * @param desc the new description.
	 * @param privacy the new privacy.
	 */
	public void editGroup(String id, String name, String desc, String privacy) {
		for(Team t : allTeams){
			if(String.valueOf(t.getId()).contentEquals(id)){
				if(!name.contentEquals("NO")){
					t.setTeamName(name);
				} else if (!desc.contentEquals("NO")){
					t.setTeamDescription(desc);
				} else if (!privacy.contentEquals("NO")){
					if (privacy.contentEquals("PUBLIC")){
						t.setPrivacy(Privacy.PUBLIC);
					} else if(privacy.contentEquals("PRIVATE")){
						t.setPrivacy(Privacy.PRIVATE);
					}
				}
			}
		}
	}

}
