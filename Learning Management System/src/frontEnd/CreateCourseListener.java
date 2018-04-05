package frontEnd;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import server.CreateCourseHandler;
import server.Server;
import server.Course;
import server.User;

public class CreateCourseListener implements ActionListener {
	DashboardFrame theFrame;
	User user;
	Socket aSocket;
	ObjectOutputStream outObj = null;

	CreateCourseListener(DashboardFrame theFrame, User user, Socket aSocket)
	{
		this.theFrame = theFrame;
		this.user = user;
		this.aSocket = aSocket;
		try {
			outObj = new ObjectOutputStream(aSocket.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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
			String name = theFrame.createCourses.getName();
			String number = theFrame.createCourses.getNumber();
			boolean hit = false;
			if (theFrame.createCourses.rdbtnYes.isSelected())	//course is set to be active
			{
				hit = true;
			}
			Course course = new Course(name + number, hit, user.getID());
			try {
				outObj.writeObject(course);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}

}
