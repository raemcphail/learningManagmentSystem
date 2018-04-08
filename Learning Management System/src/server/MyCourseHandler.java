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
import dbManagers.EnrollmentManager;

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
	
		public MyCourseHandler(ObjectOutputStream out, ObjectInputStream in, User user)
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
				ArrayList<Course> courses;
				//gets the courses for mycourses
				//prof has all the courses they created
				//student has all course they are enrolled in
				if(user.getType() == 'P')
				{	
					courses = courseDB.getUserCourses(user.getID());
				}
				else
				{
					EnrollmentManager enrollmentDB = new EnrollmentManager();
					ArrayList<Integer> courseid = enrollmentDB.getCourseID(user.getID());
					courses = courseDB.getUserCourses(courseid);
				}
				out.writeObject(courses); //send the courses
			
			} catch (Exception e)
			{
				e.printStackTrace();
				System.err.println("error in MyCoursesrunHandler");
			}
		}
		/**
		 * modifies the by changing the course to be active or not
		 * @throws IOException 
		 * @throws ClassNotFoundException 
		 */
		public void toggleActive() throws ClassNotFoundException, IOException
		{
			Course course = (Course)in.readObject();
			CourseManager courseDB = new CourseManager();
			int courseID = courseDB.findCourseID(course.name);
			if (course.getActive())	//if the course is not active
			{
				courseDB.changeActive(1, courseID);
			}
			else
			{
				courseDB.changeActive(0, courseID);
			}
		}
	
}
