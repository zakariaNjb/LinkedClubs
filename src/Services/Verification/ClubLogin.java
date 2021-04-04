package Services.Verification;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import DAO.DaoClub;
import Services.Entities.Club;

public class ClubLogin {

	// Variables
		private HashMap<String, String> err = new HashMap<String, String>();
		DaoClub daoClubImp;

		// Constructors
		public ClubLogin() {
		}

		public ClubLogin(DaoClub daoClubImp) {
			this.daoClubImp = daoClubImp;
		}

		// Getters && setters
		public HashMap<String, String> getErr() {
			return err;
		}

		public void setErr(HashMap<String, String> err) {
			this.err = err;
		}

		// Verify login function
		public Club verifyLogin(HttpServletRequest request) {
			// Getting params
			String clubId = (String) request.getParameter("clubId");
			String password = (String) request.getParameter("password");

			// calling verification functions
			Club club = new Club();
			verifyClubId(clubId, club);
			verifyPassword(password);

			// Looking for the existing Student
			if (err.isEmpty()) {
				Club clb = this.daoClubImp.find(clubId);
				
				// I need decrept the std password and compare it with student.password
				if (clb != null) {
					try {
						if (!clb.getClubId().equals(clubId) || !Hashing.validatePassword(password, clb.getPassword()))
							err.put("errLogin", "Login has failed");
						else {
							return clb;
						}
					} catch (NoSuchAlgorithmException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InvalidKeySpecException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else
					err.put("errLogin", "Login has failed");

			}

			return club;
		}

		// Functions
		public void verifyClubId(String clubId, Club club) {
			// Setting student CNES
			club.setClubName(clubId);

			try {
				if (clubId.isEmpty()) {
					throw new Exception("Please insert a valid CNE");
				}
			} catch (Exception e) {
				// TODO: handle exception
				err.put("errCNE", e.getMessage());
			}
		}

		public void verifyPassword(String password) {
			try {
				if (password.isEmpty())
					throw new Exception("Please make to insert a valid password");
			} catch (Exception e) {
				// TODO: handle exception
				err.put("errPassword", e.getMessage());
			}
		}
}
