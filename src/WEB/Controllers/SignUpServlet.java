package WEB.Controllers;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.DaoStudent;
import DAO.DaoStudentImp;
import Services.Entities.Student;
import Services.Verification.SignUp;

/**
 * Servlet implementation class SignUpServlet
 */
@WebServlet("/Signup")
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private DaoStudent daoStudentImp;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignUpServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		 this.daoStudentImp= new DaoStudentImp();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getServletContext().getRequestDispatcher("/WEB-INF/JSP/SignUp.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// TODO Auto-generated method stub
		SignUp signUpForm=new SignUp(this.daoStudentImp);
		Student student=signUpForm.verifySignUp(request);
		
		if(!signUpForm.getErr().isEmpty()) {
			request.setAttribute("erreur", signUpForm.getErr());
			request.setAttribute("student", student);
			request.getServletContext().getRequestDispatcher("/WEB-INF/JSP/SignUp.jsp").forward(request, response);
		}else response.sendRedirect("/LinkedClubs/Login");
		
		//doGet(request, response);
	}

}
