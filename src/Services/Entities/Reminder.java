package Services.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="reminder")
public class Reminder {
	
	@ManyToOne
	private Student student;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private int id;

	private String title;
	private String reminderDate;

	public Reminder() {
		// TODO Auto-generated constructor stub
	}
	
	

	public Reminder(Student student, int id, String title, String reminderDate) {
		super();
		this.student = student;
		this.id = id;
		this.title = title;
		this.reminderDate = reminderDate;
	}



	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getReminderDate() {
		return reminderDate;
	}

	public void setReminderDate(String reminderDate) {
		this.reminderDate = reminderDate;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

}
