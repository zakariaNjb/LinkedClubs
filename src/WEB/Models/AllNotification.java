package WEB.Models;

import java.util.ArrayList;

import Services.Entities.Notification;

public class AllNotification {
	//Variables
	private ArrayList<Notification> notificationsList;

	//Constructor
	public AllNotification() {}
	public AllNotification(ArrayList<Notification> notificationsList) {
		super();
		this.notificationsList = notificationsList;
	}

	//Getters & setters
	public ArrayList<Notification> getNotificationsList() {
		return notificationsList;
	}

	public void setNotificationsList(ArrayList<Notification> notificationsList) {
		this.notificationsList = notificationsList;
	}
	
}
