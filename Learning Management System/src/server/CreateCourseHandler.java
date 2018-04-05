package server;

import java.io.*;
import java.net.Socket;

import dbManagers.CourseManager;

public class CreateCourseHandler {
Socket aSocket;
ObjectInputStream in = null;
	
	public CreateCourseHandler(Socket aSocket) throws IOException
	{
		this.aSocket = aSocket;
	}
	
	public void runHandler()
	{
		try {
			in = new ObjectInputStream(aSocket.getInputStream());
			Course course = (Course)in.readObject();
			CourseManager courseDB = new CourseManager();
			if (courseDB.findCourseName(course.name) == null)	//check if that course has already been added
			{
				courseDB.addItem(course.prof_ID, course.name, course.active);//add the course to db		
				System.out.println("Course added");
			}
			else
			{
				System.out.println("Duplicate course");
			}
			
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.err.println("ClassNotFound");
		} catch (IOException e)
		{
			e.printStackTrace();
			System.err.println("IOException in runHandler");
		}
	}
	/**
	 * method to add the course that was added to DB to the myCourses tab
	 */
	public void updateMyCourseGUI()
	{
		
	}
}
