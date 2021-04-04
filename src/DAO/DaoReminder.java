package DAO;

import java.util.ArrayList;

import Services.Entities.Reminder;

public interface DaoReminder {
	
	public Reminder add(Reminder reminder); // Add new reminder
	public ArrayList<Reminder> getStudentReminder(String studentCNE); // Get reminders of a specific student

}
