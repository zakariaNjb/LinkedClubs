package Services.Entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="club")
public class Club {
	
	@Id
	private String clubId;
	private String clubName;
	private String password;
	private String image;
	private String coverImage;
	private int membersNumber;
	private String clubBio;
	private String facebook;
	private String instagram;
	
	// Constructors

	public Club() {}

	public Club(String clubId, String clubName, String password, String image, String coverImage, int membersNumber,
			String clubBio, String facebook, String instagram) {
		super();
		this.clubId = clubId;
		this.clubName = clubName;
		this.password = password;
		this.image = image;
		this.coverImage = coverImage;
		this.membersNumber = membersNumber;
		this.clubBio = clubBio;
		this.facebook = facebook;
		this.instagram = instagram;
	}

	// Getters and Setters

	public String getClubId() {
		return clubId;
	}

	public void setClubId(String clubId) {
		this.clubId = clubId;
	}

	public String getClubName() {
		return clubName;
	}

	public void setClubName(String clubName) {
		this.clubName = clubName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getMembersNumber() {
		return membersNumber;
	}

	public void setMembersNumber(int membersNumber) {
		this.membersNumber = membersNumber;
	}

	public String getClubBio() {
		return clubBio;
	}

	public void setClubBio(String clubBio) {
		this.clubBio = clubBio;
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


	public String getCoverImage() {
		return coverImage;
	}


	public void setCoverImage(String coverImage) {
		this.coverImage = coverImage;
	}
	
	

}
