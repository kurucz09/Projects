package team;

import java.io.Serializable;

/**
 * This class is responsible for mapping the notifications.
 * 
 * @author Alex & Bogdan
 *
 */
public class Notification implements Serializable {

	private static final long serialVersionUID = 2885788677754552671L;
	private Notification_type type;
	private String text;
	private String invDate;
	/**
	 * sender = who sends the message
	 */
	private String sender;
	private int teamId;

	/**
	 * Constructor used for team invitations.
	 * 
	 * @param notificationType
	 * @param sender
	 * @param date
	 * @param teamId
	 */
	public Notification(Notification_type notificationType, String sender, String date, int teamId) {
		setType(notificationType);

		setSender(sender);
		setTeamId(teamId);
		setInvDate(date);
	}

	
	/**
	 * Constructor used for invitations of type message.
	 * 
	 * @param notificationType
	 * @param message
	 * @param sender
	 */
	public Notification(Notification_type notificationType, String message, String sender) {
		setType(notificationType);
		setText(message);
		setSender(sender);
	}

	public Notification_type getType() {
		return type;
	}

	public void setType(Notification_type type) {
		this.type = type;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getTeamId() {
		return teamId;
	}

	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}

	public String getInvDate() {
		return invDate;
	}

	public void setInvDate(String invDate) {
		this.invDate = invDate;
	}
}
