package technic;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import client.LocalTask;
import client.LocalTaskDatabase;
import team.Team;
import team.TeamDatabase;
import team.TeamMember;

/**
 * This class is responsible for serialization of the data. We opted for the
 * pattern Observer so on each modification on the data it is serialized.
 * 
 * @author Alex
 *
 */
public class InOut implements Observer {

	private static InOut inOutInstance = new InOut();

	public InOut() {
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		serializeUsers();
		serializeGroups();
		serializeLocalTasks();
	}

	/**
	 * Serialize the users
	 */
	public void serializeUsers() {
		try {
			System.out.printf("RegUser serialised");
			FileOutputStream fileOut = new FileOutputStream("users.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(RegisteredUser.getInstance().getMembers());
			out.close();
			fileOut.close();

		} catch (IOException i) {
			i.printStackTrace();
		}
	}

	/**
	 * Deserializes the users.
	 * 
	 * @return The list of users.
	 */
	@SuppressWarnings("unchecked")
	private List<TeamMember> deserializeUsers() {
		List<TeamMember> e = null;
		try {
			FileInputStream fileIn = new FileInputStream("users.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			e = (List<TeamMember>) in.readObject();
			in.close();
			fileIn.close();
		} catch (IOException i) {
			i.printStackTrace();
			return null;
		} catch (ClassNotFoundException c) {
			System.out.println("Users class not found");
			c.printStackTrace();
			return null;
		}

		System.out.println("Deserialized Users...");

		return e;
	}

	/**
	 * Serialize the groups.
	 */
	private void serializeGroups() {
		try {
			FileOutputStream fileOut = new FileOutputStream("groups.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(TeamDatabase.getInstance().getTeams());
			out.close();
			fileOut.close();
			System.out.printf("Groups serialised");
		} catch (IOException i) {
			i.printStackTrace();
		}
	}

	/**
	 * 
	 * Deserializes the groups
	 * 
	 * @return List of teams.
	 */
	@SuppressWarnings("unchecked")
	private List<Team> deserializeGroups() {
		List<Team> e = null;
		try {
			FileInputStream fileIn = new FileInputStream("groups.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			e = (List<Team>) in.readObject();
			in.close();
			fileIn.close();
		} catch (IOException i) {
			i.printStackTrace();
			return null;
		} catch (ClassNotFoundException c) {
			System.out.println("Groups class not found");
			c.printStackTrace();
			return null;
		}

		System.out.println("Deserialized Groups...");
		return e;
	}

	/**
	 * Serialize the local tasks.
	 */
	private void serializeLocalTasks() {
		try {
			FileOutputStream fileOut = new FileOutputStream("localtask.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(LocalTaskDatabase.getInstance().getAllLocalTasks());
			out.close();
			fileOut.close();
			System.out.printf("LocalTasks serialised");
		} catch (IOException i) {
			i.printStackTrace();
		}
	}

	/**
	 * Deserialize the local tasks
	 * @return List of local tasks.
	 */
	@SuppressWarnings("unchecked")
	private List<LocalTask> deserializeLocalTasks() {
		List<LocalTask> e = null;
		try {
			FileInputStream fileIn = new FileInputStream("localtask.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			e = (List<LocalTask>) in.readObject();
			in.close();
			fileIn.close();
		} catch (IOException i) {
			i.printStackTrace();
			return null;
		} catch (ClassNotFoundException c) {
			System.out.println("LocalTasks class not found");
			c.printStackTrace();
			return null;
		}

		System.out.println("Deserialized LocalTasks...");

		return e;
	}

	/**
	 * Wrapper for the deserializeUsers as it is a private method.
	 * @return The list of TeamMembers.
	 */ 
	public List<TeamMember> readMemb() {
		return deserializeUsers();
	}

	/**
	 * Wrapper for the deserializeGroups as it is a private method.
	 * @return The list of Team.
	 */ 
	public List<Team> readGroups() {
		return deserializeGroups();
	}

	/**
	 * Wrapper for the local tasks as deserializeLocalTasks it is a private method.
	 * @return The list of LocalTasks.
	 */ 
	public List<LocalTask> readLocalTasks() {
		return deserializeLocalTasks();
	}

	public static InOut getInOutInstance() {
		return inOutInstance;
	}

}
