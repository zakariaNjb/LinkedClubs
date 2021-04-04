package Services.Verification;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import DAO.DaoNotification;
import DAO.DaoPost;
import Services.Entities.Club;
import Services.Entities.Notification;
import Services.Entities.Post;

public class PostVerification {
	// Variables
	private HashMap<String, String> err = new HashMap<String, String>();
	private DaoPost daoPostImp;
	private DaoNotification daoNotificationImp;

	// Constructors
	public PostVerification(DaoPost daoPostImp, DaoNotification daoNotificationImp) {
		this.daoPostImp = daoPostImp;
		this.daoNotificationImp = daoNotificationImp;

	}

	// Verification function
	public Post verifyPost(HttpServletRequest request) throws IOException, ServletException {

		// Get the current project path
		String applicationPath = request.getServletContext().getRealPath("");

		// Get the upload folder for profile images
		String profileImagesUploadDirectory = applicationPath + File.separator + "Images" + File.separator
				+ "postFiles";

		// Getting files
		Part postImagePart = request.getPart("postFile");

		// Getting parameters
		String postDescription = (String) request.getParameter("description");
		String postFileLink = (String) postImagePart.getSubmittedFileName();

		// Get the notification
		NotificationVerify notificationVerifyForm = new NotificationVerify(this.daoNotificationImp, this.daoPostImp);
		Notification notification = notificationVerifyForm.notificationVerify(request);

		// Create post object
		Post post = new Post();

		// Call verification functions
		verifyPostDescription(postDescription, post);
		verifyFileLink(postImagePart, postFileLink, post);

		HttpSession session = request.getSession();
		Club club = (Club) session.getAttribute("club");

		post.setClub(club);

		if (err.isEmpty()) {
			if (!(postFileLink.equals(""))) {
				postImagePart.write(profileImagesUploadDirectory + File.separator + postFileLink);
			}
			// Setting the date of the post
			post.setPublishedDate(new java.util.Date());

			// Set the notification to the post
			post.setNotification(notification);

			// Inserting the post in the dataBase
			post = this.daoPostImp.add(post);
		}

		return post;
	}

	// functions
	public void verifyPostDescription(String postDescription, Post post) {
		// Setting post description
		post.setPostDescription(postDescription);
		// Sometimes we will have a post width description sometimes not
	}

	public void verifyFileLink(Part postImagePart, String fileLink, Post post) {
		// Setting post image
		post.setFileLink(fileLink);

		// Verify the type of the file
		if (!postImagePart.getContentType().equals("application/octet-stream")) {
			post.setFileType(postImagePart.getContentType());
		}

		try {
			if (post.getPostDescription().isEmpty() && post.getFileLink().isEmpty())
				throw new Exception("Please make sure to insert a valid post information");
		} catch (Exception e) {
			err.put("errPost", e.getMessage());
			// TODO: handle exception
		}
	}

	// Getters and Setters
	public HashMap<String, String> getErr() {
		return err;
	}

	public void setErr(HashMap<String, String> err) {
		this.err = err;
	}

}