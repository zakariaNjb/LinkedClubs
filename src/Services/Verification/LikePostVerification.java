package Services.Verification;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import DAO.DaoPost;
import DAO.DaoPostStudent;
import Services.Entities.Post;
import Services.Entities.PostStudent;
import Services.Entities.Student;

public class LikePostVerification {
	//variables
	private DaoPostStudent daoPostStudentImp;
	private DaoPost daoPostImp;

	//Constructor
	public LikePostVerification() {}
	public LikePostVerification(DaoPostStudent daoPostStudentImp, DaoPost daoPostImp) {
		this.daoPostStudentImp=daoPostStudentImp;
		this.daoPostImp = daoPostImp;
	}

	public PostStudent verifyLikePost(HttpServletRequest request) {
		//Getting parameters
		int postId=Integer.parseInt((String) request.getParameter("id"));
		boolean isLiked= Boolean.parseBoolean(request.getParameter("isLiked"));
		
		HttpSession session=request.getSession();
		Student student=(Student) session.getAttribute("student");
		String studentId=student.getCNE();
		
		// Get the current post
		Post post = this.daoPostImp.find(postId);
		
		// Update like number
		this.daoPostImp.updateLikeNumber(post, isLiked);
		
		PostStudent post_student=new PostStudent();
		post_student.setPost_id(postId);
		post_student.setStudent_id(studentId);
		//post_student.setId(92);
		
		if (isLiked) {
			PostStudent postStudent= this.daoPostStudentImp.delete(post_student);
			return postStudent;
		}else {
			PostStudent postStudent= this.daoPostStudentImp.add(post_student);
			return postStudent;
		}

	}
}
