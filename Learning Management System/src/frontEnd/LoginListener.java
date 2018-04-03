package frontEnd;
import java.awt.event.*;
import javax.swing.*;
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
	public LoginListener (LoginFrame l, Socket s, BufferedReader r, PrintWriter w)
	{
		login = l;
		aSocket = s;
		socketIn = r;
		socketOut = w;
	}
	public void actionPerformed(ActionEvent e)
	{
		try
		{
		char [] input = login.getpassword().getPassword(); 
		System.out.println("login was pressed");
		//sends LoginHandler the username over the socket to find in database then LoginHandler returns the expected password  
		String username = login.getUserName().getText();
		socketOut.println(username);
		System.out.println(username);
		
		String password = socketIn.readLine();
		System.out.println("Required password is " + password);
		char [] cPassword = password.toCharArray();
		
		System.out.println(username);

		if(Arrays.equals(cPassword, input))
		{
			System.out.println("Password is correct");
		}
		else
		{
			System.out.println("The password is wrong");
		}
		
		}catch(IOException i)
		{
			System.out.println("IOException in loginlistener");
		}
	}
}
