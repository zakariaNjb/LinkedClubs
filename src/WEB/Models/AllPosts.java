package WEB.Models;

import java.util.ArrayList;


import Services.Entities.Post;

public class AllPosts {

	ArrayList<Post> Posts;

	public AllPosts() {
		super();
	}

	public AllPosts(ArrayList<Post> posts) {
		super();
		Posts = posts;
	}

	public ArrayList<Post> getPosts() {
		return Posts;
	}

	public void setPosts(ArrayList<Post> posts) {
		Posts = posts;
	}
	
}
