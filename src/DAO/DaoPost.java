package DAO;

import java.util.ArrayList;

import Services.Entities.Post;

public interface DaoPost {
	public Post add(Post post); // Add new post
	public ArrayList<Post> getPosts(String clubId); // Get the posts of a specific club
	public Post update(Post post); // Update post information
	public Post find(int id); // Find post by id
	public Post updateLikeNumber(Post post, Boolean isLiked); // Update like number of a post
	public Post updateCommentNumber(Post post); // Update comment number of a post
	public Post updateNotification(Post post); // Update notification of a post
	public ArrayList<Post> getAllPosts(); // getAll posts
}