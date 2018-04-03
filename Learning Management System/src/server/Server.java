package server;


import java.io.*;
import java.net.Socket;
import java.net.ServerSocket;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

/**
 * A server for the program. This server waits for a client to 
 * connect then gives the user the login window, user must provide
 * a correct user name and password before the main page is displayed
 * @author raemc
 *
 */
public class Server {
	private ServerSocket serverSocket;
	private Socket aSocket;
	ExecutorService pool;
	BufferedReader in;
	PrintWriter out;
	
	public Server (int portnumber)
	{
		try
		{
			serverSocket = new ServerSocket(portnumber);
			pool = Executors.newCachedThreadPool();
			
			
		}catch(IOException e)
		{
			System.err.println("Server error");
		}
	}
	
	public void runServer() throws IOException
	{
		String line = "yo yo";
		
			while(true)
			{
				aSocket = serverSocket.accept();
				in = new BufferedReader(new InputStreamReader(aSocket.getInputStream()));
				out = new PrintWriter(aSocket.getOutputStream(),true);
				//pool.execute();
				LoginHandler loginhandler = new LoginHandler(aSocket, in, out); 
				loginhandler.runHandler();
//				while(true)
//				{
//					System.out.println(line);
//					line = in.readLine();
//					System.out.println(line);
//					out.println("Server says " + line);
//				}
		
			}
	}
	
	public static void main(String[] args) throws IOException
	{
		Server server = new Server(9090);
	
		System.out.println("Server is now running");
		server.runServer();
		server.in.close();
		server.out.close();
		
	}
	
	
}
