package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Observable;

import client.LocalTaskDatabase;
import task.Task;
import team.Notification;
import team.Notification_type;
import team.Team;
import team.TeamDatabase;
import team.TeamMember;
import technic.InOut;
import technic.RegisteredUser;

public class Server extends Observable {
	private static int uniqueId;
	private ArrayList<ClientThread> clientList;
	private ServerGUI serverGUI;
	private SimpleDateFormat simpleDateFormat;
	private int port;
	private boolean keepGoing;
	private static RegisteredUser regUserInstance;
	private static TeamDatabase teamDatabaseInstance;
	private static LocalTaskDatabase localTaskDatabaseInstance;
	private static InOut inOutInstance;

	/**
	 * Constructor
	 * 
	 * @param port
	 *            port to which the server will listen
	 */
	public Server(int port) {
		this(port, null);
	}

	public Server(int port, ServerGUI sg) {
		this.serverGUI = sg;
		this.port = port;
		simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
		clientList = new ArrayList<ClientThread>();

		inOutInstance = InOut.getInOutInstance();
		this.addObserver(inOutInstance);
		regUserInstance = RegisteredUser.getInstance();
		regUserInstance.addObserver(inOutInstance);
		regUserInstance.setMembers(inOutInstance.readMemb());
		teamDatabaseInstance = TeamDatabase.getInstance();
		teamDatabaseInstance.addObserver(inOutInstance);
		teamDatabaseInstance.setTeams(inOutInstance.readGroups());
		localTaskDatabaseInstance = LocalTaskDatabase.getInstance();
		localTaskDatabaseInstance.addObserver(inOutInstance);
		localTaskDatabaseInstance.setAllLocalTasks(inOutInstance.readLocalTasks());

	}

	/**
	 * Waits client and when a client connects it accepts connection and then it
	 * will create a new client thread
	 */
	public void start() {
		keepGoing = true;
		try {

			ServerSocket serverSocket = new ServerSocket(port);
			while (keepGoing) {
				display("Server waiting for Clients on port " + port + ".");

				Socket socket = serverSocket.accept();
				if (!keepGoing)
					break;
				ClientThread clientThread = new ClientThread(socket);
				clientList.add(clientThread);
				clientThread.start();
			}
			// inchide serverul
			try {
				serverSocket.close();
				for (int i = 0; i < clientList.size(); ++i) {
					ClientThread auxClientThread = clientList.get(i);
					try {
						auxClientThread.streamInput.close();
						auxClientThread.streamOutput.close();
						auxClientThread.socket.close();
					} catch (IOException ioE) {

					}
				}
			} catch (Exception e) {
				display("Exception closing the server and clients: " + e);
			}
		} catch (IOException e) {
			String msg = simpleDateFormat.format(new Date()) + " Exception on new ServerSocket: " + e + "\n";
			display(msg);
		}
	}

	/**
	 * Stop the server
	 */
	@SuppressWarnings("resource")
	protected void stop() {
		keepGoing = false;
		try {
			new Socket("seproject.hopto.org", 6789);
		} catch (Exception e) {

		}
	}

	/**
	 * Show on the server gui the messages
	 * 
	 * @param msg
	 *            mesajul afisat
	 */
	private void display(String msg) {
		String time = simpleDateFormat.format(new Date()) + " " + msg;
		if (serverGUI == null)
			System.out.println(time);
		else
			serverGUI.appendEvent(time + "\n");
	}

	/**
	 * Send a message to all users in a sync way.
	 * 
	 * @param message
	 *            the message to be sent
	 */
	private synchronized void broadcast(String message) {
		String time = simpleDateFormat.format(new Date());
		String messageLf = time + " " + message + "\n";
		if (serverGUI == null)
			System.out.print(messageLf);
		else
			serverGUI.appendRoom(messageLf);
		for (int i = clientList.size(); --i >= 0;) {
			ClientThread clientThreadAux = clientList.get(i);

			if (!clientThreadAux.writeMsg(new ChatMessage(MessageType.LOGIN, messageLf, "IGNORE"))) {
				clientList.remove(i);
				display("Disconnected Client " + clientThreadAux.username + " removed from list.");
			}
		}
	}

	/**
	 * Sync way to remove a client from the list
	 * 
	 * @param id
	 *            client's id
	 */
	synchronized void remove(int id) {
		for (int i = 0; i < clientList.size(); ++i) {
			ClientThread ct = clientList.get(i);
			// found it
			if (ct.id == id) {
				clientList.remove(i);
				return;
			}
		}
	}

	/**
	 * One instance of this thread will run for each client
	 * 
	 * @author Alex and Bogdan
	 *
	 */
	class ClientThread extends Thread {
		Socket socket;
		ObjectInputStream streamInput;
		ObjectOutputStream streamOutput;
		int id;
		String username;
		ChatMessage chatMessage;
		String date;

		ClientThread(Socket socket) {
			id = ++uniqueId;
			this.socket = socket;
			System.out.println("Thread trying to create Object Input/Output Streams");
			try {
				streamOutput = new ObjectOutputStream(socket.getOutputStream());
				streamInput = new ObjectInputStream(socket.getInputStream());
				username = (String) streamInput.readObject();
				display(username + " just connected.");
			} catch (IOException e) {

				return;
			} catch (ClassNotFoundException e) {
			}
			date = new Date().toString() + "\n";
		}
		// METHODS FOR LOGG IN

		/**
		 * Method for the user existence
		 * 
		 * @param username
		 *            user's name
		 * @return true/false
		 */
		private boolean verifyUserExists(String username) {
			return regUserInstance.searchUsername(username);
		}

		/**
		 * Method for the email existence
		 * 
		 * @param email
		 *            user's email
		 * @return true/false
		 */
		private boolean verifyEmailExists(String email) {
			return regUserInstance.searchEmail(email);
		}

		/**
		 * Check credentials
		 * 
		 * @param username the user name to be checked
		 * @param password the password to be checked
		 * @return true false depending on the result of search
		 */
		private boolean verifyPasswordIsGood(String username, String password) {
			TeamMember foundMember = regUserInstance.searchAccount(username, password);
			if (foundMember == null) {
				return false;
			}
			return true;
		}

	/**
	 * Generates the specific error code for the log in failure
	 * @param message the username 
	 * @param message2 the pass for the account 
	 * @return
	 */
		private boolean tryToLogIn(String message, String message2) {

			String name = message;
			String pass = message2;

			regUserInstance.printAllMembers();

			if (name.equals("")) {
				writeMsg(new ChatMessage(MessageType.LOGIN_ERR, "1"));
				return false;
			}
			if (pass.equals("")) {
				writeMsg(new ChatMessage(MessageType.LOGIN_ERR, "2"));
				return false;
			}
			if (!verifyUserExists(name)) {
				writeMsg(new ChatMessage(MessageType.LOGIN_ERR, "3"));
				return false;
			}
			if (!verifyPasswordIsGood(name, pass)) {
				writeMsg(new ChatMessage(MessageType.LOGIN_ERR, "4"));
				return false;
			}
			return true;
		}

		// METHODS FOR REGISTER
		/**
		 * Check if user and email exists and if so it returns false. In this
		 * case we send to client a message of type REGISTER_ERR and the code of
		 * the error
		 * 
		 * @param email
		 *            the email which will be checked
		 * @param username
		 *            the username which will be checked
		 * @return false in case the credentials already exists.
		 */
		private boolean tryToRegister(String email, String username) {

			if (verifyEmailExists(email)) {
				writeMsg(new ChatMessage(MessageType.REGISTER_ERROR, "4"));
				return false;
			}

			if (verifyUserExists(username)) {
				writeMsg(new ChatMessage(MessageType.REGISTER_ERROR, "5"));
				return false;
			}
			return true;
		}

		/**
		 * In this class the incoming message will be decoded
		 */
		private void decodeMessage() {

			switch (chatMessage.getType()) {
			/**
			 * simple message
			 */
			case MESSAGE:
				String message = chatMessage.getMessage();
				broadcast(username + ": " + message);
				break;
			case LOGOUT:
				/**
				 * logout message
				 */
				display(username + " disconnected with a LOGOUT message.");
				keepGoing = false;
				break;
			case WHOISIN:
				/**
				 * see which users are logged in
				 */
				writeMsg(new ChatMessage(MessageType.LOGIN,
						"List of the users connected at " + simpleDateFormat.format(new Date()) + "\n"));
				for (int i = 0; i < clientList.size(); ++i) {
					ClientThread ct = clientList.get(i);
					writeMsg(new ChatMessage(MessageType.LOGIN, (i + 1) + ") " + ct.username + " since " + ct.date));
				}
				break;
			case LOGIN:
				/**
				 * login action
				 */
				if (chatMessage.getMessage().contentEquals("UTATARATATA")) {
					writeMsg(new ChatMessage(MessageType.LOGIN, "UTATARATATA"));
				} else {
					String messageLOGIN1 = chatMessage.getMessage();
					String messageLOGIN2 = chatMessage.getMessage2();
					if (tryToLogIn(messageLOGIN1, messageLOGIN2)) {
						ChatMessage chmes = new ChatMessage(MessageType.LOGIN, messageLOGIN1, "Log in successful");
						writeMsg(chmes);
					}
				}
				break;
			case REGISTER:
				/**
				 * Register case
				 */
				String messageREGISTER1 = chatMessage.getMessage();
				String messageREGISTER2 = chatMessage.getMessage2();
				// if we successfully register
				if (tryToRegister(messageREGISTER1, messageREGISTER2)) {
					writeMsg(new ChatMessage(MessageType.REGISTER, "GOOD"));
				}
				break;
			case NEWACCOUNT:
				/**
				 * after the register function is successfully executed the
				 * client will send a request as the new account will be added
				 */
				TeamMember tm = chatMessage.getTeamMember();
				regUserInstance.addUser(tm);
				localTaskDatabaseInstance.addUser(tm.getAccount());
				writeMsg(new ChatMessage(MessageType.NEWACCOUNT, "CREATED"));
				break;
			case EMAIL:
				/**
				 * if the email does not exists writes a message to inform user
				 */
				boolean exists = verifyEmailExists(chatMessage.getMessage());
				if (exists) {
					writeMsg(new ChatMessage(MessageType.EMAIL, ""));
				} else {
					writeMsg(new ChatMessage(MessageType.EMAIL, "NO EMAIL"));
				}
				break;
			case SUPPORT:
				/**
				 * contact support team
				 */
				String username = chatMessage.getMessage();
				String supportMessage = chatMessage.getMessage2();
				broadcast("SUPPORT :: " + username + " :: " + supportMessage);
				break;
			case CREATE_GROUP:
				// When creating a group first we add the admin of the group as user and then add the team in the team's database
				for (TeamMember memb : regUserInstance.getMembers()) {
					if (memb.getAccount().getUserName().contentEquals(chatMessage.getTeam().getAdmin())) {
						chatMessage.getTeam().addMember(memb);
					}
				}
				teamDatabaseInstance.addteam(chatMessage.getTeam()); // add the
																		// team
																		// in
																		// the
																		// data
																		// base
				//inform the user that the group was created
				writeMsg(new ChatMessage(MessageType.CREATE_GROUP, "Group created"));
				
				break;
			case GET_GROUPS:
				// writes a message to the user with the groups passed as teamDataBaseInstance
				writeMsg(new ChatMessage(MessageType.GET_GROUPS, teamDatabaseInstance));
				break;
			case ADD_LOCAL_TASK:
				/**
				 * Adds local tasks and then send a response
				 */
				localTaskDatabaseInstance.addTask(chatMessage.getMessage(), chatMessage.getTask());
				writeMsg(new ChatMessage(MessageType.ADD_LOCAL_TASK, "Task created"));
				setChanged();
				notifyObservers();
				break;
			case GET_LOCAL_TASKS:
				// writes a message to the user with the groups passed as localTaskDatabaseInstance
				writeMsg(new ChatMessage(MessageType.GET_LOCAL_TASKS, localTaskDatabaseInstance));
				break;
			case ADD_TASK: {
				//first search the team in the database and then add the task to the team
				for (Team m : teamDatabaseInstance.getTeams()) {
					if (String.valueOf(m.getId()).contentEquals(chatMessage.getMessage())) {
						m.addTask(chatMessage.getTask());
					}
				}
				// write a message to the user in order to inform him
				writeMsg(new ChatMessage(MessageType.ADD_TASK, teamDatabaseInstance));
				setChanged();
				notifyObservers();
			}
				break;
			case USER_MESSAGE:
				//creates a message which is sent from currently logged in user to other user.
				//The other user is found by the username sent on the chatMessage as second message
				// The first message on the chatMessage represents the message itself
				//The second message on the chatMessage repersents the name of the sender ( logged in user in our case).
				for (TeamMember tm1 : regUserInstance.getMembers()) {
					if (tm1.getAccount().getUserName().contentEquals(chatMessage.getMessage2())) {
						// add to the "listener" user a notification of type message 
						tm1.addNotification(new Notification(Notification_type.MESSAGE, chatMessage.getMessage(),
								chatMessage.getMessage3()));
						setChanged();
						notifyObservers();
					}
				}
				// send also the message to the client interface ( in order to be displayed).
				writeMsg(
						new ChatMessage(MessageType.USER_MESSAGE, chatMessage.getMessage3(), chatMessage.getMessage()));
				
				break;
			case ADD_USER:
				//ADDS A USER TO A TEAM 
				// first find the team by the name ( second message on chatMessage)
				// check if the user exists and if so send a response as error
				// if user is not in the team add him
				
				// chatMessage( String message, String message2) 
				// message = the name of the user to be added
				// msssage2 = the name of the team 
				Team aux = teamDatabaseInstance.getTeam(chatMessage.getMessage2());
				boolean alreadyInTm = false;
				for (TeamMember mb : aux.getMembers()) {
					if (mb.getAccount().getUserName().contentEquals(chatMessage.getMessage())) {
						writeMsg(new ChatMessage(MessageType.ERROR_ALREADY_IN_TM, ""));
						alreadyInTm = true;
					}
				}
				if (!alreadyInTm) {
					for (TeamMember mb : regUserInstance.getMembers()) {
						if (mb.getAccount().getUserName().contentEquals(chatMessage.getMessage())) {
							aux.addMember(mb);
							writeMsg(new ChatMessage(MessageType.ADD_USER, chatMessage.getMessage()));
							setChanged();
							notifyObservers();
						}
					}
				}
				setChanged();
				notifyObservers();
				break;
			case GET_USERS:
				//sends a message with the registered users 
				writeMsg(new ChatMessage(MessageType.GET_USERS, regUserInstance));
				break;
			case NOT_STATUS:
				//aici explica tu mai clar BOGDAN
				if (chatMessage.getMessage().contentEquals("CHECK_ONLY_ONCE")) {
					for (TeamMember tt : regUserInstance.getMembers()) {
						if (tt.getAccount().getUserName().contentEquals(chatMessage.getMessage2())) {
							writeMsg(new ChatMessage(MessageType.NOT_STATUS, tt));
						}
					}
				} else {
					for (TeamMember tt : regUserInstance.getMembers()) {
						if (tt.getAccount().getUserName().contentEquals(chatMessage.getMessage2())) {
							if (chatMessage.getMessage().contentEquals("true")) {
								tt.setNotificationChecked(true);
							} else if (chatMessage.getMessage().contentEquals("false")) {
								tt.setNotificationChecked(false);
							}
							writeMsg(new ChatMessage(MessageType.NOT_STATUS, tt));
						}
					}
				}
				setChanged();
				notifyObservers();

				break;
			case LEAVE_GROUP:
				/**
				 * When a user wants to leave the group it is removed from the group
				 */
				teamDatabaseInstance.remove(chatMessage.getMessage(), chatMessage.getMessage2(),
						chatMessage.getMessage3());
				writeMsg(new ChatMessage(MessageType.LEAVE_GROUP, "Success"));
				setChanged();
				notifyObservers();
				break;
			case QUIT_LOCAL_TASK:
				//quit the task 
				// first message of the chatMessage represents the task name
				// the second message represents the user from which is removed.
				localTaskDatabaseInstance.quit(chatMessage.getMessage(), chatMessage.getMessage2());
				setChanged();
				notifyObservers();
				break;
			case UPDATE_LOCAL_TASK:
				//updates the task
				// first message = selected task
				// second message = new title
				// third message = new description
				// fourth message = new priority
				// fifth message = the user of whom is the task
								localTaskDatabaseInstance.update(chatMessage.getMessage(), chatMessage.getMessage2(),
						chatMessage.getMessage3(), chatMessage.getMessage4(), chatMessage.getMessage5());
				setChanged();
				notifyObservers();
				break;
			case UPDATE_TASK_DATE:
				//updates the date of a task
				// message = task selected
				// message2 = the user of whom the task is
				//message3 = the extra amount of time
				localTaskDatabaseInstance.addTime(chatMessage.getMessage(), chatMessage.getMessage2(),
						chatMessage.getMessage3());
				setChanged();
				notifyObservers();
				break;
			case SUBMIT_LOCAL_TASK:
				// submits the local task
				// message =  task selected
				// message2 =  user of whom task is submited
				localTaskDatabaseInstance.submit(chatMessage.getMessage(), chatMessage.getMessage2());
				setChanged();
				notifyObservers();
				break;
			case ADD_LOCAL_COMMENT:
				//adds comments to local tasks
				//message = user of whom the task is
				// message2 = the name of the task
				// message3 = the comment
				localTaskDatabaseInstance.addComment(chatMessage.getMessage(), chatMessage.getMessage2(),
						chatMessage.getMessage3());
				setChanged();
				notifyObservers();
				break;
			case CHECK_IF_MEMBER:
				//checks if a user is member in a team
				//message = group name
				// message2 = user who is searched
				boolean ifFound = teamDatabaseInstance.searchUserInTeam(chatMessage.getMessage(),
						chatMessage.getMessage2());
				writeMsg(new ChatMessage(MessageType.CHECK_IF_MEMBER, String.valueOf(ifFound), chatMessage.getMessage(),
						chatMessage.getMessage3()));
				break;
			case INVITE_MEMBER: 
				//sends a notification of type invite to a user
				//message= the user which is invited
				//message2= the sender
				//message3= the id of the team to which the user is invited
				for (TeamMember mb : regUserInstance.getMembers()) {
					if (mb.getAccount().getUserName().contentEquals(chatMessage.getMessage())) {
						
						DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
						Date date = new Date();
						mb.addNotification(new Notification(Notification_type.INVITE, chatMessage.getMessage2(),
								dateFormat.format(date), Integer.valueOf(chatMessage.getMessage3())));
					}
				}
				setChanged();
				notifyObservers();

				writeMsg(new ChatMessage(MessageType.INVITE_MEMBER, ""));
				break;
			case INVITATION_ACTION:
				//performs the action on the invitation
				//the invitation could be accepted or denied . This is signaled by the first message in the chatMessage.
				//message = true or false depending if the invitation is accepted
				//message2= the user who accepted denied the invitation 
				//message3= the name of the team
				//message4= the sender of the invitation
				for (TeamMember tm1 : regUserInstance.getMembers()) {
					if (tm1.getAccount().getUserName().contentEquals(chatMessage.getMessage2())) {
						for (Notification no : tm1.getNotifications()) {
							if (no.getType().equals(Notification_type.INVITE)
									&& no.getSender().contentEquals(chatMessage.getMessage4()) && teamDatabaseInstance
											.getTeam(chatMessage.getMessage3()).getId() == no.getTeamId()) {
								if (chatMessage.getMessage().contentEquals("true")) {

									teamDatabaseInstance.getTeamById(no.getTeamId()).addMember(tm1);
									writeMsg(new ChatMessage(MessageType.INVITATION_ACTION, "true"));
								} else {
									writeMsg(new ChatMessage(MessageType.INVITATION_ACTION, "false"));
								}
							}
						}
					}
				}
				setChanged();
				notifyObservers();
				break;
			case DELETE_NOTIFICATION:
				
				//deletes notification. This is valid only for the Notifications of type Invitation.
				// message = the user 
				// message2 = the sender
				// message3 = the name of the team
				
				for (TeamMember m : regUserInstance.getMembers()) {
					if (m.getAccount().getUserName().contentEquals(chatMessage.getMessage())) {
						Iterator<Notification> it = m.getNotifications().iterator();
						while (it.hasNext()) {
							Notification n = it.next();
							if (n.getType().equals(Notification_type.INVITE)) {
								if (n.getSender().contentEquals(chatMessage.getMessage2())) {
									if (teamDatabaseInstance.getTeam(chatMessage.getMessage3()).getId() == n
											.getTeamId()) {
										it.remove();

									}
								}
							}
						}

					}

				}
				setChanged();
				notifyObservers();
				break;
			case GET_TASK:
				// This represents the action of taking a task by the user
				//message = team id
				//message2 = task name
				//message3 = the name of the person which takes the task
				Task task = teamDatabaseInstance.getTask(chatMessage.getMessage(), chatMessage.getMessage2(),
						chatMessage.getMessage3());
				if (task == null) {
					writeMsg(new ChatMessage(MessageType.GET_TASK, "ERROR"));
				} else {
					writeMsg(new ChatMessage(MessageType.GET_TASK, "GOOD", task));
				}
				break;
			case ADD_COMMENT:
				//add a comment to a task
				//message=  team id
				//message2= task's name
				//message3= working person
				///message4= user which places the comment 
				//message5=  the comment itself
				teamDatabaseInstance.addComment(chatMessage.getMessage(), chatMessage.getMessage2(),
						chatMessage.getMessage3(), chatMessage.getMessage4(), chatMessage.getMessage5());
				setChanged();
				notifyObservers();
				break;
			case SUBMIT_TASK:
				//submit a task
				// message= task's name
				//message2= user
				//message3= team id
				teamDatabaseInstance.changeStatus("SUBMIT", chatMessage.getMessage(), chatMessage.getMessage2(),
						chatMessage.getMessage3(), "null");

				writeMsg(new ChatMessage(MessageType.SUBMIT_TASK, chatMessage.getMessage(), chatMessage.getMessage2(),
						chatMessage.getMessage3()));
				setChanged();
				notifyObservers();
				break;
			case TAKE_TASK:
				//takes a task
				// message= task's name
				//message2= user
				//message3= team id
				teamDatabaseInstance.changeStatus("TAKE", chatMessage.getMessage(), chatMessage.getMessage2(),
						chatMessage.getMessage3(), "null");
				writeMsg(new ChatMessage(MessageType.TAKE_TASK, chatMessage.getMessage(), chatMessage.getMessage2(),
						chatMessage.getMessage3()));
				setChanged();
				notifyObservers();
				break;
			case QUIT_TASK:
				//quits a task
				// message= task's name
				//message2= user
				//message3= team id
				teamDatabaseInstance.changeStatus("QUIT", chatMessage.getMessage(), chatMessage.getMessage2(),
						chatMessage.getMessage3(), "null");
				writeMsg(new ChatMessage(MessageType.QUIT_TASK, chatMessage.getMessage(), chatMessage.getMessage2(),
						chatMessage.getMessage3()));
				setChanged();
				notifyObservers();
				break;
			case RESC_TASK:
				//Reschedule a task
				// message= task's name
				//message2= user
				//message3= team id
				//message4 = extra time to add
				teamDatabaseInstance.changeStatus("RESC", chatMessage.getMessage(), chatMessage.getMessage2(),
						chatMessage.getMessage3(), chatMessage.getMessage4());
				writeMsg(new ChatMessage(MessageType.RESC_TASK, chatMessage.getMessage(), chatMessage.getMessage2(),
						chatMessage.getMessage3(), chatMessage.getMessage4()));
				setChanged();
				notifyObservers();
				break;
			case UPDATE_TASK:
				//Update information on the task
				// message= team id
				//message2= task's name
				//message3= new name
				//message4= new description
				//message5=  new Priority
				teamDatabaseInstance.updateTask(chatMessage.getMessage(), chatMessage.getMessage2(),
						chatMessage.getMessage3(), chatMessage.getMessage4(), chatMessage.getMessage5());
				writeMsg(new ChatMessage(MessageType.UPDATE_TASK, "Task updated"));
				setChanged();
				notifyObservers();
				break;
			case CHANGE_ADMIN:
				//change the admin of the team
				//message = team id
				// message2 = the name of the new admin
				teamDatabaseInstance.changeAdmin(chatMessage.getMessage(), chatMessage.getMessage2());
				writeMsg(new ChatMessage(MessageType.UPDATE_TASK, "Task updated"));
				setChanged();
				notifyObservers();
				break;
			case DELETE_GROUP:
				//deletes the group
				// message = the id of the group
				teamDatabaseInstance.deleteGroup(chatMessage.getMessage());
				setChanged();
				notifyObservers();
				break;
			case EDIT_GROUP:
				// edits the group information
				//message = the id of the group
				//message2 = the new  name of the group
				//message3 = the new description of the group
				//message4 = the new privacy
				teamDatabaseInstance.editGroup(chatMessage.getMessage(), chatMessage.getMessage2(),
						chatMessage.getMessage3(), chatMessage.getMessage4());
				setChanged();
				notifyObservers();
				break;
			default:
				break;
			}
		}

		/**
		 * Connection between user and server Reads the message until log out
		 * When the client logs out it is removed and then the thread is
		 * finished.
		 */
		public void run() {
			boolean keepGoing = true;
			while (keepGoing) {
				try {
					chatMessage = (ChatMessage) streamInput.readObject();
					decodeMessage();
				} catch (IOException e) {
					display(username + " Exception reading Streams: " + e);
					break;
				} catch (ClassNotFoundException e2) {
					break;
				}

			}
			remove(id);
			close();
		}

		/**
		 * In this method you close the Streams. It is very important to notice
		 * that the output Stream is closed first.
		 */
		private void close() {
			try {
				if (streamOutput != null)
					streamOutput.close();
			} catch (Exception e) {
			}
			try {
				if (streamInput != null)
					streamInput.close();
			} catch (Exception e) {
			}
			;
			try {
				if (socket != null)
					socket.close();
			} catch (Exception e) {
			}
		}

		/**
		 * Write the message back to the client
		 * 
		 * @param chmes
		 *            The message to be sent
		 * @return false In case the message could not be sent due to the connection problems.
		 */
		private boolean writeMsg(ChatMessage chmes) {
			if (!socket.isConnected()) {
				close();
				return false;
			}
			try {
				streamOutput.reset();
				streamOutput.writeObject(chmes);
			} catch (IOException e) {
				display("Error sending message to " + username);
				display(e.toString());
			}
			return true;
		}
	}
}