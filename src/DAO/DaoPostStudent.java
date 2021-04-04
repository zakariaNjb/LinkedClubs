package DAO;

import java.util.ArrayList;

import Services.Entities.PostStudent;

public interface DaoPostStudent {
	public PostStudent add(PostStudent post_student); // Add new line to postStudent table
	public ArrayList<PostStudent> getLikedPosts(String studentCNE); // Get liked posts by a student
	public PostStudent delete(PostStudent postStudent); // Delete occurence in poststudent table
	
}