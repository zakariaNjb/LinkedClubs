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
import Services.Verification.JoinClubVerification;

/**
 * Servlet implementation class HandleJoinClubServlet
 */
@WebServlet("/HandleJoinClub")
public class HandleJoinClubServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DaoJoinClub daoJoinClubImp;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HandleJoinClubServlet() {
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		JoinClubVerification joinClubVerification = new JoinClubVerification(this.daoJoinClubImp);
		ClubsMembers clubsMembers = joinClubVerification.verifyJoinClub(request);

		if (clubsMembers == null) {
			response.setContentType("text/xml");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("<message>failure</message>");
		} else {
			response.setContentType("text/xml");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("<message>success</message>");
		}

	}

}
