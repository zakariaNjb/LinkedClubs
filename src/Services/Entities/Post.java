package Services.Entities;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name="post")
public class Post {
	
	@ManyToOne
	private Club club;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private int id;

	private String fileLink;
	private String fileType;
	private Date publishedDate;
	private String postDescription;
	private int likeNumber;
	private int commentNumber;
	
	@OneToOne
	private Notification notification;
	
	// Constructors

	public Notification getNotification() {
		return notification;
	}

	public void setNotification(Notification notification) {
		this.notification = notification;
	}

	public Post() {
		// TODO Auto-generated constructor stub
	}

	public Post(Club club, int id, String fileLink, String fileType, Date publishedDate,
			String postDescription, int likeNumber, int commentNumber) {
		super();
		this.club = club;
		this.id = id;
		this.fileLink = fileLink;
		this.fileType = fileType;
		this.publishedDate = publishedDate;
		this.postDescription = postDescription;
		this.likeNumber = likeNumber;
		this.commentNumber = commentNumber;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}


	public String getFileLink() {
		return fileLink;
	}

	public void setFileLink(String fileLink) {
		this.fileLink = fileLink;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public Date getPublishedDate() {
		return publishedDate;
	}

	public void setPublishedDate(Date publishedDate) {
		this.publishedDate = publishedDate;
	}

	public String getPostDescription() {
		return postDescription;
	}

	public void setPostDescription(String postDescription) {
		this.postDescription = postDescription;
	}

	public int getLikeNumber() {
		return likeNumber;
	}

	public void setLikeNumber(int likeNumber) {
		this.likeNumber = likeNumber;
	}

	public int getCommentNumber() {
		return commentNumber;
	}

	public void setCommentNumber(int commentNumber) {
		this.commentNumber = commentNumber;
	}

	public Club getClub() {
		return club;
	}

	public void setClub(Club club) {
		this.club = club;
	}
	
	

}
