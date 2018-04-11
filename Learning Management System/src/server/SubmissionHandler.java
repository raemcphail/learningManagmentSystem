package server;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import dbManagers.AssignmentManager;
import dbManagers.CourseManager;
import dbManagers.SubmissionManager;

public class SubmissionHandler 
{
	ObjectInputStream in = null;
	ObjectOutputStream out = null;
		SubmissionHandler(ObjectInputStream in, ObjectOutputStream out)
		{
			this.in = in;
			this.out = out;
		}
	
	public void uploadSub()
	{
		try
		{
			String assignName = (String)in.readObject();
			//System.out.println(title);
			Course course = (Course)in.readObject();
			//System.out.println(course.name);
			int studentID = (Integer)in.readObject();
			//System.out.println(studentID);
			AssignmentManager a = new AssignmentManager();
			CourseManager c = new CourseManager();
			int courseID = c.findCourseID(course.name);
			int assignID = a.GETAssignID(assignName, courseID);
			SubmissionManager s = new SubmissionManager();
			String ex = (String)in.readObject();
			String title = (String)in.readObject();
			s.addItem(assignID, title, studentID);
			int subID = s.recentID();
			String path = recieveFile(Integer.toString(subID), ex);
			s.addPath(subID, path);
		}catch(IOException e)
		{
			e.printStackTrace();
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}
	
	public void downloadSub()
	{
		try
		{
			int studentID = Integer.parseInt((String)in.readObject());
			String date = (String)in.readObject();
			SubmissionManager s = new SubmissionManager();
			String path = s.getPath(studentID, date);
			sendFile(path);
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
	
	public void sendFile(String path)
	{
		File selectedFile = new File(path);
		long length = selectedFile.length();
		byte[] content = new byte[(int) length];
		try
		{
			FileInputStream fis = new FileInputStream(selectedFile);
			BufferedInputStream bos = new BufferedInputStream(fis);
			bos.read(content, 0, (int)length);
			out.writeObject(content);
			out.flush();
		}catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public void getSubs()
	{
		try
		{
			String assignName = (String)in.readObject();
			Course course = (Course)in.readObject();
			AssignmentManager a = new AssignmentManager();
			CourseManager c = new CourseManager();
			int courseID = c.findCourseID(course.name);
			int assignID = a.GETAssignID(assignName, courseID);
			SubmissionManager s = new SubmissionManager();
			ArrayList <Submissions> submissions = s.getSubmissions(assignID);
			out.writeObject(submissions);
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
		String STORAGEPATH = "C:\\" + File.separator + "Users\\" + File.separator + "raemc\\" + File.separator + "Desktop\\" + File.separator + "lmsSubmissions\\" + File.separator;
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
