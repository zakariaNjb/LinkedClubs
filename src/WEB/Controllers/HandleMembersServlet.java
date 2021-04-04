package WEB.Controllers;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.DaoJoinClub;
import DAO.DaoJoinClubImp;
import Services.Entities.ClubsMembers;
import Services.Verification.AcceptDeclineMembersVerification;

/**
 * Servlet implementation class HandleMembersServlet
 */
@WebServlet("/HandleMembers")
public class HandleMembersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DaoJoinClub daoJoinClubImp;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HandleMembersServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		this.daoJoinClubImp = new DaoJoinClubImp();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@SuppressWarnings("unused")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String clickedBtn = request.getParameter("btnClicked");

		AcceptDeclineMembersVerification acceptDeclineMembersVerification = new AcceptDeclineMembersVerification(
				this.daoJoinClubImp);
		ClubsMembers clubsMembers = acceptDeclineMembersVerification.verifyClubsMembers(request);

		if (clickedBtn.equals("accept")) {
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
