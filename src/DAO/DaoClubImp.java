package DAO;

import java.util.ArrayList;

import org.hibernate.Session;

import DAO.UTIL.HibernateUtil;
import Services.Entities.Club;

public class DaoClubImp implements DaoClub {

	public DaoClubImp() {
		// TODO Auto-generated constructor stub
	}

	public Club add(Club club) {
		Session session = HibernateUtil.openSession();
		try {
			session.beginTransaction();
			session.save(club);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			// TODO: handle exception
		}
		return club;
	}

	@Override
	public Club find(String clubId) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.openSession();
		session.beginTransaction();
		try {
			Club club = session.get(Club.class, clubId);
			session.getTransaction().commit();
			return club;
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public Club update(Club club) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.openSession();
		try {
			session.beginTransaction();
			Club clb = find(club.getClubId());
			clb.setClubName(club.getClubName());
			clb.setFacebook(club.getFacebook());
			clb.setInstagram(club.getInstagram());
			clb.setClubBio(club.getClubBio());
			clb.setMembersNumber(club.getMembersNumber());
			clb.setPassword(club.getPassword());

			if (!club.getImage().isEmpty()) {
				clb.setImage(club.getImage());
			}
			if (!club.getCoverImage().isEmpty()) {
				clb.setCoverImage(club.getCoverImage());
			}

			session.update(clb);

			session.getTransaction().commit();

			return clb;
		} catch (Exception e) {
			// TODO: handle exception
			session.getTransaction().rollback();
		}
		// TODO Auto-generated method stub
		return club;
	}

	// Get all clubs function
	@Override
	public ArrayList<Club> getAllClubs() {
		Session session = HibernateUtil.openSession();
		session.beginTransaction();
		try {
			@SuppressWarnings("unchecked")
			ArrayList<Club> allClubs = (ArrayList<Club>) session.createSQLQuery("SELECT * FROM club")
					.addEntity(Club.class).list();
			session.getTransaction().commit();

			return allClubs;

		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}

		return null;
	}

	// Method for getting club images

	public static ArrayList<Club> getClubImages() {
		Session session = HibernateUtil.openSession();
		session.beginTransaction();
		try {
			@SuppressWarnings("unchecked")
			ArrayList<Club> clubImages = (ArrayList<Club>) session.createSQLQuery("SELECT * FROM club")
					.addEntity(Club.class).list();
			session.getTransaction().commit();

			return clubImages;

		} catch (Exception e) {
			session.beginTransaction().rollback();
			e.printStackTrace();
		}

		return null;
	}

	// Update number of members of a club
	@Override
	public Club updateNumberOfMembers(Club club) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.openSession();
		try {
			session.beginTransaction();
			Club clb = find(club.getClubId());
			
			clb.setMembersNumber(club.getMembersNumber() + 1);

			session.update(clb);

			session.getTransaction().commit();

			return clb;
		} catch (Exception e) {
			// TODO: handle exception
			session.getTransaction().rollback();
		}
		// TODO Auto-generated method stub
		return club;
	}

}
