package WEB.Controllers;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.DaoNotification;
import DAO.DaoNotificationImp;
import DAO.DaoPost;
import DAO.DaoPostImp;
import Services.Entities.Notification;
import Services.Verification.NotificationVerify;

/**
 * Servlet implementation class HandleNotificationServlet
 */
@WebServlet("/HandleNotification")
public class HandleNotificationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DaoPost daoPostImp;
	private DaoNotification daoNotificationImp;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HandleNotificationServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method student
		this.daoPostImp = new DaoPostImp();
		this.daoNotificationImp = new DaoNotificationImp();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);
		String isClicked = (String) request.getParameter("isClicked");
		if (!Boolean.parseBoolean(isClicked)) {
			NotificationVerify notificationVerify = new NotificationVerify(daoNotificationImp, daoPostImp);
			Notification notification = notificationVerify.notificatinFct(request);

			if (notification != null) {
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

}
