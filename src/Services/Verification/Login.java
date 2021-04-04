package Services.Verification;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import DAO.DaoStudent;
import Services.Entities.Student;

public class Login {
	// Variables
	private HashMap<String, String> err = new HashMap<String, String>();
	DaoStudent daoStudentImp;

	// Constructors
	public Login() {
	}

	public Login(DaoStudent daoStudentImp) {
		this.daoStudentImp = daoStudentImp;
	}

	// Getters && setters
	public HashMap<String, String> getErr() {
		return err;
	}

	public void setErr(HashMap<String, String> err) {
		this.err = err;
	}

	// Verify login function
	public Student verifyLogin(HttpServletRequest request) {
		// Getting params
		String CNE = (String) request.getParameter("cne");
		String password = (String) request.getParameter("password");

		// calling verification functions
		Student student = new Student();
		verifyCNE(CNE, student);
		verifyPassword(password);

		// Looking for the existing Student
		if (err.isEmpty()) {
			Student std = this.daoStudentImp.find(CNE);
			
			// I need decrept the std password and compare it with student.password
			if (std != null) {
				try {
					if (!std.getCNE().equals(CNE) || !Hashing.validatePassword(password, std.getPassword()))
						err.put("errLogin", "Login has failed");
					else {
						return std;
					}
				} catch (NoSuchAlgorithmException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvalidKeySpecException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else
				err.put("errLogin", "Login has failed");

		}

		return student;
	}

	// Functions
	public void verifyCNE(String CNE, Student student) {
		// Setting student CNES
		student.setCNE(CNE);

		try {
			if (CNE.isEmpty()) {
				throw new Exception("Please insert a valid CNE");
			} else {
				if (CNE.length() != 10)
					throw new Exception("Please insert a valid CNE");
			}
		} catch (Exception e) {
			// TODO: handle exception
			err.put("errCNE", e.getMessage());
		}
	}

	public void verifyPassword(String password) {
		try {
			if (password.isEmpty())
				throw new Exception("Please make to insert a valid password");
		} catch (Exception e) {
			// TODO: handle exception
			err.put("errPassword", e.getMessage());
		}
	}

}
