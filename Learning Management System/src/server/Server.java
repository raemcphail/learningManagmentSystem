package server;


import java.io.*;
import java.net.Socket;
import java.sql.SQLException;
import java.net.ServerSocket;
import java.util.concurrent.Executors;

import dbManagers.Manager;

import java.util.concurrent.ExecutorService;

/**
 * A server for the program. This server waits for a client to 
 * connect then gives the user the login window, user must provide
 * a correct user name and password before the main page is displayed
 * @author louis rae
 * @version 1.0
 * @since April 11, 2018
 */
public class Server implements Runnable{
	/**
	 * the socket used for data exchange between the frontend and database handlers
	 */
	private Socket aSocket;
	/**
	 * objectinputstream to receive objects
	 */
	ObjectInputStream in = null;
	/**
	 * objectoutputstream to send objects
	 */
	ObjectOutputStream out = null;
	/**
	 * Handler used to interact with database regarding login data 
	 */
	LoginHandler loginhandler;
	/**
	 * Handler used to interact with database regarding course data 
	 */
	public CreateCourseHandler createHandler;
	/**
	 * Handler used to interact with database regarding course data 
	 */
	public MyCourseHandler getCourseHandler;
	/**
	 * Handler used to interact with database regarding student data 
	 */
	public StudentHandler studentHandler;
	/**
	 * Handler used to interact with database regarding enrollment data 
	 */
	public StudentEnrollmentHandler enrollHandler;
	/**
	 * Handler used to interact with database regarding assignment data 
	 */
	public AssignmentHandler assignmentHandler;
	/**
	 * Handler used to interact with database regarding email data 
	 */
	public EmailHandler emailHandler;
	/**
	 * Handler used to interact with database regarding submission data 
	 */
	public SubmissionHandler submissionHandler;
	/**
	 * Handler used to interact with database regarding grade data 
	 */
	public GradesHandler gradeHandler;
	
	
	/**
	 * method that creates thread pool and connects to clients 
	 */
	public static void runServer() throws IOException
	{
		ServerSocket serverSocket = null;
		ExecutorService pool = Executors.newCachedThreadPool();
		try{
			serverSocket = new ServerSocket(9090);

			while(true)
			{			
				Server s = new Server();
				s.aSocket = serverSocket.accept();	//once a client connects
				s.out = new ObjectOutputStream(s.aSocket.getOutputStream());
				s.in = new ObjectInputStream(s.aSocket.getInputStream()); 
				pool.execute(s);
			}
		}catch(IOException e){ 
			System.err.println("Server error");
			if (serverSocket != null) serverSocket.close();
		}
	}
	
	/**
	 * runnable method for the thread pool, has all the handlers the requires to update databse
	 * opcodes are sent as Strings through the socket so that the server knows which handler 
	 * methods to call
	 */
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
				gradeHandler = new GradesHandler(in, out);
			try
			{
				while (true)
				{
					Manager.connection.beginRequest();
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
					else if (opCode.equals("getBool"))
					{
						getCourseHandler.updateCourseState();
					}
					else if (opCode.equals("student"))
					{
						studentHandler.runHandler();
					}
					else if (opCode.equals("selected"))
					{
						enrollHandler.runHandler();
					}
					else if(opCode.equals("assignment"))//adds an assignment
					{
						assignmentHandler.addHandler();
					}
					else if (opCode.equals("getAssignment"))//fills list on assignmnet with all assignments in that course for Prof
					{
						assignmentHandler.updateList();
					}
					else if (opCode.equals("getActiveAssignment"))//fills list on assignment with all active assignments in that course Student
					{
						assignmentHandler.updateActiveList();
					}
					else if (opCode.equals("updateAssign"))//updates if an assignment is active or not
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
					else if (opCode.equals("sendEnrolled"))
					{
						enrollHandler.getEnrolledStudents();
					}
					else if (opCode.equals("downloadSub"))
					{
						submissionHandler.downloadSub();
					}
					else if (opCode.equals("sendEnrolled"))
					{
						enrollHandler.getEnrolledStudents();
					}
					else if (opCode.equals("updateGrade"))
					{
						gradeHandler.updateSubGrade();
					}
					else if (opCode.equals("showGrades"))
					{
						gradeHandler.getGrades();
					}
					Manager.connection.endRequest();
					
				}// end while
			}
			catch (SQLException s)
			{
				s.printStackTrace();
			}
				
			}
			catch (ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		try {
			in.close();
			out.close();
		} catch (Exception e) { e.printStackTrace(); }
		
	}	
	public static void main(String[] args) throws IOException
	{
		
		System.out.println("Server is now running");
		Server.runServer();
		
	}
}
