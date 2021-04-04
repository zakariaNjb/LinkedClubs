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
import DAO.DaoJoinClub;
import DAO.DaoJoinClubImp;
import DAO.DaoStudent;
import DAO.DaoStudentImp;
import Services.Entities.Club;
import Services.Verification.AdminVerification;

/**
 * Servlet implementation class CreateClubServlet
 */
@WebServlet("/administration")
public class CreateClubServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DaoClub daoClubImp;
	DaoStudent daoStudentImp;
	DaoJoinClub daoJoinClubImp;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateClubServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		this.daoClubImp = new DaoClubImp();
		this.daoStudentImp = new DaoStudentImp();
		this.daoJoinClubImp = new DaoJoinClubImp();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		// Number of students
		int nbrStudents = this.daoStudentImp.getAll().size();
		
		// Number of clubs
		int nbrClubs = this.daoClubImp.getAllClubs().size();
		
		// Number of members of clubs
		int nbrMembers = this.daoJoinClubImp.getAll().size();
		
		request.setAttribute("nbrStudents", nbrStudents);
		request.setAttribute("nbrClubs", nbrClubs);
		request.setAttribute("nbrMembers", nbrMembers);
		
		// Get the session parametters
		HttpSession session = request.getSession();
		String adminUsername = (String) session.getAttribute("adminUsername");
		String adminPassword = (String) session.getAttribute("adminPassword");
		
		if (adminUsername==null || adminPassword==null || adminUsername.equals("") || adminPassword.equals("")) {
			request.getServletContext().getRequestDispatcher("/WEB-INF/JSP/adminLogin.jsp").forward(request, response);
		}else {
			request.getServletContext().getRequestDispatcher("/WEB-INF/JSP/dashboardAdmin.jsp").forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		AdminVerification adminVerification = new AdminVerification(this.daoClubImp);
		Club club = adminVerification.verifyClub(request);
		if(adminVerification.getErr().isEmpty()) {
		
			response.sendRedirect("/LinkedClubs/administration");

		} else {
			request.setAttribute("club", club);
			request.setAttribute("err", adminVerification.getErr());
			request.getServletContext().getRequestDispatcher("/WEB-INF/JSP/adminPage.jsp").forward(request, response);
		}
	}

}
