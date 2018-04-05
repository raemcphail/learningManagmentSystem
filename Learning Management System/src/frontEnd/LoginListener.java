package frontEnd;
import java.awt.event.*;
import java.awt.event.*;
import javax.swing.*;

import server.User;

import java.awt.event.ActionEvent;
import java.io.*;
import java.net.Socket;
import java.util.*;


public class LoginListener implements ActionListener
{
	private Socket aSocket;
	private PrintWriter socketOut;
	private BufferedReader socketIn;
	private LoginFrame login;
	ObjectInputStream in = null;
	User user;

	public LoginListener (LoginFrame l, Socket s, BufferedReader r, PrintWriter w, ObjectInputStream in)
	{
		login = l;
		aSocket = s;
		socketIn = r;
		socketOut = w;
		this.in = in;

	}
	public void actionPerformed(ActionEvent e)
	{
		try
		{
		char [] input = login.getpassword().getPassword(); 
		System.out.println("login was pressed");
		//sends LoginHandler the username over the socket to find in database then LoginHandler returns the expected password  
		String username = login.getUserName().getText();
		System.out.println(input);
		if(username.length() != 8 || input.toString().length() >20)
		{
			login.enableErrorMessage();
			return;
		}
		socketOut.println(username);
		System.out.println(username);
		socketOut.println(input);

		String result = socketIn.readLine();	//find out if the password's matched or not
		System.out.println(result);
		if(result.equals("success"))
		{
			//read user object
			try {
				user = (User)in.readObject();
				//in.close();
				System.out.println(user.getEmail());
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println("Password is correct");
			socketOut.println("QUIT");
			login.dispose();
		}
		else
		{
			login.enableErrorMessage();
			System.out.println("The password is wrong");
		}
		
		}catch(IOException i)
		{
			i.printStackTrace();
		}
	}
	public User getUser()
	{
		return user;
	}
}
