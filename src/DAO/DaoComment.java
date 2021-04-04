package DAO;

import java.util.ArrayList;

import Services.Entities.Comment;

public interface DaoComment {
	
	public Comment add(Comment comment); // Add new comment function
	public ArrayList<Comment> getAllComments(); // Get all comments function

}
