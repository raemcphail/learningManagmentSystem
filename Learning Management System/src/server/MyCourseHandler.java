package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

import dbManagers.CourseManager;

/**
 * handler continuously checks for new courses and fetches them from database to display on GUI,
 * by sending course names through sockets to the MyCoursesPanel.
 * @author louis
 *
 */
public class MyCourseHandler {
	Socket aSocket;
	BufferedReader in;
	PrintWriter out;
	/**
	 * User to access courses with their id
	 */
	User user;
	/**
	 * object I/O to send courses to the GUI
	 */
	ObjectOutputStream outObj = null;

		public MyCourseHandler(Socket aSocket, User user) throws IOException
		{
			this.aSocket = aSocket;
			in = new BufferedReader(new InputStreamReader(this.aSocket.getInputStream()));
			out = new PrintWriter(this.aSocket.getOutputStream(),true);
			this.user = user;
			outObj = new ObjectOutputStream(aSocket.getOutputStream());
		}
		
		public void runHandler()
		{
			try {
				System.out.println("myCoursesHandler running");
				CourseManager courseDB = new CourseManager();
				ArrayList<Course> courses = courseDB.getUserCourses(user.getID());
				System.out.println(courses.get(2).name);
				outObj.flush();
				outObj.writeObject(courses); //send the courses
				outObj.flush();
			
			} catch (Exception e)
			{
				e.printStackTrace();
				System.err.println("error in MyCoursesrunHandler");
			}
		}
	
}
