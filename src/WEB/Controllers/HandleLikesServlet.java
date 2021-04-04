package WEB.Controllers;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.DaoPost;
import DAO.DaoPostImp;
import DAO.DaoPostStudent;
import DAO.DaoPostStudentImp;
import Services.Entities.PostStudent;
import Services.Verification.LikePostVerification;

/**
 * Servlet implementation class HandleLikesServlet
 */
@WebServlet("/HandleLikes")
public class HandleLikesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DaoPostStudent daoPostStudentImp;
	private DaoPost daoPostImp;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HandleLikesServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		this.daoPostStudentImp = new DaoPostStudentImp();
		this.daoPostImp = new DaoPostImp();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@SuppressWarnings("unused")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		LikePostVerification likePostVerify = new LikePostVerification(this.daoPostStudentImp, this.daoPostImp);
		PostStudent postStudent = likePostVerify.verifyLikePost(request);

		int id = Integer.parseInt(request.getParameter("id"));
		boolean isClicked = Boolean.parseBoolean(request.getParameter("isLiked"));
		if (isClicked) {
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
