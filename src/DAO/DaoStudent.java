package DAO;

import java.util.ArrayList;

import Services.Entities.Student;

public interface DaoStudent {
	
	public Student add(Student student); // Add new student
	public Student find(String CNE); // Find student by CNE
	public Student update(Student student); // Update student information
	public ArrayList<Student> getAll(); // Get all students
	
}
