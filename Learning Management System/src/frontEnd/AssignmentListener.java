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
import java.util.Iterator;

import javax.swing.JFileChooser;

import server.Assignments;
import server.Course;
/**
 * AssignmentListener handles actions if the back button is pressed in the courseView,
 *  or if the assignment button is pressed, and facilitates sending files.
 * @author Louis, Raemc
 * @version 1.1
 * @since April 9, 2018
 */
public class AssignmentListener implements ActionListener
{
	/**
	 * Object IO used to send objects across socket
	 */
	ObjectOutputStream out = null;
	/**
	 * Object IO used to send objects across socket
	 */
	ObjectInputStream in = null;
	/**
	 * panel used to access values on the courseView
	 */
	CourseViewPanel panel;
	/**
	 * the course object that the user is currently on
	 */
	Course course;
	/**
	 * the overall dashboard frame that contains everything
	 */
	DashboardFrame theFrame;
	
	AssignmentListener(CourseViewPanel p, ObjectOutputStream out, ObjectInputStream in, Course c, DashboardFrame theFrame)
	{
		this.theFrame = theFrame;
		panel = p;
		this.in = in;
		this.out = out;
		course = c;
	}
	
	/**
	 * method that responds to any button pressed on the Side View Panel
	 */
	public void actionPerformed(ActionEvent e) 
	{
		
		if (e.getSource() == panel.svpanel.btnBack)
		{
			theFrame.cardLayout.show(theFrame.content, "courses");
			theFrame.middleBar.emailButton.setActionCommand("default");
		}

		if (e.getSource() == panel.assignmentpanel.btnAdd)
		{
			try
			{	
				out.writeObject(new String("assignment"));
				sendFile();
			}	
				
			 catch ( IOException e1) {
				e1.printStackTrace();
				System.err.println("error catching course in MiddleBarListener");
			}
		}
		else if (e.getSource() == panel.svpanel.btnAssignments)	//if the assignment button is pressed
		{
			try {
				panel.assignmentpanel.list.clear();
				if(theFrame.user.getType() == 'P')
				{	
					out.writeObject("getAssignment"); 	//send opcode for prof visible assignments
				}
				else 
				{
					out.writeObject("getActiveAssignment"); 	//send opcode for student visible assignments
				}
				out.writeObject(course.toString());	//send coursename
				ArrayList<Assignments> assignments = (ArrayList<Assignments>)in.readObject();
				if (assignments.size() != 0)
				{
					Iterator<Course> co = theFrame.user.getCourses().iterator();
					Iterator<Assignments> it = assignments.iterator();
					while (it.hasNext())
					{
						panel.assignmentpanel.list.addElement(it.next().toString());
					}	
					while (co.hasNext())	//go through courses and if the course matches, add the assignments to course
					{
						Course temp = co.next();
						if (temp.name.equals(course.name))
						{
							temp.setAssignments(assignments);
						}
						
					}
				}
				
			} catch (ClassNotFoundException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	/**
	 * method that prompts user to use file chooser (for profs uploading an assignment) to select a file 
	 * then sends that file over the socket, it also sends the name, extension and course so that
	 * database can update, and save file on the server machine properly
	 */
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
					System.out.println("Unacceptable file");
					return;
				}
				try 
				{
				out.writeObject(ex);
				out.writeObject(name);
				out.writeObject(course);
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
	

