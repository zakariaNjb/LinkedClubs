package WEB.Models;

import java.util.ArrayList;

import Services.Entities.Post;

public class PostsOfClub {
	
	private ArrayList<Post> postsOfClub;

	public PostsOfClub() {
		// TODO Auto-generated constructor stub
	}
	
	public PostsOfClub(ArrayList<Post> postsOfClub) {
		super();
		this.postsOfClub = postsOfClub;
	}

	public ArrayList<Post> getPostsOfClub() {
		return postsOfClub;
	}

	public void setPostsOfClub(ArrayList<Post> postsOfClub) {
		this.postsOfClub = postsOfClub;
	}


}
