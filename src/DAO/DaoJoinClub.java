package DAO;

import java.util.ArrayList;

import Services.Entities.Club;
import Services.Entities.ClubsMembers;
import Services.Entities.Student;

public interface DaoJoinClub {
	
	public ClubsMembers add(ClubsMembers clubsMembers); // Add new line to clubsmembers table
	public ArrayList<ClubsMembers> readMembers(String clubId); // Get lines of table clubsmembers by clubId
	public ArrayList<ClubsMembers> readClubs(String CNE); // Get lines of table clubsmembers by CNE
	public ArrayList<Student> getMembers(ArrayList<ClubsMembers> membersCNE); // Get students from clubsmembers table
	public ArrayList<Club> getClubs(ArrayList<ClubsMembers> clubIds); // Get clubs from clubsmembers table
	public ClubsMembers delete(ClubsMembers clubsMembers); // Delete occurences from clubsmembers table
	public ClubsMembers update(ClubsMembers clubsMembers); // Update occurences from clubsmembers table
	public ArrayList<ClubsMembers> getAll(); // get all occurences from clubsmembers table

}
