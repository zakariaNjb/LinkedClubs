package Services.Verification;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import DAO.DaoClub;
import DAO.DaoClubImp;
import Services.Entities.Club;

public class ClubSetting {

	private HashMap<String, String> err = new HashMap<String, String>();
	private DaoClubImp daoClubImp;

	// Constructors
	public ClubSetting(DaoClub daoClubImp) {
		super();
		this.daoClubImp = (DaoClubImp) daoClubImp;
	}

	public ClubSetting() {

	}

	// Getters && Setters
	public HashMap<String, String> getErr() {
		return err;
	}

	public void setErr(HashMap<String, String> err) {
		this.err = err;
	}

	public DaoClubImp getDaoClubImp() {
		return daoClubImp;
	}

	public void setDaoClubImp(DaoClubImp daoClubImp) {
		this.daoClubImp = daoClubImp;
	}

	public Club verifySettingClub(HttpServletRequest request) throws IOException, ServletException {
		// Get the current project path
		String applicationPath = request.getServletContext().getRealPath("");

		// Get the upload folder for profile images
		String clubImagesUploadDirectory = applicationPath + File.separator + "Images" + File.separator
				+ "clubProfileImages";

		// Get the upload folder for cover images
		String coverImagesUploadDirectory = applicationPath + File.separator + "Images" + File.separator
				+ "clubCoverImages";
		// Getting files

		Part clubImagePart = request.getPart("image");
		Part coverImagePart = request.getPart("cover");

		// Getting parameters;
		String ClubName = request.getParameter("ClubName");
		String Password = request.getParameter("password");
		String Confirmation = request.getParameter("confirmPassword");
		String Facebook = request.getParameter("Facebook");
		String Instagram = request.getParameter("Instagram");
		String ClubBio = request.getParameter("ClubBio");
		String ClubImage = (String) clubImagePart.getSubmittedFileName();
		String coverImage = (String) coverImagePart.getSubmittedFileName();

		Club club = new Club();
		verifyClubName(ClubName, club);
		verifyClubPassowrd(Password, Confirmation, club);
		verifyClubFb(Facebook, club);
		verifyClubInst(Instagram, club);
		verifyClubBio(ClubBio, club);
		verifyCoverImg(coverImage, club);
		verifyClubImg(ClubImage, club);

		HttpSession session = request.getSession();
		club.setClubId(((Club) session.getAttribute("club")).getClubId());

		try {
			if (err.isEmpty()) {
				// Update student in the dataBase
				if (!ClubImage.isEmpty()) {
					clubImagePart.write(clubImagesUploadDirectory + File.separator + ClubImage);
				}
				if (!coverImage.isEmpty()) {
					coverImagePart.write(coverImagesUploadDirectory + File.separator + coverImage);
				}

				try {

					String hashedPassword = Hashing.generateStorngPasswordHash(Password);
					club.setPassword(hashedPassword);
					return this.daoClubImp.update(club);

				} catch (Exception e) {
					// TODO: handle exception
				}

			} else {
				throw new Exception("Update has failed!!");
			}
		} catch (Exception e) {
			// TODO: handle exception
			err.put("errUpdate", e.getMessage());
		}

		return club;

	}

	// les fonctions du virification
	public void verifyClubName(String clubName, Club club) {

		club.setClubName(clubName);

		try {
			if (clubName.isEmpty())
				throw new Exception("Please insert a valid name");
		} catch (Exception e) {

			err.put("errName", e.getMessage());
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

	public void verifyClubFb(String clubFb, Club club) {

		club.setFacebook(clubFb);

		try {
			if (clubFb.isEmpty())
				throw new Exception("Please insert a facebook");
		} catch (Exception e) {

			err.put("errfb", e.getMessage());
		}
	}

	public void verifyClubInst(String ClubInst, Club club) {

		club.setInstagram(ClubInst);

		try {
			if (ClubInst.isEmpty())
				throw new Exception("Please insert a Instagram");
		} catch (Exception e) {

			err.put("errInst", e.getMessage());
		}
	}

	public void verifyClubBio(String ClubBio, Club club) {

		club.setClubBio(ClubBio);

		try {
			if (ClubBio.isEmpty())
				throw new Exception("Please insert a Bio");
		} catch (Exception e) {

			err.put("errBio", e.getMessage());
		}
	}

	public void verifyCoverImg(String cover, Club club) {
		// Setting student major
		club.setCoverImage(cover);
	}

	public void verifyClubImg(String image, Club club) {
		// Setting student major
		club.setImage(image);
	}

}