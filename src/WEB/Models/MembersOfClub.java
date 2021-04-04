package WEB.Models;

import java.util.ArrayList;

import Services.Entities.Student;

public class MembersOfClub {
	
	private ArrayList<Student> members;

	public MembersOfClub(ArrayList<Student> members) {
		super();
		this.members = members;
	}

	public MembersOfClub() {
		// TODO Auto-generated constructor stub
	}

	public ArrayList<Student> getMember() {
		return members;
	}

	public void setMember(ArrayList<Student> members) {
		this.members = members;
	}

}
