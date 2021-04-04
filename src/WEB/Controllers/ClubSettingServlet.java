package WEB.Controllers;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.DaoClub;
import DAO.DaoClubImp;
import Services.Entities.Club;
import Services.Verification.ClubSetting;

/**
 * Servlet implementation class ClubSettingServlet
 */

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 50, maxRequestSize = 1024 * 1024 * 5 * 5)

@WebServlet("/ClubSetting")
public class ClubSettingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DaoClub daoClubImp;

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		this.daoClubImp = new DaoClubImp();
	}

	public ClubSettingServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		ClubSetting clubSettingForm = new ClubSetting(this.daoClubImp);
		Club club = clubSettingForm.verifySettingClub(request);

		if (clubSettingForm.getErr().isEmpty()) {

			HttpSession session = request.getSession();
			session.setAttribute("club", club);

			response.sendRedirect("/LinkedClubs/ProfileClub");
		} else {
			request.setAttribute("errClubSetting", clubSettingForm.getErr());
			request.getServletContext().getRequestDispatcher("/WEB-INF/JSP/ProfileClub.jsp").forward(request, response);
		}

	}

}
