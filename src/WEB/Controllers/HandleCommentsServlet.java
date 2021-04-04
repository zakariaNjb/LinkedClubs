package WEB.Controllers;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.DaoComment;
import DAO.DaoCommentImp;
import Services.Entities.Comment;
import Services.Verification.CommentVerification;
/**
 * Servlet implementation class HandleCommentsServlet
 */
@WebServlet("/HandleComments")
public class HandleCommentsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DaoComment daoCommentImp;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HandleCommentsServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub

		this.daoCommentImp = new DaoCommentImp();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		CommentVerification commentVerification = new CommentVerification(this.daoCommentImp);
		@SuppressWarnings("unused")
		Comment comment = commentVerification.verifyComment(request);

		if (commentVerification.getErr().isEmpty()) {
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
