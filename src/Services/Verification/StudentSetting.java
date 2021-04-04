package Services.Verification;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import DAO.DaoStudent;
import DAO.DaoStudentImp;
import Services.Entities.Student;

public class StudentSetting {
	// Variables
	private HashMap<String, String> err = new HashMap<String, String>();
	private DaoStudentImp daoStudentImp;

	// Constructors
	public StudentSetting() {
	}

	public StudentSetting(DaoStudent daoStudentImp) {
		this.daoStudentImp = (DaoStudentImp) daoStudentImp;
	}

	// Getters && Setters
	public HashMap<String, String> getErr() {
		return err;
	}

	public void setErr(HashMap<String, String> err) {
		this.err = err;
	}

	// Setting student profile verification function
	public Student verifySettingStudent(HttpServletRequest request) throws IOException, ServletException {

		// Get the current project path
		String applicationPath = request.getServletContext().getRealPath("");

		// Get the upload folder for profile images
		String profileImagesUploadDirectory = applicationPath + File.separator + "Images" + File.separator
				+ "profileImages";

		// Get the upload folder for cover images
		String coverImagesUploadDirectory = applicationPath + File.separator + "Images" + File.separator
				+ "coverImages";

		// Getting files

		Part profileImagePart = request.getPart("profileImage");
		Part coverImagePart = request.getPart("backgroundImage");

		// Getting parameters;
		String CNE = (String) request.getParameter("CNE");
		String major = (String) request.getParameter("major");
		String level = (String) request.getParameter("level");
		String email = (String) request.getParameter("email");
		String password1 = (String) request.getParameter("password1");
		String password2 = (String) request.getParameter("password2");
		String instagram = (String) request.getParameter("instagram");
		String facebook = (String) request.getParameter("facebook");
		String profileImage = (String) profileImagePart.getSubmittedFileName();
		String coverImage = (String) coverImagePart.getSubmittedFileName();
		String bio = (String) request.getParameter("bio");

		// Calling verify functions
		Student student = new Student();
		verifyMajor(major, student);
		verifyLevel(level, student);
		verifyEmail(email, student);
		verifyPassowrd(password1, password2, student);
		verifyInstagram(instagram, student);
		verifyFacebook(facebook, student);
		verifyProfileImg(profileImage, student);
		verifyCoverImg(coverImage, student);
		verifyBio(bio, student);

		// Testing error
		try {
			if (err.isEmpty()) {
				// Update student in the dataBase
				if (!profileImage.isEmpty()) {
					profileImagePart.write(profileImagesUploadDirectory + File.separator + profileImage);
				}
				if (!coverImage.isEmpty()) {
					coverImagePart.write(coverImagesUploadDirectory + File.separator + coverImage);
				}

				try {

					student.setCNE(CNE);

					String hashedPassword = Hashing.generateStorngPasswordHash(password1);
					student.setPassword(hashedPassword);
					return this.daoStudentImp.update(student);

				} catch (Exception e) {
					// TODO: handle exception
				}

			} else {
				throw new Exception("Update has failed!!");
			}
		} catch (Exception e) {
			// TODO: handle exception
			err.put("errUpdate", e.getMessage());
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

	public void verifyFacebook(String facebook, Student student) {
		// Setting student facebook
		student.setFacebook(facebook);

		try {
			if (facebook.isEmpty()) {
				throw new Exception("Please insert a valid facebook account");
			}
		} catch (Exception e) {
			// TODO: handle exception
			err.put("errFacebook", e.getMessage());
		}
	}

	public void verifyInstagram(String instagram, Student student) {
		// Setting student instagram
		student.setInstagram(instagram);

		try {
			if (instagram.isEmpty()) {
				throw new Exception("Please insert a valid instagram account");
			}
		} catch (Exception e) {
			// TODO: handle exception
			err.put("errInstagram", e.getMessage());
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

	public void verifyBio(String bio, Student student) {
		// Setting student major
		student.setStudentBio(bio);

		try {
			if (bio.isEmpty())
				throw new Exception("Please insert a valid bio");
		} catch (Exception e) {
			// TODO: handle exception
			err.put("errbio", e.getMessage());
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

	public void verifyProfileImg(String profileImage, Student student) {
		// Setting student major
		student.setProfileImage(profileImage);
	}

	public void verifyCoverImg(String backgroundImage, Student student) {
		// Setting student major
		student.setCoverImage(backgroundImage);
	}

}