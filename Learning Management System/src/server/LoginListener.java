package server;
import java.awt.event.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.*;

public class LoginListener implements ActionListener
{
	frontEnd.LoginFrame login;
	public LoginListener (frontEnd.LoginFrame l)
	{
		login = l;
	}
	public void actionPerformed(ActionEvent e) 
	{
		char [] input = login.getpassword().getPassword();
		char [] correct = { 'p', 'a', 's', 's', 'w', 'o', 'r', 'd' }; 
		System.out.println("login was pressed");
		String username = login.getUserName().getText();
		//String password = a.toString();
		System.out.println("Username is " + username);
		if(Arrays.equals(input, correct))
		{
			System.out.println("Password is correct");
		}
		else
		{
			System.out.println("The password is wrong");
		}
	}
}
