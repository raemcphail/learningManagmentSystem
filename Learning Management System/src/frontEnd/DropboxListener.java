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
 * Listener to handle when the dropbox button on the sideviewpanel is pressed.
 * @author Louis, Raemc
 * @version 1.1
 * @since April 9, 2018
 */
public class DropboxListener implements ActionListener
{
	ObjectOutputStream out = null;
	ObjectInputStream in = null;
	CourseViewPanel panel;
	Course course;
	DashboardFrame theFrame;
	
	DropboxListener(CourseViewPanel p, ObjectOutputStream out, ObjectInputStream in, Course c, DashboardFrame theFrame)
	{
		this.theFrame = theFrame;
		panel = p;
		this.in = in;
		this.out = out;
		course = c;
	}
	
	public void actionPerformed(ActionEvent e) 
	{
		
		if (e.getSource() == panel.svpanel.btnDropbox)	//if the dropbox button is pressed
		{
			try {
				System.out.println("in dropbox listener");
				panel.dropboxpanel.list.clear();
				if(theFrame.user.getType() == 'P')
				{	
					out.writeObject("getAssignment"); 	//send opcode for prof visable assignments
				}
				else 
				{
					out.writeObject("getActiveAssignment"); 	//send opcode for student visable assignments
				}
				out.writeObject(course.toString());	//send coursename
				ArrayList<Assignments> assignments = (ArrayList<Assignments>)in.readObject();
				if (assignments.size() != 0)
				{
					Iterator it = assignments.iterator();
					while (it.hasNext())
					{
						panel.dropboxpanel.list.addElement(it.next().toString());
					}	
				}
				
			} catch (ClassNotFoundException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}
	