package Services.Verification;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import DAO.DaoJoinClub;
import Services.Entities.Club;
import Services.Entities.ClubsMembers;

public class AcceptDeclineMembersVerification {

	private DaoJoinClub daoJoinClubImp;

	public AcceptDeclineMembersVerification(DaoJoinClub daoJoinClubImp) {
		// TODO Auto-generated constructor stub
		this.daoJoinClubImp = daoJoinClubImp;
	}

	public ClubsMembers verifyClubsMembers(HttpServletRequest request) {

		// Get the clicked Button
		String clickedBtn = request.getParameter("btnClicked");

		// Get the member we'd like to accept
		String memberCNE = request.getParameter("cne");

		// Get the current club in the session
		HttpSession session = request.getSession();
		Club club = (Club) session.getAttribute("club");

		// Get the current club ID
		String clubId = club.getClubId();

		ClubsMembers clubsMembers = new ClubsMembers();
		clubsMembers.setClubId(clubId);
		clubsMembers.setCNE(memberCNE);

		if (clickedBtn.equals("accept")) {
			// Call update (accept) function
			clubsMembers = this.daoJoinClubImp.update(clubsMembers);

		} else if (clickedBtn.equals("decline")) {
			// Call decline (delete) function
			clubsMembers = this.daoJoinClubImp.delete(clubsMembers);
		}

		return clubsMembers;
	}

}
