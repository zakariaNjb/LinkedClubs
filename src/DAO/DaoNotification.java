package DAO;

import java.util.ArrayList;

import Services.Entities.Notification;

public interface DaoNotification {
	public Notification add(Notification notification); // Add new notification
	public ArrayList<Notification> getAll(String cne); // get notification of a specific student
	public Notification update(Notification notification); // Update notification
}
