package Services.Verification;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import DAO.DaoReminder;
import Services.Entities.Reminder;
import Services.Entities.Student;

public class ReminderVerification {

	// Variables
	private HashMap<String, String> err = new HashMap<String, String>();
	private DaoReminder daoReminderImp;

	public ReminderVerification(DaoReminder daoReminderImp) {
		// TODO Auto-generated constructor stub
		this.daoReminderImp = daoReminderImp;
	}

	// Main Verification function
	public Reminder verifyReminder(HttpServletRequest request) {

		String reminderTitle = request.getParameter("title");
		String reminderDate = request.getParameter("date");

		HttpSession session = request.getSession();
		Student student = (Student) session.getAttribute("student");

		Reminder reminder = new Reminder();

		verifyTitle(reminderTitle, reminder);
		verifyDate(reminderDate, reminder);
		reminder.setStudent(student);

		if (this.err.isEmpty()) {
			return this.daoReminderImp.add(reminder);
		}

		return reminder;
	}

	// Verification functions
	public void verifyTitle(String title, Reminder reminder) {
		reminder.setTitle(title);

		try {
			if (title.isEmpty()) {
				throw new Exception("Please give a title to your reminder !");
			}
		} catch (Exception e) {
			// TODO: handle exception
			err.put("errReminderTitle", e.getMessage());
		}
	}

	public void verifyDate(String date, Reminder reminder) {

		reminder.setReminderDate(date);

		try {
			if (date.isEmpty()) {
				throw new Exception("Please give a date to your reminder !");
			}
		} catch (Exception e) {
			// TODO: handle exception
			err.put("errReminderDate", e.getMessage());
		}

	}

	// Getters and Setters
	public HashMap<String, String> getErr() {
		return err;
	}

	public void setErr(HashMap<String, String> err) {
		this.err = err;
	}

}
