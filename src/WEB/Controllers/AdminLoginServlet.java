package WEB.Controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Services.Verification.AdminLoginVerification;

/**
 * Servlet implementation class AdminLoginServlet
 */
@WebServlet("/adminLogin")
public class AdminLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String adminUsername = request.getParameter("username");
		String adminPassword = request.getParameter("password");
		
		AdminLoginVerification adminLoginVerification = new AdminLoginVerification();
		adminLoginVerification.verifyAdminLogin(request);
		
		if (adminLoginVerification.getErr().isEmpty()) {
			
			HttpSession session = request.getSession();
			session.setAttribute("adminUsername", adminUsername);
			session.setAttribute("adminPassword", adminPassword);
			
			response.sendRedirect("/LinkedClubs/administration");
		}else {
			request.setAttribute("err", adminLoginVerification.getErr());
			request.getServletContext().getRequestDispatcher("/WEB-INF/JSP/adminLogin.jsp").forward(request, response);
		}
		
	}

}
