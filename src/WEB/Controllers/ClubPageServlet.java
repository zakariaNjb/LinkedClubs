package WEB.Controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.DaoClub;
import DAO.DaoClubImp;
import DAO.DaoComment;
import DAO.DaoCommentImp;
import DAO.DaoJoinClub;
import DAO.DaoJoinClubImp;
import DAO.DaoPost;
import DAO.DaoPostImp;
import DAO.DaoPostStudent;
import DAO.DaoPostStudentImp;
import Services.Entities.Club;
import Services.Entities.ClubsMembers;
import Services.Entities.Post;
import Services.Entities.PostStudent;
import Services.Entities.Student;
import WEB.Models.AllComments;

/**
 * Servlet implementation class ClubPageServlet
 */
@WebServlet("/clubs")
public class ClubPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DaoClub daoClubIm;
	private DaoPost daoPostIm;
	private DaoComment daoCommentImp;
	private DaoPostStudent daoPostStudentImp;
	private DaoJoinClub daoJoinClubImp;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ClubPageServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		this.daoClubIm = new DaoClubImp();
		this.daoPostIm = new DaoPostImp();
		this.daoCommentImp = new DaoCommentImp();
		this.daoPostStudentImp = new DaoPostStudentImp();
		this.daoJoinClubImp = new DaoJoinClubImp();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Getting the current student
		HttpSession session = request.getSession();

		try {
			String CNE = ((Student) session.getAttribute("student")).getCNE();

			// Getting the lines in the table poststudent related with the current student
			ArrayList<PostStudent> likedPosts = this.daoPostStudentImp.getLikedPosts(CNE);

			// Getting the ids of the posts liked by the current student from the previous
			// list from poststudent
			ArrayList<Integer> likedPostsIds = new ArrayList<Integer>();

			// Loop through each line of likedPosts from poststudent and add it to the posts
			// ids ArrayList
			for (PostStudent likedPosts_ids : likedPosts) {

				likedPostsIds.add(likedPosts_ids.getPost_id());

			}

			// Getting the lines in clubsmembers table related with the current student
			ArrayList<ClubsMembers> clubIdsAndMembers = this.daoJoinClubImp.readClubs(CNE);
			
			ArrayList<String> clubIds = new ArrayList<String>();

			for (ClubsMembers clubsMembers : clubIdsAndMembers) {

				clubIds.add(clubsMembers.getClubId());

			}

			request.setAttribute("joinedClubs", clubIds);
			request.setAttribute("likedPosts", likedPostsIds);
		} catch (Exception e) {
			// TODO: handle exception
		}

		// TODO Auto-generated method stub
		String clubId = request.getParameter("clubId");

		// Get the current club
		Club club = this.daoClubIm.find(clubId);

		ArrayList<Post> posts = this.daoPostIm.getPosts(clubId);

		// Getting all the comments, and then we'll check for each post and its comments
		// in the jsp !!
		AllComments allComments = new AllComments(this.daoCommentImp.getAllComments());

		request.setAttribute("club", club);
		request.setAttribute("posts", posts);
		request.setAttribute("allComments", allComments.getAllComments());

		request.getServletContext().getRequestDispatcher("/WEB-INF/JSP/ClubPage.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

}
