package frontEnd;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JButton;

import server.Course;


/**
 * listener to select the ContentPanel that displays the courses(or lack there of), or the 
 * create courses panel
 * @author Louis, Raemc
 * @version 1.1
 * @since April 9, 2018
 */
public class MiddleBarListener implements ActionListener {
	/**
	 * the main frame
	 */
	DashboardFrame theFrame;
	/**
	 * Object IO used to send objects across socket
	 */
	ObjectOutputStream out = null;
	/**
	 * Object IO used to send objects across socket
	 */
	ObjectInputStream in = null;
	
	MiddleBarListener(DashboardFrame theFrame, ObjectOutputStream out, ObjectInputStream in)
	{
		this.theFrame = theFrame;
		this.in = in;
		this.out = out;

	}
	/**
	 * if the mycourses button is pressed, switches the outer cardlayout within dashboard frame to display courses,
	 * otherwise displays the creatCourse panel
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == theFrame.middleBar.btnMyCourses)
		{
			try
			{
				theFrame.middleBar.emailButton.setActionCommand("default");
				theFrame.cardLayout.show(theFrame.content, "courses");
				out.writeObject("checkCourses");	//signal to MyCourseHandler
				ArrayList<Course> courses = (ArrayList<Course>)in.readObject();
				if (courses.size() != 0)
				{
					theFrame.myCourses.remove(theFrame.myCourses.emptyCourseMessage);
				}

			} catch (ClassNotFoundException | IOException e1) {
				e1.printStackTrace();
				System.err.println("error catching course in MiddleBarListener");
			}
			
		}
		if (e.getSource() == theFrame.middleBar.btnNewButton)
		{
			theFrame.cardLayout.show(theFrame.content, "createCourses");
			System.out.println("create courses");
		}
		
	}

}
