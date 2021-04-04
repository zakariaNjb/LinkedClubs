package WEB.Controllers;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.DaoReminder;
import DAO.DaoReminderImp;
import Services.Entities.Reminder;
import Services.Verification.ReminderVerification;

/**
 * Servlet implementation class HandleReminderServlet
 */
@WebServlet("/HandleReminder")
public class HandleReminderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DaoReminder daoReminderImp;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HandleReminderServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		this.daoReminderImp = new DaoReminderImp();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@SuppressWarnings("unused")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		ReminderVerification reminderVerification = new ReminderVerification(this.daoReminderImp);
		Reminder reminder = reminderVerification.verifyReminder(request);

		if (reminderVerification.getErr().isEmpty()) {
			response.setContentType("text/xml");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("<message>success</message>");
		} else {
			response.setContentType("text/xml");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("<message>failure</message>");
		}

	}

}
