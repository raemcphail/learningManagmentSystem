package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
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
	/**
	 * User to access courses with their id
	 */
	User user;
	/**
	 * object I/O to send courses to the GUI
	 */
	ObjectOutputStream out = null;
	ObjectInputStream in = null;
	
		public MyCourseHandler(ObjectOutputStream out, ObjectInputStream in, User user) throws IOException
		{
			this.out = out;
			this.in = in;
			this.user = user;
		}
		
		public void runHandler()
		{
			try {
				System.out.println("myCoursesHandler running");
				CourseManager courseDB = new CourseManager();
				ArrayList<Course> courses = courseDB.getUserCourses(user.getID());
				System.out.println(courses.get(2).name);
				out.writeObject(courses); //send the courses
			
			} catch (Exception e)
			{
				e.printStackTrace();
				System.err.println("error in MyCoursesrunHandler");
			}
		}
	
}
