package server;

import java.net.Socket;
import java.util.Arrays;

import dbManagers.UserManager;

import java.io.*;

/**
 * handler responsible for login/database related actions
 * @author louis rae
 * @version 1.0
 * @since April 11, 2018
 */
public class LoginHandler {
	/**
	 * the user sending the email
	 */
	User user;
	/**
	 * objectoutputstream to send objects
	 */
	ObjectOutputStream out = null;
	/**
	 * objectinputstream to receive objects
	 */
	ObjectInputStream in = null;
	
	LoginHandler(ObjectOutputStream out, ObjectInputStream in)
	{
		this.out = out;
		this.in = in;
	}
	
	/**
	 * method that checks database for user with matching login information
	 * then results in "success" where user can login or 
	 * "failure where they have to return to login screen
	 */
	public User runHandler(User user) 
	{
		this.user = user;
		try
		{
			while(true)
			{
			String username = (String)in.readObject();
			char [] password = (char [])in.readObject();
			
			int userN = Integer.parseInt(username);

			dbManagers.UserManager u = new UserManager();
			String actualPassword = u.findClientPassword(userN);
			boolean match = checkPassword(password, actualPassword);
			
			if (match)
			{
				char[] type = u.findClientType(userN);
				if (type[0] == 'S')
				{
					user = new Student(userN, password.toString(), u.findClientEmail(userN),
							u.findClientFirstname(userN), u.findClientLastname(userN), 'S');

				}
				else if (type[0] == 'P')
				{
					user = new Professor(userN, password.toString(), u.findClientEmail(userN),
							u.findClientFirstname(userN), u.findClientLastname(userN), 'P');
				}
				String email = u.findClientEmail(userN);
				user.setEmail(email);
				
				String firstname = u.findClientFirstname(userN);
				user.setfirstName(firstname);
				
				String lastname = u.findClientLastname(userN);
				user.setlastName(lastname);
				
				user.setID(userN);
				user.setPassword(actualPassword);
				
				user.setType(type[0]);
				out.writeObject("success");

				out.writeObject(user);
				return user;
			}
			else
			{
				out.writeObject("failure");
			}
			
		}//end while
		}catch(ClassNotFoundException | IOException e)
		{
			e.printStackTrace();
			System.out.println("runHandler error");
		}
		return null;
		
	}
	
	/**
	 * method that recieves actual password from database and the
	 * password field from GUI then checks if they match
	 */
	 public boolean checkPassword(char [] password, String actualPassword)
	 {
		 if (actualPassword == null)
		 {
			 return false;
		 }
		 char [] AcPassword = actualPassword.toCharArray();
		 return Arrays.equals(password, AcPassword);
	 }

}
