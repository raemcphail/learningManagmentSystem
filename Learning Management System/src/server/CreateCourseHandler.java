package server;

import java.io.*;
import java.net.Socket;

import dbManagers.CourseManager;

/**
 * handler responsible for course/database related actions
 * @author louis rae
 * @version 1.0
 * @since April 11, 2018
 */
public class CreateCourseHandler {
	/**
	 * objectinputstream to receive objects
	 */
	ObjectInputStream in = null;
	/**
	 * objectoutputstream to send objects
	 */
	ObjectOutputStream out = null;
	
	CreateCourseHandler(ObjectInputStream in, ObjectOutputStream out)
	{
		this.in = in;
		this.out = out;
	}
	
	/**
	 * method that updates database when a course is added
	 */
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
					Course course = new Course(name, false, Prof_ID);
					out.writeObject(course);
				}
				
			}
			else
			{
				out.writeObject(new Course("duplicate", false, -1));
			}
			
			
			
		} catch (ClassNotFoundException | IOException e)
		{
			e.printStackTrace();
			System.err.println("exception in createCourse runHandler");
		}
	}
	
}
