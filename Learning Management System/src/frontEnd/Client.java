package frontEnd;
import java.io.*;
import java.net.Socket;

import server.User;

/**
 * Client is the user to connect, and the starting point for the application,
 * NOTE: server must be running for client to connect too.
 * @author Louis, Raemc
 * @version 1.1
 * @since April 9, 2018
 */
public class Client 
{
	/**
	 * socket object to communicate to server
	 */
	private Socket aSocket;
	/**
	 * login frame to display upon connection
	 */
	private LoginFrame login;
	/**
	 * Object IO used to send objects across socket
	 */
	ObjectInputStream in = null;
	/**
	 * Object IO used to send objects across socket
	 */
	ObjectOutputStream out = null;
	/**
	 * the user that logs in
	 */
	User user;
	
	
	public Client(String servername, int portnumber)
	{
		try 
		{
			aSocket = new Socket(servername, portnumber);
			login = new LoginFrame();
			out = new ObjectOutputStream(aSocket.getOutputStream());
			in = new ObjectInputStream(aSocket.getInputStream());
			
		}catch(IOException e) {
			System.out.println("Client error");
		}
		
	}
	/**
	 * the central method that runs the user program
	 */
	public void runClient()
	{
		login.setVisible(true);
		LoginListener listener = new LoginListener(login, out, in);
		login.getbtnLogin().addActionListener(listener);
		while (true)	//wait until a user has been successfully added
		{
			if (listener.getUser() != null)
			{
				user = listener.getUser();
				break;
			}
		}
		DashboardFrame Dashboard = new DashboardFrame(user, out, in);
		Dashboard.setVisible(true);
		}
	
	public static void main(String [] args) throws ClassNotFoundException
	{
		Client client = new Client("localhost", 9090);
		System.out.println("Client is running");
		client.runClient();
	}

}
