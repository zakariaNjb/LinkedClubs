package DAO;

import java.util.ArrayList;

import org.hibernate.Session;

import DAO.UTIL.HibernateUtil;
import Services.Entities.Club;
import Services.Entities.ClubsMembers;
import Services.Entities.Student;

public class DaoJoinClubImp implements DaoJoinClub {

	public DaoJoinClubImp() {
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<ClubsMembers> readMembers(String clubId) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.openSession();
		session.beginTransaction();
		try {
			ArrayList<ClubsMembers> getAllClubsAndMembers = (ArrayList<ClubsMembers>) session
					.createSQLQuery("SELECT * FROM clubsmembers WHERE clubId='" + clubId + "'")
					.addEntity(ClubsMembers.class).list();
			session.getTransaction().commit();

			return getAllClubsAndMembers;

		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}

		return null;

	}

	@Override
	public ArrayList<Student> getMembers(ArrayList<ClubsMembers> membersCNE) {
		// TODO Auto-generated method stub

		DaoStudent daoStudentImp = new DaoStudentImp();

		ArrayList<Student> members = new ArrayList<Student>();

		for (ClubsMembers clubsMembers : membersCNE) {
			members.add(daoStudentImp.find(clubsMembers.getCNE()));
		}

		return members;
	}

	@Override
	public ArrayList<ClubsMembers> readClubs(String CNE) {
		Session session = HibernateUtil.openSession();
		session.beginTransaction();
		try {
			@SuppressWarnings("unchecked")
			ArrayList<ClubsMembers> getAllClubsAndMembers = (ArrayList<ClubsMembers>) session
					.createSQLQuery("SELECT * FROM clubsmembers WHERE CNE='" + CNE + "'").addEntity(ClubsMembers.class)
					.list();
			session.getTransaction().commit();

			return getAllClubsAndMembers;

		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public ArrayList<Club> getClubs(ArrayList<ClubsMembers> clubIds) {
		// TODO Auto-generated method stub

		DaoClub daoClubImp = new DaoClubImp();

		ArrayList<Club> clubs = new ArrayList<Club>();

		for (ClubsMembers clubsMembers : clubIds) {
			clubs.add(daoClubImp.find(clubsMembers.getCNE()));
		}

		return clubs;
	}

	@Override
	public ClubsMembers delete(ClubsMembers clubsMembers) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.openSession();
		try {
			session.beginTransaction();

			@SuppressWarnings("unchecked")
			ArrayList<ClubsMembers> clubsMembersList = (ArrayList<ClubsMembers>) session
					.createSQLQuery("SELECT * FROM clubsmembers WHERE CNE='" + clubsMembers.getCNE() + "' AND clubId='"
							+ clubsMembers.getClubId() + "'")
					.addEntity(ClubsMembers.class).list();

			session.delete(clubsMembersList.get(0));
			session.getTransaction().commit();
			return clubsMembers;
		} catch (Exception e) {
			session.getTransaction().rollback();
			return null;
			// TODO: handle exception
		}
	}

	@Override
	public ClubsMembers update(ClubsMembers clubsMembers) {
		Session session = HibernateUtil.openSession();
		try {
			session.beginTransaction();

			@SuppressWarnings("unchecked")
			ArrayList<ClubsMembers> clubsMembersList = (ArrayList<ClubsMembers>) session
					.createSQLQuery("SELECT * FROM clubsmembers WHERE CNE='" + clubsMembers.getCNE() + "' AND clubId='"
							+ clubsMembers.getClubId() + "'")
					.addEntity(ClubsMembers.class).list();

			ClubsMembers clbMembers = clubsMembersList.get(0);

			clbMembers.setClubId(clubsMembers.getClubId());
			clbMembers.setCNE(clubsMembers.getCNE());
			clbMembers.setPayed(true);

			session.update(clbMembers);

			session.getTransaction().commit();

			return clbMembers;
		} catch (Exception e) {
			// TODO: handle exception
			session.getTransaction().rollback();
		}
		// TODO Auto-generated method stub
		return clubsMembers;
	}

	@Override
	public ClubsMembers add(ClubsMembers clubsMembers) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.openSession();
		try {
			session.beginTransaction();

			@SuppressWarnings("unchecked")
			ArrayList<ClubsMembers> clubsMembersList = (ArrayList<ClubsMembers>) session
					.createSQLQuery("SELECT * FROM clubsmembers WHERE CNE='" + clubsMembers.getCNE() + "' AND clubId='"
							+ clubsMembers.getClubId() + "'")
					.addEntity(ClubsMembers.class).list();

			if (clubsMembersList.isEmpty()) {

				session.save(clubsMembers);
				session.getTransaction().commit();
				return clubsMembers;
			}
		} catch (Exception e) {
			session.getTransaction().rollback();
			// TODO: handle exception
		}
		return null;
	}

	@Override
	public ArrayList<ClubsMembers> getAll() {
		Session session = HibernateUtil.openSession();
		session.beginTransaction();
		try {
			@SuppressWarnings("unchecked")
			ArrayList<ClubsMembers> allClubsMembers = (ArrayList<ClubsMembers>) session
					.createSQLQuery("SELECT * FROM clubsmembers").addEntity(ClubsMembers.class).list();
			session.getTransaction().commit();

			return allClubsMembers;

		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}

		return null;
	}

}
