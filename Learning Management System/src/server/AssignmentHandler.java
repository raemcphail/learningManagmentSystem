package server;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import dbManagers.AssignmentManager;
import dbManagers.CourseManager;

public class AssignmentHandler 
{
	ObjectInputStream in = null;
	ObjectOutputStream out = null;
		AssignmentHandler(ObjectInputStream in, ObjectOutputStream out)
		{
			this.in = in;
			this.out = out;
		}
		
	public void addHandler()
	{
		try
		{
		//String name = (String)in.readObject();
		String ex = (String)in.readObject();
		String title = (String)in.readObject();
		Course course = (Course)in.readObject();
		CourseManager c = new CourseManager();
		AssignmentManager a = new AssignmentManager();
		int courseid = c.findCourseID(course.name);
		a.addItem(courseid, title, false);
		int assignmentid = a.recentID();
		String path = recieveFile(Integer.toString(assignmentid), ex);
		a.addPath(assignmentid, path);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		
	}
	
	public void updateList()
	{
		try {
			System.out.println("in updatelist");
			String courseName = (String)in.readObject();
			CourseManager courseDB = new CourseManager();
			int CourseID = courseDB.findCourseID(courseName);
			AssignmentManager assignDB = new AssignmentManager();
			ArrayList<Assignments> assignments = assignDB.getAssignments(CourseID);
			//send the arraylist
			out.writeObject(assignments);
			
			
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void updateActive()
	{
		try {
			String assignID = (String)in.readObject();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String recieveFile (String name, String ex)
	{
		String STORAGEPATH = "C:\\" + File.separator + "Users\\" + File.separator + "louis\\" + File.separator + "Desktop\\" + File.separator + "serverComputer\\" + File.separator;
		String NAME = name;
		String EXTENSION = ex;
		String path = STORAGEPATH + NAME + EXTENSION;
		System.out.println(path);
		System.out.println("Server is recieving file");
		try
		{
			System.out.println("recieving file");
			byte[] content = (byte[]) in.readObject();
			File newFile = new File(path);
			if(! newFile.exists())
			{
				newFile.createNewFile();
			}
			FileOutputStream writer = new FileOutputStream (newFile);
			BufferedOutputStream bos = new BufferedOutputStream(writer);
			bos.write(content);
			bos.close();
			return path;
		}catch(IOException e)
		{
			e.printStackTrace();
			return path;
		}		
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
			return path;
		}
	}
}
