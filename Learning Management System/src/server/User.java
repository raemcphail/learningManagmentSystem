package server;

import java.net.Socket;
import java.io.Serializable;

public class User implements Serializable {
	/**
	 * the class id for serializing
	 */
	final static long serialVersionUID = 1L;

	/**
	 * the users id
	 */
	int ID;
	/**
	 * the users password
	 */
	String Password;
	/**
	 * the users email address
	 */
	String Email;
	/**
	 * the users first name
	 */
	String firstName;
	/**
	 * the users last name
	 */
	String lastName;
	/**
	 * the user type- either S for student or P for professer
	 */
	char Type;
	/**
	 * the course[10] will go below
	 */
	
	/**
	 * the socket from the server that will communicate with the client
	 */
	Socket aSocket;
	
	Course [] courses;
	
	public User(Socket aSocket)
	{
		this.aSocket = aSocket;
	}
	
	public User (int ID, String Password, String Email,
			String firstname, String lastName, char Type)
	{
		this.ID = ID;
		this.Password = Password;
		this.Email = Email;
		this.firstName = firstname;
		this.lastName = lastName;
		this.Type = Type;
		courses = new Course [10];
	}
	
	/**
	 * setters
	 */
	public void setID(int ID)
	{
		this.ID = ID;
	}
	public void setPassword(String Password)
	{
		this.Password = Password;
	}
	public void setEmail(String Email)
	{
		this.Email = Email;
	}
	public void setfirstName(String firstName)
	{
		this.firstName = firstName;
	}
	public void setlastName(String lastName)
	{
		this.lastName = lastName;
	}
	public void setType(char Type)
	{
		this.Type = Type;
	}
	
}
