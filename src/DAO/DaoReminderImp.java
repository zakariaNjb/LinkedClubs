package DAO;

import java.util.ArrayList;

import org.hibernate.Session;

import DAO.UTIL.HibernateUtil;
import Services.Entities.Reminder;

public class DaoReminderImp implements DaoReminder {

	public DaoReminderImp() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Reminder add(Reminder reminder) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.openSession();
		try {
			session.beginTransaction();
			session.save(reminder);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			// TODO: handle exception
		}
		return reminder;
	}

	@Override
	public ArrayList<Reminder> getStudentReminder(String studentCNE) {
		
		Session session=HibernateUtil.openSession();
		session.beginTransaction();
		try {
			@SuppressWarnings("unchecked")
			ArrayList<Reminder> reminders = (ArrayList<Reminder>) session.createSQLQuery("SELECT * FROM reminder ORDER BY reminderDate DESC").addEntity(Reminder.class).list();
			session.getTransaction().commit();
			
			return reminders;
			
		}catch(Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		
		return null;
	}

}
