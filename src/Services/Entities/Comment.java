package Services.Entities;

import java.util.Date;

import javax.persistence.*;
@Entity
@Table(name="comment")
public class Comment {
	
	@ManyToOne
	private Post post;
	
	@ManyToOne
	private Student student;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private int id;
	private String content;
	private Date date;

	public Comment() {
		// TODO Auto-generated constructor stub
	}

	public Comment(Post post, String content, Date date, Student student) {
		super();
		this.post = post;
		this.content = content;
		this.date = date;
		this.student = student;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public int getId() {
		return id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
	
	

}
