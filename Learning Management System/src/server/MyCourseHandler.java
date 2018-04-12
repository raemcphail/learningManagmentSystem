package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;

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
			boolean courseVal = (Boolean)in.readObject();
			CourseManager courseDB = new CourseManager();
			int courseID = courseDB.findCourseID(course.name);
			if (courseVal)	//if the course was not active, but has been toggled true
			{
				courseDB.changeActive(1, courseID);
				System.out.println(courseDB.findCourseActive(courseID));
			}
			else
			{
				courseDB.changeActive(0, courseID);
			}
		}
		
		public void updateCourseState()
		{
			try {
				Course course = (Course)in.readObject();
				CourseManager courseDB = new CourseManager();
				int courseID = courseDB.findCourseID(course.name);
				boolean courseState = courseDB.findCourseActive(courseID);
				//System.out.println(courseState);
				out.writeObject(courseState);
				}	
			catch (ClassNotFoundException | IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		}
	
}
