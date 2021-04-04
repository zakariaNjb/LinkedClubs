package DAO;

import java.util.ArrayList;

import org.hibernate.Session;

import DAO.UTIL.HibernateUtil;
import Services.Entities.Notification;

public class DaoNotificationImp implements DaoNotification {

	@Override
	public Notification add(Notification notification) {
		Session session=HibernateUtil.openSession();
		try {
			session.beginTransaction();
			session.save(notification);
			session.getTransaction().commit();
			return notification;
		} catch (Exception e) {
			session.getTransaction().rollback();
			 //TODO handle exception
		}
		 //TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Notification> getAll(String cne) {
		Session session=HibernateUtil.openSession();
		session.beginTransaction();
		try {
			@SuppressWarnings("unchecked")
			ArrayList<Notification> getAllNotifications = (ArrayList<Notification>) session.createSQLQuery("SELECT * FROM notification WHERE idStudent='"+cne+"' ORDER BY id DESC").addEntity(Notification.class).list();
			session.getTransaction().commit();
			
			return getAllNotifications;
			
		}catch(Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public Notification update(Notification notification) {
		Session session=HibernateUtil.openSession();
		try {
			session.beginTransaction();
			Notification ntf = session.get(Notification.class, notification.getId());
			
			ntf.setIdStudent(notification.getIdStudent());
			
			
			session.update(ntf);
			
			session.getTransaction().commit();	
			
			return ntf;
		} catch (Exception e) {
			// TODO: handle exception
			session.getTransaction().rollback();
		}
		return null;
	}

	

}
