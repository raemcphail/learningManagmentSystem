package server;

import java.net.Socket;
import java.io.Serializable;

/**
 * This class represents all the data for a User
 * @author raemc
 *
 */
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
	 * the courses which the user has
	 */
	Course [] courses;
	
	public User()
	{
		
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
	/**
	 * getters
	 */
	public String getEmail()
	{
		return Email;
	}
	public char getType()
	{
		return Type;
	}
	public String getFirstname()
	{
		return firstName;
	}
	public String getLastname()
	{
		return lastName;
	}
	
}
