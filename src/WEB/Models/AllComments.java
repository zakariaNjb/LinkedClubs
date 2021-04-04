package WEB.Models;

import java.util.ArrayList;

import Services.Entities.Comment;

public class AllComments {
	
	ArrayList<Comment> allComments;
	
	// Constructors
	public AllComments() {
		// TODO Auto-generated constructor stub
	}

	public AllComments(ArrayList<Comment> allComments) {
		super();
		this.allComments = allComments;
	}
	
	// Getters & Setters
	public ArrayList<Comment> getAllComments() {
		return allComments;
	}

	public void setAllComments(ArrayList<Comment> allComments) {
		this.allComments = allComments;
	}

}
