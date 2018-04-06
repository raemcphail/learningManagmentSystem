package server;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import dbManagers.AssignmentManager;
import dbManagers.CourseManager;

/**
 * handler responsible for assignment/database related actions, including:
 * adding an assignment to database,
 * @author louis
 *
 */
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
			String title = (String)in.readObject();
			Course course = (Course)in.readObject();
			AssignmentManager assignDB = new AssignmentManager();
			CourseManager cm = new CourseManager();
			int courseID = cm.findCourseID(course.name);
			
			int AssignID = assignDB.GETAssignID(title, courseID);
			boolean active = assignDB.isActive(AssignID);
			System.out.println("the assign ID: " + AssignID);
			System.out.println(title);
			 if (active)
			 {
				int choice = JOptionPane.showConfirmDialog(null, "This assignment is ACTIVE.\n Do you want to deactivate it?", "deactivate", JOptionPane.YES_NO_OPTION);
				if (choice == JOptionPane.YES_OPTION)
				{
					assignDB.changeActive(0, AssignID);
				}
				else
				{
					return;
				}
				
			 }
			 else	//student is NOT enrolled
			 {
				int choice = JOptionPane.showConfirmDialog(null, "This assignment is NOT ACTIVE.\n Do you want to activate it?", "activate", JOptionPane.YES_NO_OPTION);
				if (choice == JOptionPane.YES_OPTION)
				{
					assignDB.changeActive(1, AssignID);
				}
				else
				{
					return;
				}
			 }
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
