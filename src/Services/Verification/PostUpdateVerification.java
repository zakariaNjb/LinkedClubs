package Services.Verification;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import DAO.DaoPost;
import Services.Entities.Post;

public class PostUpdateVerification {

	private HashMap<String, String> err = new HashMap<String, String>();
	private DaoPost daoPostImp;

	// Constructors

	public PostUpdateVerification() {
		// TODO Auto-generated constructor stub
	}

	public PostUpdateVerification(DaoPost daoPostImp) {
		super();
		this.daoPostImp = daoPostImp;
	}

	// Getters and Setters

	public HashMap<String, String> getErr() {
		return err;
	}

	public void setErr(HashMap<String, String> err) {
		this.err = err;
	}

	// Verify function

	public Post verifyUpdatedPost(HttpServletRequest request) throws IOException, ServletException {

		// Get the current project path
		String applicationPath = request.getServletContext().getRealPath("");

		// Get the upload folder for profile images
		String postImagesUploadDirectory = applicationPath + File.separator + "Images" + File.separator + "postFiles";

		// Getting files
		Part postImagePart = request.getPart("fileLink");

		int id = Integer.parseInt(request.getParameter("post_id"));
		String postDescription = request.getParameter("description");
		String fileLink = postImagePart.getSubmittedFileName();

		Post post = new Post();

		if (!postImagePart.getContentType().equals("application/octet-stream")) {
			postImagePart.write(postImagesUploadDirectory + File.separator + fileLink);
		} else {

		}

		verifyPostDescription(postDescription, post);
		post.setFileLink(fileLink);
		post.setFileType(postImagePart.getContentType());
		post.setId(id);

		post = this.daoPostImp.update(post);

		return post;
	}

	// Verificatuion functions

	public void verifyPostDescription(String postDescription, Post post) {
		// Setting post description
		post.setPostDescription(postDescription);
		// Sometimes we will have a post width description sometimes not
	}

}
