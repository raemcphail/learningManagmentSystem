package server;

import java.net.Socket;

/**
 * This class represents all the data for a Student
 * @author raemc
 *
 */
public class Student extends User
{
	//Socket aSocket;
	
	public Student(int id, String password, String email,
					 String first, String last, char type)
	{
		super(id, password, email, first, last, type);
		//aSocket = socket;
	}
}
