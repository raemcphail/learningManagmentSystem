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
	ObjectInputStream in = null;
	ObjectOutputStream out = null;
	LoginHandler loginhandler;
	public CreateCourseHandler createHandler;
	public MyCourseHandler getCourseHandler;
	public StudentHandler studentHandler;
	public StudentEnrollmentHandler enrollHandler;
	public AssignmentHandler assignmentHandler;
	public EmailHandler emailHandler;
	public SubmissionHandler submissionHandler;
	
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
				out = new ObjectOutputStream(aSocket.getOutputStream());
				in = new ObjectInputStream(aSocket.getInputStream()); 
				pool.execute(this);
				
			}
	}
	
	@Override
	public void run(){
		try {
				loginhandler = new LoginHandler(out, in);
				User user = new User();
				user = loginhandler.runHandler(user);	//run to start
				createHandler = new CreateCourseHandler(in, out);
				getCourseHandler = new MyCourseHandler(out, in, user);
				getCourseHandler.runHandler();	//once the user logs in, update mycourses with courses
				
				studentHandler = new StudentHandler(out, in);
				enrollHandler = new StudentEnrollmentHandler(out, in);
				assignmentHandler = new AssignmentHandler(in, out);
				emailHandler = new EmailHandler(user, in, out);
				submissionHandler = new SubmissionHandler(in, out);

				while (true)
				{
					String opCode = (String)in.readObject();
					if (opCode.equals("create"))
					{
						createHandler.runHandler();
					}
					else if (opCode.equals("checkCourses"))
					{
						getCourseHandler.runHandler();
					}
					else if (opCode.equals("updateCourse"))
					{
						getCourseHandler.toggleActive();
					}
					else if (opCode.equals("student"))
					{
						studentHandler.runHandler();
					}
					else if (opCode.equals("selected"))
					{
						enrollHandler.runHandler();
					}
					else if(opCode.equals("assignment"))
					{
						assignmentHandler.addHandler();
					}
					else if (opCode.equals("getAssignment"))
					{
						assignmentHandler.updateList();
					}
					else if (opCode.equals("getActiveAssignment"))
					{
						assignmentHandler.updateActiveList();
					}
					else if (opCode.equals("updateAssign"))
					{
						assignmentHandler.updateActive();
					}
					else if (opCode.equals("downloadAssign"))
					{
						assignmentHandler.downloadAssign();
					}
					else if (opCode.equals("sendEmail"))
					{
						emailHandler.runHandler();
					}
					else if (opCode.equals("uploadSub"))
					{
						submissionHandler.uploadSub();
					}
					else if (opCode.equals("getSubs"))
					{
						submissionHandler.getSubs();
					}

					else if (opCode.equals("downloadSub"))
					{
						submissionHandler.downloadSub();
					}
					else if (opCode.equals("sendEnrolled"))
					{
						enrollHandler.getEnrolledStudents();
					}
				}
				
				
			}
			catch (ClassNotFoundException | IOException e) {
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
