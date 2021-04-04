package Services.Verification;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import DAO.DaoClub;
import DAO.DaoClubImp;
import DAO.DaoJoinClub;
import Services.Entities.Club;
import Services.Entities.ClubsMembers;
import Services.Entities.Student;

public class JoinClubVerification {
	
	DaoJoinClub daoJoinClubImp;
	DaoClub daoClubImp = new DaoClubImp();

	public JoinClubVerification(DaoJoinClub daoJoinClubImp) {
		// TODO Auto-generated constructor stub
		this.daoJoinClubImp = daoJoinClubImp;
	}
	
	
	// Main verify function
	public ClubsMembers verifyJoinClub(HttpServletRequest request) {
		
		// Getting the current student that want to join
		HttpSession session = request.getSession();
		Student student = (Student) session.getAttribute("student");
		
		// Getting the CNE of the current student
		String studentCNE = student.getCNE();
		
		// Getting the club that the student want to join
		String clubId = request.getParameter("clubId");
		
		// Create a ClubsMmbers object
		ClubsMembers clubsMembers = new ClubsMembers();
		clubsMembers.setClubId(clubId);
		clubsMembers.setCNE(studentCNE);
		clubsMembers.setPayed(false);
		
		Club club = this.daoClubImp.find(clubId);
		
		
		clubsMembers = this.daoJoinClubImp.add(clubsMembers);
		if (clubsMembers!=null) {
			club = this.daoClubImp.updateNumberOfMembers(club);
			session.setAttribute("club", club);
			
		}
		
		return clubsMembers;
	}
	

}
