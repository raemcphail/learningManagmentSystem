package frontEnd;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

import server.Course;

/**
 * listener to select the ContentPanel that displays the courses(or lack there of), or the 
 * create courses, **to add course catalog later
 * @author louis
 *
 */
public class MiddleBarListener implements ActionListener {
	DashboardFrame theFrame;
	Socket aSocket;
	ObjectInputStream in = null;
	BufferedReader socketIn;
	PrintWriter socketOut;
	MiddleBarListener(DashboardFrame theFrame, Socket aSocket, ObjectInputStream in)
	{
		this.theFrame = theFrame;
		this.aSocket = aSocket;
		this.in = in;
		try {
			in = new ObjectInputStream(aSocket.getInputStream());
			socketIn = new BufferedReader(new InputStreamReader(aSocket.getInputStream()));
			socketOut = new PrintWriter(aSocket.getOutputStream(),true);
		}	catch (IOException e) {
			e.printStackTrace();
		}
	}
	@SuppressWarnings("unchecked")
	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == theFrame.middleBar.btnMyCourses)
		{
			try {
				theFrame.cardLayout.show(theFrame.content, "courses");
				socketOut.println("checkCourses");	//signal to MyCourseHandler
				ArrayList<Course> courses = (ArrayList<Course>)in.readObject();
				System.out.println(courses.get(2).name);
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
		/*	to add course catalog here****
		if (e.getSource() == "MyCourses")
		{
			theFrame.cardLayout.show(theFrame.content, "courses");
		}*/
		
	}

}
