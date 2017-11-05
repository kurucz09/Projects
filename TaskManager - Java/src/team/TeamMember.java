package team;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class TeamMember extends Observable implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4316418427683494857L;
	private Account account;
	private String name;
	private String surname;
	private String email;
	private String country;
	private List<Notification> notifications = new ArrayList<Notification>();
	private boolean notificationChecked;

	public TeamMember() {

	};

	public TeamMember(Account account, String name, String surname, String email, String country) {
		setAccount(account);
		setName(name);
		setSurname(surname);
		setEmail(email);
		setCountry(country);
		setNotificationChecked(true);
	}

	public TeamMember(Account cont) {
		setAccount(cont);
	}

	/**
	 * Deletes the notification given as parameter.
	 * @param n the notification to be deleted.
	 */
	public void deleteNotification(Notification n) {
		notifications.remove(n);
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * Adds the notification of type Notification_type, with the specific message and sender. Used for the messages of type MESSAGE.
	 * @param nt notification type
	 * @param message message to add
	 * @param sender the person who sends the message.
	 */
	public void addNotification(Notification_type nt, String message, String sender) {
		Notification newnot = new Notification(nt, message, sender);
		notifications.add(newnot);
	}

	/**
	 * Adds to the list of notifications the notification provided as parameter.
	 * @param notification the notification to be added.
	 */
	public void addNotification(Notification notification) {
		notifications.add(notification);
	}

	public List<Notification> getNotifications() {
		return notifications;
	}

	public void setNotifications(List<Notification> notifications) {
		this.notifications = notifications;
	}

	public boolean isNotificationChecked() {
		return notificationChecked;
	}

	public void setNotificationChecked(boolean notificationChecked) {
		this.notificationChecked = notificationChecked;
	}

}
