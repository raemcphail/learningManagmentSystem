package server;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

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
		String p = a.findPath(assignmentid);
		System.out.println("FROM database " + p);
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
	
	public String recieveFile (String name, String ex)
	{
		String STORAGEPATH = "C:\\" + File.separator + "Users\\" + File.separator + "raemc\\" + File.separator + "Desktop\\" + File.separator + "lmsServer\\" + File.separator;
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
			path.replace("r", "R");
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
