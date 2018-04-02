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
//	private ObjectInputStream input;
//	private ObjectOutputStream output;
	ExecutorService pool;
	
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
			while(true)
			{
				aSocket = serverSocket.accept();
		
			}
	}
	
	public static void main(String[] args) throws IOException
	{
		Server server = new Server(9090);
		System.out.println("Server is now running");
		server.runServer();
	}
	
	
}
