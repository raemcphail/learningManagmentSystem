package frontEnd;
import java.io.*;
import java.net.Socket;


public class Client 
{
	private Socket aSocket;
	private PrintWriter socketOut;
	private BufferedReader stdin, socketIn;
	
	
	public Client(String servername, int portnumber)
	{
		try 
		{
			aSocket = new Socket(servername, portnumber);
			stdin = new BufferedReader(new InputStreamReader(System.in));
			socketIn = new BufferedReader(new InputStreamReader(aSocket.getInputStream()));
			socketOut = new PrintWriter(aSocket.getOutputStream(),true);
			
			
		}catch(IOException e) {
			System.out.println("Client error");
		}
		
	}
	
	public void runClient()
	{
		System.out.println("run Client run");
		String line = "";
		String response = "";
		int i = 0;
		while(i<5)
		{
			try
			{
				System.out.println("Please enter word");
				line = stdin.readLine();
				System.out.println(line);
				socketOut.println(line);
				response = socketIn.readLine();
				System.out.println(response);
				
			}catch(IOException e)
			{
				System.out.println("Run client error");
			}
			i++;
		}
		try
		{
			stdin.close();
			socketIn.close();
			socketOut.close();
		}catch(IOException e)
		{
			System.out.println("The the sockets won't close");
		}
		
	}
	
	public static void main(String [] args)throws ClassNotFoundException
	{
		Client client = new Client("localhost", 9090);
		System.out.println("Client is running");
		client.runClient();
	}

}
