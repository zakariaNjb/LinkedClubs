package DAO;

import java.util.ArrayList;

import org.hibernate.Session;

import DAO.UTIL.HibernateUtil;
import Services.Entities.Post;

public class DaoPostImp implements DaoPost{

	@Override
	public Post add(Post post) {
		// TODO Auto-generated method stub
		Session session=HibernateUtil.openSession();
		try {
			session.beginTransaction();
			session.save(post);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			// TODO: handle exception
		}
		return post;
	}

	@Override
	public ArrayList<Post> getPosts(String clubId) {
		Session session=HibernateUtil.openSession();
		session.beginTransaction();
		try {
			@SuppressWarnings("unchecked")
			ArrayList<Post> postsOfClub = (ArrayList<Post>) session.createSQLQuery("SELECT * FROM post WHERE club_clubId='" + clubId + "' ORDER BY publishedDate DESC").addEntity(Post.class).list();
			session.getTransaction().commit();
			
			return postsOfClub;
			
		}catch(Exception e) {
			session.beginTransaction().rollback();
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public Post update(Post post) {
		Session session=HibernateUtil.openSession();
		try {
			session.beginTransaction();
			Post pst=find(post.getId());
			pst.setPostDescription(post.getPostDescription());
			
			if(!post.getFileLink().isEmpty()) {
				pst.setFileLink(post.getFileLink());
				pst.setFileType(post.getFileType());
			}else {
				
			}
			
			session.update(pst);
			
			session.getTransaction().commit();	
			
			return pst;
		} catch (Exception e) {
			// TODO: handle exception
			session.getTransaction().rollback();
		}
		// TODO Auto-generated method stub
		return post;
	}

	@Override
	public Post find(int id) {
		Session session=HibernateUtil.openSession();
		session.beginTransaction();
		try {
			Post post=session.get(Post.class, id);
			session.getTransaction().commit();
			return post;
		}catch(Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public Post updateLikeNumber(Post post, Boolean isLiked) {
		Session session=HibernateUtil.openSession();
		try {
			session.beginTransaction();
			Post pst=find(post.getId());
			
			if (!isLiked) {
				pst.setLikeNumber(pst.getLikeNumber() + 1);
			}else {
				pst.setLikeNumber(pst.getLikeNumber() - 1);
			}
			
			session.update(pst);
			
			session.getTransaction().commit();	
			
			return pst;
		} catch (Exception e) {
			// TODO: handle exception
			session.getTransaction().rollback();
		}
		// TODO Auto-generated method stub
		return post;
	}

	@Override
	public Post updateCommentNumber(Post post) {
		Session session=HibernateUtil.openSession();
		try {
			session.beginTransaction();
			Post pst=find(post.getId());
			
			pst.setCommentNumber(pst.getCommentNumber() + 1);
			
			session.update(pst);
			
			session.getTransaction().commit();	
			
			return pst;
		} catch (Exception e) {
			// TODO: handle exception
			session.getTransaction().rollback();
		}
		// TODO Auto-generated method stub
		return post;
	}
	
	@Override
	public Post updateNotification(Post post) {
		// TODO Auto-generated method stub
		Session session=HibernateUtil.openSession();
		try {
			session.beginTransaction();
			Post pst=find(post.getId());

			pst.setNotification(post.getNotification());
			
			session.update(pst);

			session.getTransaction().commit();

			return pst;
		} catch (Exception e) {
			// TODO: handle exception
			session.getTransaction().rollback();
		}
		// TODO Auto-generated method stub
		return null;
	}

	@Override
public ArrayList<Post> getAllPosts() {
		
		Session session=HibernateUtil.openSession();
		session.beginTransaction();
		try {
			@SuppressWarnings("unchecked")
			ArrayList<Post> postsOfClub = (ArrayList<Post>) session.createSQLQuery("SELECT * FROM post").addEntity(Post.class).list();
			session.getTransaction().commit();
			
			return postsOfClub;
			
		}catch(Exception e) {
			session.beginTransaction().rollback();
			e.printStackTrace();
		}
		
		return null;
		
	}
	
}