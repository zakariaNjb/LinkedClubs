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

import DAO.DaoStudent;
import DAO.DaoStudentImp;
import Services.Entities.Student;
import Services.Verification.StudentSetting;

/**
 * Servlet implementation class StudentSettingServlet
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)

@WebServlet("/StudentSetting")
public class StudentSettingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DaoStudent daoStudentImp;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StudentSettingServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		this.daoStudentImp = new DaoStudentImp();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		request.getServletContext().getRequestDispatcher("/WEB-INF/JSP/StudentSetting.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		StudentSetting studentSettingForm = new StudentSetting(this.daoStudentImp);
		Student student = studentSettingForm.verifySettingStudent(request);

		if (studentSettingForm.getErr().isEmpty()) {

			HttpSession session = request.getSession();
			session.setAttribute("student", student);

			response.sendRedirect("/LinkedClubs/profile");
		} else {

			request.setAttribute("err", studentSettingForm.getErr());

			request.getServletContext().getRequestDispatcher("/WEB-INF/JSP/StudentSetting.jsp").forward(request,
					response);
		}

	}

}