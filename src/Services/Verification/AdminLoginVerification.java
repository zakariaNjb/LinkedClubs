package Services.Verification;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

public class AdminLoginVerification {
	
	private HashMap<String, String> err = new HashMap<String, String>();

	public AdminLoginVerification() {
		// TODO Auto-generated constructor stub
	}
	
	// Main Function 
	
	public void verifyAdminLogin(HttpServletRequest request) {
		// Getting request parametters
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		verifyUsername(username);
		verifyPassword(password);
		
		
	}
	
	// Functions
		public void verifyUsername(String username) {

			try {
				if (username.isEmpty()) {
					throw new Exception("Please insert a valid username");
				}else if (!(username.equals("admin"))) {
					throw new Exception("Please insert a valid username");
				}
			} catch (Exception e) {
				// TODO: handle exception
				err.put("errUsername", e.getMessage());
			}
		}

		public void verifyPassword(String password) {
			try {
				if (password.isEmpty()) {
					throw new Exception("Please make sure to insert a valid password");
				}else if (!(password.equals("admin"))) {
					throw new Exception("Please make sure to insert a valid password");
				}
			} catch (Exception e) {
				// TODO: handle exception
				err.put("errPassword", e.getMessage());
			}
		}

		public HashMap<String, String> getErr() {
			return err;
		}

		public void setErr(HashMap<String, String> err) {
			this.err = err;
		}

}
