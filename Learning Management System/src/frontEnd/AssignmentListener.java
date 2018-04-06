package frontEnd;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import server.Course;

public class AssignmentListener implements ActionListener
{
	ObjectOutputStream out = null;
	ObjectInputStream in = null;
	AssignmentPanel panel;
	
	AssignmentListener(AssignmentPanel p, ObjectOutputStream out, ObjectInputStream in)
	{
		panel = p;
		this.in = in;
		this.out = out;
	}
	
	public void actionPerformed(ActionEvent e) 
	{

		if (e.getSource() == panel.btnAdd)
		{
			System.out.println("add button pushed");
			try
			{	
				out.writeObject(new String("assignment"));
				//sendFile();
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
}
	

