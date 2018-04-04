package frontEnd;
import java.io.*;
import java.net.Socket;


public class Client 
{
	private Socket aSocket;
	private PrintWriter socketOut;
	private BufferedReader stdin, socketIn;
	private LoginFrame login;
	ObjectInputStream in = null;
	
	
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
		login.getbtnLogin().addActionListener(new LoginListener(login, aSocket, socketIn, socketOut, in));
			
		}
	
	public static void main(String [] args) throws ClassNotFoundException
	{
		Client client = new Client("localhost", 9090);
		System.out.println("Client is running");
		client.runClient();
	}

}
