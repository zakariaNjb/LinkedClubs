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
import DAO.DaoNotification;
import DAO.DaoNotificationImp;
import DAO.DaoPost;
import DAO.DaoPostImp;
import DAO.DaoPostStudent;
import DAO.DaoPostStudentImp;
import DAO.DaoReminder;
import DAO.DaoReminderImp;
import Services.Entities.Post;
import Services.Entities.PostStudent;
import Services.Entities.Student;
import WEB.Models.AllComments;
import WEB.Models.AllNotification;
import WEB.Models.AllPosts;
import WEB.Models.RemindersOfStudent;

/**
 * Servlet implementation class HomePostsServlet
 */
@WebServlet("/home")
public class HomePage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DaoPost daoPostImp;
	private DaoComment daoCommentImp;
	private DaoPostStudent daoPostStudentImp;
	private DaoReminder daoReminderImp;
	private DaoNotification daoNotificationImp;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HomePage() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		this.daoPostImp = new DaoPostImp();
		this.daoCommentImp = new DaoCommentImp();
		this.daoPostStudentImp = new DaoPostStudentImp();
		this.daoReminderImp = new DaoReminderImp();
		this.daoNotificationImp = new DaoNotificationImp();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

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

			RemindersOfStudent remindersOfStudent = new RemindersOfStudent(CNE,
					this.daoReminderImp.getStudentReminder(CNE));

			// Variables for number of reminders and number of notifications
			int nbrNotifications = allNotifications.getNotificationsList().size();
			int nbrReminders = remindersOfStudent.getReminders().size();

			request.setAttribute("nbrNotifications", nbrNotifications);
			request.setAttribute("nbrReminders", nbrReminders);

			request.setAttribute("likedPosts", likedPostsIds);

			request.setAttribute("allComments", allComments.getAllComments());

			request.setAttribute("reminders", remindersOfStudent.getReminders());

			request.setAttribute("notifications", allNotifications.getNotificationsList());
		}

		ArrayList<Post> postsOfClubs = this.daoPostImp.getAllPosts();
		Collections.shuffle(postsOfClubs);
		AllPosts allPost = new AllPosts();
		allPost.setPosts(postsOfClubs);

		request.setAttribute("posts", allPost.getPosts());
		request.getServletContext().getRequestDispatcher("/WEB-INF/JSP/homePage.jsp").forward(request, response);
	}


}
