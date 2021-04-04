package Services.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="clubsmembers")
public class ClubsMembers {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private int id;
	private String CNE;
	private String clubId;
	private boolean payed;
	
	// Constructors

	public ClubsMembers() {
		// TODO Auto-generated constructor stub
	}
	

	public ClubsMembers(String cNE, String clubId, boolean payed) {
		super();
		CNE = cNE;
		this.clubId = clubId;
		this.payed = payed;
	}
	
	// Getters and setters

	public int getId() {
		return id;
	}

	public String getCNE() {
		return CNE;
	}

	public void setCNE(String cNE) {
		CNE = cNE;
	}

	public String getClubId() {
		return clubId;
	}

	public void setClubId(String clubId) {
		this.clubId = clubId;
	}

	public boolean isPayed() {
		return payed;
	}

	public void setPayed(boolean payed) {
		this.payed = payed;
	}
	
	

}
