package server;

import java.net.Socket;

/**
 * This class represents all the data for a Student
 * @author louis rae
 * @version 1.0
 * @since April 11, 2018
 *
 */
public class Student extends User
{	
	public Student(int id, String password, String email,
					 String first, String last, char type)
	{
		super(id, password, email, first, last, type);
	}
	
	/**
	 * method that returns a string with the student name, email and ID
	 */
	@Override
	public String toString()
	{
		return firstName + " " + lastName + " " + Email + " " + ID;
	}
}
