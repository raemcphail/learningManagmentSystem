package server;

import java.io.*;
import java.net.Socket;

import dbManagers.CourseManager;

public class CreateCourseHandler {
Socket aSocket;
BufferedReader in;
PrintWriter out;
	
	public CreateCourseHandler(Socket aSocket) throws IOException
	{
		this.aSocket = aSocket;
		in = new BufferedReader(new InputStreamReader(this.aSocket.getInputStream()));
		//out = new PrintWriter(this.aSocket.getOutputStream(),true);	TO BE DELETED
	}
	
	public void runHandler()
	{
		try {
			String name = in.readLine();
			String number = in.readLine();
			name += number;
			String Prof_ID = in.readLine();
			String valid = in.readLine();
			CourseManager courseDB = new CourseManager();
			if (courseDB.findCourseName(name) == null)	//check if that course has already been added
			{
				if (valid.equals("active"))
				{
					courseDB.addItem(Prof_ID, name, true);//add the course to db		
				}
				else
				{
					courseDB.addItem(Prof_ID, name, false);							
				}
				System.out.println("Course added");
			}
			else
			{
				System.out.println("Duplicate course");
			}
			
			
			
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
