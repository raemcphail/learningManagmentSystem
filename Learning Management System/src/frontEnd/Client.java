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
	ObjectOutputStream out = null;
	User user;
	
	
	public Client(String servername, int portnumber)
	{
		try 
		{
			aSocket = new Socket(servername, portnumber);
			stdin = new BufferedReader(new InputStreamReader(System.in));
			login = new LoginFrame();
			out = new ObjectOutputStream(aSocket.getOutputStream());
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
