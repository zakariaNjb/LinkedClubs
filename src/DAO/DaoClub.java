package DAO;

import java.util.ArrayList;

import Services.Entities.Club;

public interface DaoClub {
	
	public Club add(Club club); // Function to add new club
	public Club find(String clubId); // Function to find club by Id
	public Club update(Club club); // Update club information
	public ArrayList<Club> getAllClubs(); // Get all clubs function
	public Club updateNumberOfMembers(Club club); // Function to update the number of members of a club

}
