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
		//char [] correct = { 'p', 'a', 's', 's', 'w', 'o', 'r', 'd' }; 
		System.out.println("login was pressed");
		String username = login.getUserName().getText();
		socketOut.println(username);
		System.out.println(username);
		username = socketIn.readLine();
		System.out.println(username);
		}catch(IOException i)
		{
			System.out.println("IOException in loginlistener");
		}
		
//		if(Arrays.equals(input, correct))
//		{
//			System.out.println("Password is correct");
//		}
//		else
//		{
//			System.out.println("The password is wrong");
//		}
	}
}
