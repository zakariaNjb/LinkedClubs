package WEB.Models;

import java.util.ArrayList;

import Services.Entities.Reminder;

public class RemindersOfStudent {
	
	String studentCNE;
	ArrayList<Reminder> reminders;

	
	// Constructors
	public RemindersOfStudent() {
		// TODO Auto-generated constructor stub
	}

	public RemindersOfStudent(String studentCNE, ArrayList<Reminder> reminders) {
		super();
		this.studentCNE = studentCNE;
		this.reminders = reminders;
	}

	// Getters and Setters
	public String getStudentCNE() {
		return studentCNE;
	}

	public void setStudentCNE(String studentCNE) {
		this.studentCNE = studentCNE;
	}

	public ArrayList<Reminder> getReminders() {
		return reminders;
	}

	public void setReminders(ArrayList<Reminder> reminders) {
		this.reminders = reminders;
	}
	
}
