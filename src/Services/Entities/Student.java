package Services.Entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="student")
public class Student implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	private String CNE;
	
	private String fullName;
	private String gender;
	private String email;
	private String level;
	private String birthDate;
	private String major;
	private String password;
	private String profileImage;
	private String coverImage;
	private String studentBio;
	private String facebook;
	private String instagram;
	
	//Constructor
	public Student() {}
	
	public Student(String cNE, String fullName, String gender, String email, String level, String birthDate,
			String major, String password, String profileImage, String coverImage, String studentBio, String facebook, String instagram) {
		super();
		CNE = cNE;
		this.fullName = fullName;
		this.gender = gender;
		this.email = email;
		this.level = level;
		this.birthDate = birthDate;
		this.major = major;
		this.password = password;
		this.profileImage = profileImage;
		this.coverImage = coverImage;
		this.studentBio = studentBio;
		this.facebook = facebook;
		this.instagram = instagram;
	}




	//Getters && Setters
	public String getCNE() {
		return CNE;
	}
	public void setCNE(String cNE) {
		CNE = cNE;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getImage() {
		return profileImage;
	}
	public void setImage(String image) {
		this.profileImage = image;
	}
	public String getStudentBio() {
		return studentBio;
	}
	public void setStudentBio(String studentBio) {
		this.studentBio = studentBio;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getFacebook() {
		return facebook;
	}
	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}
	public String getInstagram() {
		return instagram;
	}
	public void setInstagram(String instagram) {
		this.instagram = instagram;
	}

	public String getProfileImage() {
		return profileImage;
	}

	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}

	public String getCoverImage() {
		return coverImage;
	}

	public void setCoverImage(String coverImage) {
		this.coverImage = coverImage;
	}
	
	
	
	
	
	
	
}
