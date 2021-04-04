package WEB.Controllers;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.DaoPost;
import DAO.DaoPostImp;
import Services.Entities.Post;
import Services.Verification.PostUpdateVerification;

/**
 * Servlet implementation class UpdatePostServlet
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 50, maxRequestSize = 1024 * 1024 * 5 * 5)
@WebServlet("/UpdatePost")
public class UpdatePostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private DaoPost daoPostImp;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdatePostServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		this.daoPostImp = new DaoPostImp();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unused")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		PostUpdateVerification postUpdateForm = new PostUpdateVerification(this.daoPostImp);
		
		Post post = postUpdateForm.verifyUpdatedPost(request);
		
		response.sendRedirect("/LinkedClubs/ProfileClub");
		
	}

}
