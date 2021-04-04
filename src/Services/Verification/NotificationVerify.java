package Services.Verification;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import DAO.DaoNotification;
import DAO.DaoPost;
import Services.Entities.Notification;
import Services.Entities.Student;

public class NotificationVerify {
	// Variables
	private DaoNotification daoNotificationImp;
	private DaoPost daoPostImp;

	// Constructor
	public NotificationVerify(DaoNotification daoNotificationImp, DaoPost daoPostImp) {
		super();
		this.daoNotificationImp = daoNotificationImp;
		this.setDaoPostImp(daoPostImp);
	}

	public NotificationVerify() {
	}

	// Main Functions
	// Function handling get the ticket by a student
	public Notification notificatinFct(HttpServletRequest request) {
		// Getting parameters
		int notificationId = Integer.parseInt(request.getParameter("notificationId"));

		Notification notification = new Notification();
		// Getting the id of the student to idStudent notification
		HttpSession session = request.getSession();
		Student student = (Student) session.getAttribute("student");

		notification.setIdStudent(student.getCNE());
		notification.setId(notificationId);

		// Insert notification to data base
		this.daoNotificationImp.update(notification);

		return notification;
	}

	// Add notification verification function
	public Notification notificationVerify(HttpServletRequest request) {

		// Getting parameters
		String title = request.getParameter("title");
		String date = request.getParameter("date");

		if (!(title.isEmpty() || date.isEmpty())) {

			Notification notification = new Notification();
			notification.setDate(date);
			notification.setTitle(title);

			this.daoNotificationImp.add(notification);

			return notification;

		}

		return null;
	}

	public DaoPost getDaoPostImp() {
		return daoPostImp;
	}

	public void setDaoPostImp(DaoPost daoPostImp) {
		this.daoPostImp = daoPostImp;
	}

}
