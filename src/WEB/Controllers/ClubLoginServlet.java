package WEB.Controllers;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.DaoClub;
import DAO.DaoClubImp;
import Services.Entities.Club;
import Services.Verification.ClubLogin;

/**
 * Servlet implementation class ClubLoginServlet
 */
@WebServlet("/ClubLogin")
public class ClubLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DaoClub daoClubImp;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ClubLoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		this.daoClubImp = new DaoClubImp();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getServletContext().getRequestDispatcher("/WEB-INF/JSP/ClubLogin.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		ClubLogin clubLoginForm = new ClubLogin(this.daoClubImp);
		Club club = clubLoginForm.verifyLogin(request);

		if (!clubLoginForm.getErr().isEmpty()) {
			request.setAttribute("club", club);
			request.setAttribute("err", clubLoginForm.getErr());
			request.getServletContext().getRequestDispatcher("/WEB-INF/JSP/ClubLogin.jsp").forward(request, response);
		} else {

			HttpSession session = request.getSession();
			session.setAttribute("club", club);

			response.sendRedirect("/LinkedClubs/ProfileClub");
		}

	}

}
