package frontEnd;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JButton;

import server.CreateCourseHandler;
import server.Server;
import server.Course;
import server.User;

public class CreateCourseListener implements ActionListener {
	DashboardFrame theFrame;
	User user;
	ObjectOutputStream out = null;
	ObjectInputStream in = null;
//this, user, out, in)
	CreateCourseListener(DashboardFrame theFrame, User user, ObjectOutputStream out, ObjectInputStream in)
	{
		this.theFrame = theFrame;
		this.user = user;
		this.out = out;
		this.in = in;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (theFrame.createCourses.getName().equals(""))
		{
			return;
		}
		else if (theFrame.createCourses.getNumber().equals(""))
		{
			return;
		}
		else	//there is information to be added!
		{
			//createHandler.runHandler();
		try {
			out.writeObject("create");	//send the opcode
		
			String name = theFrame.createCourses.getName();
			out.writeObject(name);
			String number = theFrame.createCourses.getNumber();
			out.writeObject(number);
			int prof_ID = theFrame.user.getID();
			out.writeObject(prof_ID);
			if (theFrame.createCourses.rdbtnYes.isSelected())	//course is set to be active
			{
				out.writeObject("active");
			}
			else
			{
				out.writeObject("inactive");
			}
			Course newCourse = (Course)in.readObject();
			if (newCourse.toString().equals("duplicate"))
			{
				//do nothing
				return;
			}
			else	//if there is a course to add
			{
				theFrame.myCourses.add(new JButton(newCourse.toString()));
				//potentially add actionlistener here
			}
		} catch (ClassNotFoundException | IOException e1) {
			e1.printStackTrace();
		}
		}
		
	}

}
