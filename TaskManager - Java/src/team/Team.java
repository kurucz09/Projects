package team;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import task.Task;

/**
 * 
 * @author Alex & Bogdan
 *
 */
public class Team implements Serializable {

	private static final long serialVersionUID = 7162855084728066715L;
	private int id;
	private String teamName;
	private String teamDescription;
	private String admin;
	private Privacy privacy;
	private List<TeamMember> members = new ArrayList<TeamMember>();
	private List<Task> tasks = new ArrayList<Task>();

	public Team() {

	}

	public Team(int id, String teamName, String teamDescription, Privacy privacy, String adm) {
		setId(id);
		setTeamName(teamName);
		setTeamDescription(teamDescription);
		setPrivacy(privacy);
		setAdmin(adm);

	}

	public void addTask(Task t) {
		this.tasks.add(t);
	}

	public void setAdmin(String name) {

		this.admin = name;
	}

	public String getAdmin() {
		return this.admin;
	}

	/**
	 * Adds a member to team
	 * 
	 * @param memb
	 *            the member to be added.
	 */
	public void addMember(TeamMember memb) {
		members.add(memb);
	}

	/**
	 * Deletes a member from the team.
	 * 
	 * @param member
	 *            the member to be deleted.
	 */
	public void deleteMember(TeamMember member) {
		Iterator<TeamMember> ite = members.iterator();
		while (ite.hasNext()) {
			TeamMember value = ite.next();
			if (value.getAccount().getUserName().contentEquals(member.getAccount().getUserName()))
				ite.remove();
		}
	}

	public int getTeamId() {
		return this.id;
	}

	public List<Task> getTasks() {
		return this.tasks;
	}

	public List<TeamMember> getMembers() {
		return members;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getTeamDescription() {
		return teamDescription;
	}

	public void setTeamDescription(String teamDescription) {
		this.teamDescription = teamDescription;
	}

	public Privacy getPrivacy() {
		return privacy;
	}

	public void setPrivacy(Privacy privacy) {
		this.privacy = privacy;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
