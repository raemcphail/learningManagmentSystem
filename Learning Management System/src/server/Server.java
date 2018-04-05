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
public class Server implements Runnable{
	private ServerSocket serverSocket;
	private Socket aSocket;
	ExecutorService pool;
	BufferedReader in;
	PrintWriter out;
	LoginHandler loginhandler;
	public CreateCourseHandler createHandler;
	
	
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
				aSocket = serverSocket.accept();	//once a client connects
				pool.execute(this);
				
			}
	}
	
	@Override
	public void run(){
		// TODO Auto-generated method stub
		try {
				loginhandler = new LoginHandler(aSocket);
				User user = new User();
				loginhandler.runHandler(user);
				
				createHandler = new CreateCourseHandler(aSocket);
				createHandler.runHandler();
			}
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
