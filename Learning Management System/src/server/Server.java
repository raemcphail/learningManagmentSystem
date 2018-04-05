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
	public MyCourseHandler getCourseHandler;
	public BufferedReader socketIn;
	
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
		try {
			
				loginhandler = new LoginHandler(aSocket);
				User user = new User();
				user = loginhandler.runHandler(user);	//run to start
				socketIn = new BufferedReader(new InputStreamReader(aSocket.getInputStream()));
				createHandler = new CreateCourseHandler(aSocket);
				getCourseHandler = new MyCourseHandler(aSocket, user);

				while (true)
				{
					String opCode = socketIn.readLine();
					if (opCode.equals("create"))
					{
						createHandler.runHandler();
					}
					else if (opCode.equals("checkCourses"))
					{
						getCourseHandler.runHandler();
					}
					//more to come
				}
				
				
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
