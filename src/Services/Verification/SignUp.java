package Services.Verification;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import DAO.DaoStudent;
import DAO.DaoStudentImp;
import Services.Entities.Student;

public class SignUp {
	// Variables
	private HashMap<String, String> err = new HashMap<String, String>();
	private DaoStudentImp daoStudentImp;

	// Constructors
	public SignUp() {
	}

	public SignUp(DaoStudent daoStudentImp) {
		this.daoStudentImp = (DaoStudentImp) daoStudentImp;
	}

	// Getters && Setters
	public HashMap<String, String> getErr() {
		return err;
	}

	public void setErr(HashMap<String, String> err) {
		this.err = err;
	}

	// Main Verify function
	public Student verifySignUp(HttpServletRequest request) {

		// Grapping params
		String CNE = (String) request.getParameter("cne");
		String fullName = (String) request.getParameter("fullname");
		String major = (String) request.getParameter("major");
		String birthDate = (String) request.getParameter("birthdate");
		String gender = (String) request.getParameter("gender");
		String level = (String) request.getParameter("level");
		String email = (String) request.getParameter("email");
		String password1 = (String) request.getParameter("password");
		String password2 = (String) request.getParameter("password_confirmation");
		String terms = (String) request.getParameter("accept");

		// Calling verification functions
		Student student = new Student();
		verifyCNE(CNE, student);
		verifyFullName(fullName, student);
		verifyBirthDate(birthDate, student);
		verifyGender(gender, student);
		verifyLevel(level, student);
		verifyMajor(major, student);
		verifyEmail(email, student);
		verifyPassowrd(password1, password2, student);
		verifyAcceptConditions(terms);
		
		// Add default profile images 
		if (gender.equals("Male")) {
			student.setImage("boy_profile_image.jpg");
		}else {
			student.setImage("girl_profile_image.jpg");
		}
		
		student.setCoverImage("default_cover_image.jpg");

		// Insert the new student into the dataBase
		if (err.isEmpty()) {
			Student std = this.daoStudentImp.find(CNE);
			if (std == null) {
				// Hashing the password before inserting the student in database
				try {
					String hashedPassword = Hashing.generateStorngPasswordHash(password1);
					student.setPassword(hashedPassword);
					
					this.daoStudentImp.add(student);
				} catch (NoSuchAlgorithmException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvalidKeySpecException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} else
				err.put("errCNE", "This id is already exist");
		}

		return student;
	}

	// Functions
	public void verifyEmail(String email, Student student) {
		// Setting student email
		student.setEmail(email);

		try {
			if (email != null) {
				if (!email.matches("([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)")) {
					throw new Exception("Please insert a valid email.");
				}
			} else {
				throw new Exception("Make sure to insert a valid email.");
			}
		} catch (Exception e) {
			// TODO: handle exception
			err.put("errEmail", e.getMessage());

		}
	}

	public void verifyFullName(String fullName, Student student) {
		// Setting student fullName
		student.setFullName(fullName);

		try {
			if (fullName.isEmpty()) {
				throw new Exception("Please insert a valid name");
			} else {
				String regx = "^[\\p{L} .'-]+$";
				Pattern pattern = Pattern.compile(regx, Pattern.CASE_INSENSITIVE);
				Matcher matcher = pattern.matcher(fullName);
				if (matcher.find() != true)
					throw new Exception("Please insert a valid full name");
			}
		} catch (Exception e) {
			// TODO: handle exception
			err.put("errFullName", e.getMessage());
		}
	}

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

	public void verifyBirthDate(String birthDate, Student student) {
		// Setting student birthday
		student.setBirthDate(birthDate);

		try {
			if (birthDate.isEmpty()) {
				throw new Exception("Please insert a valid birth date");
			}
		} catch (Exception e) {
			// TODO: handle exception
			err.put("errBirthDate", e.getMessage());
		}
	}

	public void verifyGender(String gender, Student student) {
		// Setting student gender
		student.setGender(gender);

		try {
			if (gender.isEmpty())
				throw new Exception("Please select one of the gender choices");
			else {
				if (!gender.equals("Male") && !gender.equals("Female"))
					throw new Exception("Please select one of the gender choices");
			}
		} catch (Exception e) {
			// TODO: handle exception
			err.put("errGender", e.getMessage());
		}
	}

	public void verifyLevel(String level, Student student) {
		// Setting student level
		student.setLevel(level);

		try {
			if (level.isEmpty())
				throw new Exception("Please select your level");
		} catch (Exception e) {
			// TODO: handle exception
			err.put("errLevel", e.getMessage());
		}
	}

	public void verifyMajor(String major, Student student) {
		// Setting student major
		student.setMajor(major);

		try {
			if (major.isEmpty())
				throw new Exception("Please select your major");
		} catch (Exception e) {
			// TODO: handle exception
			err.put("errMajor", e.getMessage());
		}
	}

	public void verifyPassowrd(String password1, String password2, Student student) {
		// Setting student password
		student.setPassword(password1);

		try {
			if (password1.isEmpty() && password2.isEmpty())
				throw new Exception("Please insert a valid password");
			else if (!password1.equals(password2))
				throw new Exception("Passwords do not match");
		} catch (Exception e) {
			// TODO: handle exception
			err.put("errPassword", e.getMessage());
		}
	}

	public void verifyAcceptConditions(String terms) {

		try {
			if (terms.isEmpty())
				throw new Exception("Please accept the terms and conditions");
		} catch (Exception e) {
			// TODO: handle exception
			err.put("errAccept", e.getMessage());

		}
	}

}
