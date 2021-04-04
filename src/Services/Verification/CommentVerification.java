package Services.Verification;

import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import DAO.DaoComment;
import DAO.DaoPost;
import DAO.DaoPostImp;
import Services.Entities.Comment;
import Services.Entities.Post;
import Services.Entities.Student;

public class CommentVerification {

	private HashMap<String, String> err = new HashMap<String, String>();
	private DaoComment daoCommentImp;
	private DaoPost daoPostImp;

	public CommentVerification() {
		// TODO Auto-generated constructor stub
	}

	// Constructors
	public CommentVerification(DaoComment daoCommentImp) {
		this.daoCommentImp = daoCommentImp;
	}
	
	// Main verify function
	public Comment verifyComment(HttpServletRequest request) {
		
		// Getting parametters
		String commentContent = request.getParameter("comment");
		Date commentDate = new Date();
		int post_id = Integer.parseInt(request.getParameter("postId"));
		
		this.daoPostImp = new DaoPostImp();
		Post post = this.daoPostImp.find(post_id);
		
		HttpSession session = request.getSession();
		Student student = (Student) session.getAttribute("student");
		
		Comment comment = new Comment();
		verifyCommentContent(commentContent, comment );
		
		comment.setDate(commentDate);
		comment.setPost(post);
		comment.setStudent(student);
		
		if (this.err.isEmpty()) {
			this.daoCommentImp.add(comment);
			this.daoPostImp.updateCommentNumber(post);
		}
		
		return comment;
	}
	
	public HashMap<String, String> getErr() {
		return err;
	}

	public void setErr(HashMap<String, String> err) {
		this.err = err;
	}

	// verification functions
	public void verifyCommentContent(String commentContent, Comment comment) {
		// Setting comment content
		
		comment.setContent(commentContent);
		
		try {
			if (commentContent.isEmpty()) {
				throw new Exception("Please write your comment !");
			}
		} catch (Exception e) {
			// TODO: handle exception
			err.put("errCommentContent", e.getMessage());
		}
	}

}
