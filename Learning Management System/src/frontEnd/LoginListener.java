package frontEnd;
import java.awt.event.*;
import java.awt.event.*;
import javax.swing.*;

import server.User;

import java.awt.event.ActionEvent;
import java.io.*;
import java.net.Socket;
import java.util.*;

/**
 * Listener handles when the login button is pressed
 * @author Louis, Raemc
 * @version 1.1
 * @since April 9, 2018
 */
public class LoginListener implements ActionListener
{
	private LoginFrame login;
	ObjectInputStream in = null;
	ObjectOutputStream out = null;
	User user;

	public LoginListener (LoginFrame l, ObjectOutputStream out, ObjectInputStream in)
	{
		login = l;
		this.out = out;
		this.in = in;

	}
	/**
	 * checks the password typed to the value in the database, and determine if they match
	 * if they do: dispose frame and pass user to client to initialize dashboard frame
	 * otherwise display invalid message and do nothing
	 */
	public void actionPerformed(ActionEvent e)
	{
		try
		{
		char [] input = login.getpassword().getPassword(); 
		//sends LoginHandler the username over the socket to find in database then LoginHandler returns the expected password  
		String username = login.getUserName().getText();
		System.out.println(input);
		if(username.length() != 8 || input.toString().length() >20)
		{
			login.enableErrorMessage();
			return;
		}
		out.writeObject(username);
		System.out.println(username);
		out.writeObject(input);

		String result = (String)in.readObject();	//find out if the password's matched or not
		System.out.println(result);
		if(result.equals("success"))
		{
			//read user object
			try {
				user = (User)in.readObject();
				System.out.println(user.getEmail());
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			}
			System.out.println("Password is correct");
			login.dispose();
		}
		else
		{
			login.enableErrorMessage();
			System.out.println("The password is wrong");
		}
		
		}catch(ClassNotFoundException | IOException i)
		{
			i.printStackTrace();
		}
	}
	public User getUser()
	{
		return user;
	}
}
