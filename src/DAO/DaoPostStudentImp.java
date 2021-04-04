package DAO;

import java.util.ArrayList;

import org.hibernate.Session;

import DAO.UTIL.HibernateUtil;
import Services.Entities.PostStudent;

public class DaoPostStudentImp implements DaoPostStudent {

	@Override
	public PostStudent add(PostStudent post_student) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.openSession();
		try {
			session.beginTransaction();
			session.save(post_student);
			session.getTransaction().commit();
			return post_student;
		} catch (Exception e) {
			session.getTransaction().rollback();
			// TODO: handle exception
		}
		return null;
	}

	@Override
	public ArrayList<PostStudent> getLikedPosts(String studentCNE) {
		Session session = HibernateUtil.openSession();
		session.beginTransaction();
		try {
			@SuppressWarnings("unchecked")
			ArrayList<PostStudent> getLikedClubs = (ArrayList<PostStudent>) session
					.createSQLQuery("SELECT * FROM poststudent WHERE student_id='" + studentCNE + "'")
					.addEntity(PostStudent.class).list();
			session.getTransaction().commit();

			return getLikedClubs;

		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public PostStudent delete(PostStudent postStudent) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.openSession();
		try {
			session.beginTransaction();

			@SuppressWarnings("unchecked")
			ArrayList<PostStudent> postStudentsList = (ArrayList<PostStudent>) session.createSQLQuery("SELECT * FROM poststudent WHERE student_id='"
					+ postStudent.getStudent_id() + "' AND post_id='" + postStudent.getPost_id() + "'")
					.addEntity(PostStudent.class).list();

			session.delete(postStudentsList.get(0));
			session.getTransaction().commit();
			return postStudent;
		} catch (Exception e) {
			session.getTransaction().rollback();
			// TODO: handle exception
		}
		return null;
	}

}