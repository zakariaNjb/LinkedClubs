package Services.Verification;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import DAO.DaoClub;
import Services.Entities.Club;

public class AdminVerification {

	private HashMap<String, String> err = new HashMap<String, String>();
	private DaoClub daoClubImp;

	// constructure
	public AdminVerification() {
		super();
	}

	public AdminVerification(DaoClub daoClubImp) {
		super();

		this.daoClubImp = daoClubImp;
	}

	public Club verifyClub(HttpServletRequest request) throws IOException, ServletException {

		String clubName = request.getParameter("ClubId");
		String password = request.getParameter("password");
		String confirmation = request.getParameter("confirmPassword");

		Club club = new Club();
		verifyClubId(clubName, club);
		verifyClubPassowrd(password, confirmation, club);

		if (err.isEmpty()) {

			// Hash the password
			String hashedPassword;
			try {
				hashedPassword = Hashing.generateStorngPasswordHash(password);
				club.setPassword(hashedPassword);
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidKeySpecException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			club = this.daoClubImp.add(club);
		}

		return club;

	}

	public HashMap<String, String> getErr() {
		return err;
	}

	public void setErr(HashMap<String, String> err) {
		this.err = err;
	}

	// les fonctions du virification
	public void verifyClubId(String clubID, Club club) {

		club.setClubId(clubID);

		try {
			if (clubID.isEmpty())
				throw new Exception("Please insert a valid ID");
		} catch (Exception e) {

			err.put("errIdClub", e.getMessage());
		}
	}

	public void verifyClubPassowrd(String password1, String password2, Club club) {
		// Setting student password
		club.setPassword(password1);

		try {
			if (password1.isEmpty() && password2.isEmpty())
				throw new Exception("Please insert a valid password");
			else if (!password1.equals(password2))
				throw new Exception("Passwords do not match");
		} catch (Exception e) {
			// TODO: handle exception
			err.put("errPassword", e.getMessage());
		}
	}
}