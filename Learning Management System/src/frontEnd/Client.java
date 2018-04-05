package frontEnd;
import java.io.*;
import java.net.Socket;

import server.User;


public class Client 
{
	private Socket aSocket;
	private PrintWriter socketOut;
	private BufferedReader stdin, socketIn;
	private LoginFrame login;
	ObjectInputStream in = null;
	User user;
	
	
	public Client(String servername, int portnumber)
	{
		try 
		{
			aSocket = new Socket(servername, portnumber);
			stdin = new BufferedReader(new InputStreamReader(System.in));
			socketIn = new BufferedReader(new InputStreamReader(aSocket.getInputStream()));
			socketOut = new PrintWriter(aSocket.getOutputStream(),true);
			login = new LoginFrame();
			in = new ObjectInputStream(aSocket.getInputStream());
			
		}catch(IOException e) {
			System.out.println("Client error");
		}
		
	}
	
	public void runClient()
	{
		//String line = "";
		//String response = "";
		login.setVisible(true);
		LoginListener listener = new LoginListener(login, aSocket, socketIn, socketOut, in);
		login.getbtnLogin().addActionListener(listener);
		while (true)	//wait until a user has been successfully added
		{
			if (listener.getUser() != null)
			{
				user = listener.getUser();
				break;
			}
		}
		System.out.println("Client sees: " + user.getFirstname());
		DashboardFrame Dashboard = new DashboardFrame(user, aSocket, in);
		Dashboard.setVisible(true);
		}
	
	public static void main(String [] args) throws ClassNotFoundException
	{
		Client client = new Client("localhost", 9090);
		System.out.println("Client is running");
		client.runClient();
	}

}
