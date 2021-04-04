package WEB.Controllers;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.DaoComment;
import DAO.DaoCommentImp;
import DAO.DaoJoinClub;
import DAO.DaoJoinClubImp;
import DAO.DaoNotification;
import DAO.DaoNotificationImp;
import DAO.DaoPost;
import DAO.DaoPostImp;
import Services.Entities.Club;
import Services.Entities.ClubsMembers;
import Services.Entities.Post;
import Services.Verification.PostVerification;
import WEB.Models.AllComments;
import WEB.Models.MembersOfClub;
import WEB.Models.PostsOfClub;

/**
 * Servlet implementation class ProfileClubServlet
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 50, maxRequestSize = 1024 * 1024 * 5 * 5)

@WebServlet("/ProfileClub")
public class ProfileClubServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DaoPost daoPostImp;
	private DaoJoinClub daoJoinClubImp;
	private DaoNotification daoNotificationImp;
	private DaoComment daoCommentImp;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProfileClubServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		this.daoPostImp = new DaoPostImp();
		this.daoJoinClubImp = new DaoJoinClubImp();
		this.daoNotificationImp = new DaoNotificationImp();
		this.daoCommentImp = new DaoCommentImp();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		Club club = (Club) session.getAttribute("club");

		if (!(club == null)) {
			String clubId = club.getClubId();
			ArrayList<ClubsMembers> membersCNE = this.daoJoinClubImp.readMembers(clubId);

			MembersOfClub membersOfClub = new MembersOfClub();

			PostsOfClub postsOfClub = new PostsOfClub();

			// Getting all the comments, and then we'll check for each post and its comments
			// in the jsp !!
			AllComments allComments = new AllComments(this.daoCommentImp.getAllComments());

			membersOfClub.setMember(this.daoJoinClubImp.getMembers(membersCNE));
			postsOfClub.setPostsOfClub(this.daoPostImp.getPosts(clubId));

			request.setAttribute("members", membersOfClub.getMember());
			request.setAttribute("membersCNE", membersCNE);
			request.setAttribute("posts", postsOfClub.getPostsOfClub());
			request.setAttribute("allComments", allComments.getAllComments());

			request.getServletContext().getRequestDispatcher("/WEB-INF/JSP/ProfileClub.jsp").forward(request, response);
		} else {
			response.sendRedirect("/LinkedClubs/ClubLogin");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		PostVerification postForm = new PostVerification(this.daoPostImp, this.daoNotificationImp);
		Post post = postForm.verifyPost(request);

		if (!postForm.getErr().isEmpty()) {
			request.setAttribute("err", postForm.getErr());
			request.setAttribute("post", post);
			request.getServletContext().getRequestDispatcher("/WEB-INF/JSP/ProfileClub.jsp").forward(request, response);
		} else
			response.sendRedirect("/LinkedClubs/ProfileClub");
	}

}