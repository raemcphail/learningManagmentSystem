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
 * @author louis rae
 * @version 1.0
 * @since April 11, 2018
 *
 */
public class MyCourseHandler {
	/**
	 * User to access courses with their id
	 */
	User user;
	/**
	 * objectoutputstream to send objects
	 */
	ObjectOutputStream out = null;
	/**
	 * objectinputstream to receive objects
	 */
	ObjectInputStream in = null;
	
		public MyCourseHandler(ObjectOutputStream out, ObjectInputStream in, User user)
		{
			this.out = out;
			this.in = in;
			this.user = user;
		}
		
		/**
		 * method that writes an arrayList of courses the prof creates to 
		 * GUI over the socket if user is a prof or writes an arraylist of courses the student 
		 * is enrolled if the user is student
		 */
		public void runHandler()
		{
			try {
				CourseManager courseDB = new CourseManager();
				ArrayList<Course> courses;
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
		/**
		 * method that receives a course over the socket 
		 * then checks the database and sends a boolean representing
		 * if the course is active or not back through the socket 
		 */
		public void updateCourseState()
		{
			try {
				Course course = (Course)in.readObject();
				CourseManager courseDB = new CourseManager();
				int courseID = courseDB.findCourseID(course.name);
				boolean courseState = courseDB.findCourseActive(courseID);
				out.writeObject(courseState);
				}	
			catch (ClassNotFoundException | IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		}
	
}
