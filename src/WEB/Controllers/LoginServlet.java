package WEB.Controllers;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.DaoStudent;
import DAO.DaoStudentImp;
import Services.Entities.Student;
import Services.Verification.Login;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DaoStudent daoStudentImp;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		this.daoStudentImp = new DaoStudentImp();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getServletContext().getRequestDispatcher("/WEB-INF/JSP/Login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Login loginForm = new Login(this.daoStudentImp);
		Student student = loginForm.verifyLogin(request);

		if (!loginForm.getErr().isEmpty()) {
			request.setAttribute("student", student);
			request.setAttribute("err", loginForm.getErr());
			request.getServletContext().getRequestDispatcher("/WEB-INF/JSP/Login.jsp").forward(request, response);
		} else {

			HttpSession session = request.getSession();
			session.setAttribute("student", student);

			response.sendRedirect("/LinkedClubs/profile");
		}

		// TODO Auto-generated method stub
	}

}
