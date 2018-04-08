package server;

import java.io.*;
import java.net.Socket;

import dbManagers.CourseManager;

public class CreateCourseHandler {
ObjectInputStream in = null;
ObjectOutputStream out = null;
	CreateCourseHandler(ObjectInputStream in, ObjectOutputStream out)
	{
		this.in = in;
		this.out = out;
	}
	
	public void runHandler()
	{
		try {
			String name = (String)in.readObject();
			String number = (String)in.readObject();
			name += number;
			int Prof_ID = (int)in.readObject();
			String valid = (String)in.readObject();
			CourseManager courseDB = new CourseManager();
			if (courseDB.findCourseName(name) == null)	//check if that course has already been added
			{
				if (valid.equals("active"))
				{
					courseDB.addItem(Prof_ID, name, true);//add the course to db	
					Course course = new Course(name, true, Prof_ID);
					out.writeObject(course);
				}
				else
				{
					courseDB.addItem(Prof_ID, name, false);				
					Course course = new Course(name, true, Prof_ID);
					out.writeObject(course);
				}
				System.out.println("Course added");
				
			}
			else
			{
				out.writeObject(new Course("duplicate", false, -1));
				System.out.println("Duplicate course");
			}
			
			
			
		} catch (ClassNotFoundException | IOException e)
		{
			e.printStackTrace();
			System.err.println("exception in createCourse runHandler");
		}
	}
	/**
	 * method to add the course that was added to DB to the myCourses tab
	 */
	public void updateMyCourseGUI()
	{
		
	}
}
