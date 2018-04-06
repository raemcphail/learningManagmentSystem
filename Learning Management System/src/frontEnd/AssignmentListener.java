package frontEnd;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JFileChooser;

import server.Course;

public class AssignmentListener implements ActionListener
{
	ObjectOutputStream out = null;
	ObjectInputStream in = null;
	AssignmentPanel panel;
	Course course;
	
	AssignmentListener(AssignmentPanel p, ObjectOutputStream out, ObjectInputStream in, Course c)
	{
		panel = p;
		this.in = in;
		this.out = out;
		course = c;
	}
	
	public void actionPerformed(ActionEvent e) 
	{

		if (e.getSource() == panel.btnAdd)
		{
			System.out.println("add button pushed");
			try
			{	
				out.writeObject(new String("assignment"));
				sendFile();
				out.writeObject(course);
				
			}
				//out.writeObject("checkCourses");	//signal to MyCourseHandler
				//ArrayList<Course> courses = (ArrayList<Course>)in.readObject();
//				if (courses.size() != 0)
//				{
//					theFrame.myCourses.remove(theFrame.myCourses.emptyCourseMessage);
//				}
				
				
			 catch ( IOException e1) {
				e1.printStackTrace();
				System.err.println("error catching course in MiddleBarListener");
			}
		}
	}
	
	public void sendFile()
	{
		System.out.println("Sending file");
		JFileChooser fileBrowser = new JFileChooser();
		File selectedFile = null; 
			if(fileBrowser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
			{
				selectedFile = fileBrowser.getSelectedFile();
				String filename = selectedFile.getName();
				String ex = filename.substring(filename.length() -4, filename.length());//gets the extention from file name
				String name = filename.substring(0, filename.length()-4);//gets the extention from file name
				System.out.println(name);
				if(!ex.equals(".txt") && !ex.equals(".pdf"))
				{
					//DO something
				}
				try 
				{
				out.writeObject(ex);
				out.writeObject(name);
				}
				catch(IOException e)
				{
					e.getStackTrace();
				}
				long length = selectedFile.length();
				byte[] content = new byte [(int)length];
				try 
				{
					FileInputStream fis = new FileInputStream (selectedFile);
					BufferedInputStream bos = new BufferedInputStream (fis);
					bos.read(content, 0, (int)length);
					
					out.writeObject(content);
					out.flush();
					System.out.println("File sent");
				}catch(FileNotFoundException e)
				{
					e.printStackTrace();
				}
				catch(IOException e)
				{
					e.printStackTrace();
				}
			}	
	}
}//end of class
	

