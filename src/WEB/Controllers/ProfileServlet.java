package WEB.Controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
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
import DAO.DaoPostStudent;
import DAO.DaoPostStudentImp;
import DAO.DaoReminder;
import DAO.DaoReminderImp;
import Services.Entities.ClubsMembers;
import Services.Entities.Comment;
import Services.Entities.Post;
import Services.Entities.PostStudent;
import Services.Entities.Student;
import Services.Verification.CommentVerification;
import WEB.Models.AllComments;
import WEB.Models.AllNotification;
import WEB.Models.PostsOfClub;
import WEB.Models.RemindersOfStudent;

/**
 * Servlet implementation class ProfileServlet
 */
@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DaoPost daoPostImp;
	private DaoJoinClub daoJoinClubImp;
	private DaoComment daoCommentImp;
	private DaoPostStudent daoPostStudentImp;
	private DaoReminder daoReminderImp;
	private DaoNotification daoNotificationImp;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProfileServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		this.daoPostImp = new DaoPostImp();
		this.daoJoinClubImp = new DaoJoinClubImp();
		this.daoCommentImp = new DaoCommentImp();
		this.daoPostStudentImp = new DaoPostStudentImp();
		this.daoReminderImp = new DaoReminderImp();
		this.daoNotificationImp = new DaoNotificationImp();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		// Getting the current student
		HttpSession session = request.getSession();
		Student student = (Student) session.getAttribute("student");

		if (!(student == null)) {
			String CNE = student.getCNE();
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

			// Getting all the comments, and then we'll check for each post and its comments
			// in the jsp !!
			AllComments allComments = new AllComments(this.daoCommentImp.getAllComments());

			// Getting all the notification correspond to a student
			AllNotification allNotifications = new AllNotification(this.daoNotificationImp.getAll(CNE));

			// Getting the lines in clubsmembers table related with the current student
			ArrayList<ClubsMembers> clubIds = this.daoJoinClubImp.readClubs(CNE);

			ArrayList<Post> posts = new ArrayList<Post>();

			for (ClubsMembers clubsMembers : clubIds) {

				posts.addAll(this.daoPostImp.getPosts(clubsMembers.getClubId()));

			}

			Collections.shuffle(posts);

			PostsOfClub postsOfClub = new PostsOfClub();

			postsOfClub.setPostsOfClub(posts);

			RemindersOfStudent remindersOfStudent = new RemindersOfStudent(CNE,
					this.daoReminderImp.getStudentReminder(CNE));

			// Variables for number of reminders and number of notifications
			int nbrNotifications = allNotifications.getNotificationsList().size();
			int nbrReminders = remindersOfStudent.getReminders().size();
			int nbrJoinedClubs = clubIds.size();

			request.setAttribute("nbrNotifications", nbrNotifications);
			request.setAttribute("nbrReminders", nbrReminders);
			request.setAttribute("nbrJoinedClubs", nbrJoinedClubs);

			request.setAttribute("posts", postsOfClub.getPostsOfClub());

			request.setAttribute("likedPosts", likedPostsIds);

			request.setAttribute("allComments", allComments.getAllComments());

			request.setAttribute("reminders", remindersOfStudent.getReminders());

			request.setAttribute("notifications", allNotifications.getNotificationsList());

			request.getServletContext().getRequestDispatcher("/WEB-INF/JSP/Profile.jsp").forward(request, response);
		} else {
			response.sendRedirect("/LinkedClubs/Login");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		CommentVerification commentForm = new CommentVerification(this.daoCommentImp);
		Comment comment = commentForm.verifyComment(request);

		if (!commentForm.getErr().isEmpty()) {
			request.setAttribute("err", commentForm.getErr());
			request.setAttribute("comment", comment);
			doGet(request, response);
		} else
			response.sendRedirect("/LinkedClubs/profile");

	}

}
