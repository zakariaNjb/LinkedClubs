package DAO;


import java.util.ArrayList;

//the new imports
import org.hibernate.Session;

import DAO.UTIL.HibernateUtil;
import Services.Entities.Student;




public class DaoStudentImp implements DaoStudent {
	//Attribute
	
	
	//Constructor
	public DaoStudentImp() {}
	
	
	@Override
	public Student add(Student student) {
		
		Session session=HibernateUtil.openSession();
		session.beginTransaction();
		try {
			session.save(student);
		}catch (Exception e) {
			// TODO: handle exception
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		session.getTransaction().commit();
		
        return student;
	}
	
	@Override
	public Student find(String CNE) {
		Session session=HibernateUtil.openSession();
		session.beginTransaction();
		try {
			Student student=session.get(Student.class, CNE);
			session.getTransaction().commit();
			return student;
		}catch(Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		
		return null;
	}
	
	@Override
	public Student update(Student student) {
		Session session=HibernateUtil.openSession();
		try {
			session.beginTransaction();
			Student std=find(student.getCNE());
			std.setEmail(student.getEmail());
			std.setPassword(student.getPassword());
			std.setInstagram(student.getInstagram());
			std.setFacebook(student.getFacebook());
			std.setLevel(student.getLevel());
			std.setMajor(student.getMajor());
			std.setStudentBio(student.getStudentBio());
			
			if (!student.getCoverImage().isEmpty()) {
				std.setCoverImage(student.getCoverImage());
			}
			if (!student.getProfileImage().isEmpty()) {
				std.setProfileImage(student.getProfileImage());
			}
			
			session.update(std);
			
			session.getTransaction().commit();	
			
			return std;
		} catch (Exception e) {
			// TODO: handle exception
			session.getTransaction().rollback();
		}
		// TODO Auto-generated method stub
		return student;
	}


	@Override
	public ArrayList<Student> getAll() {
		Session session = HibernateUtil.openSession();
		session.beginTransaction();
		try {
			@SuppressWarnings("unchecked")
			ArrayList<Student> allStudent = (ArrayList<Student>) session.createSQLQuery("SELECT * FROM student")
					.addEntity(Student.class).list();
			session.getTransaction().commit();

			return allStudent;

		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}

		return null;
	}

}
