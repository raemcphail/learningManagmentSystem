package frontEnd;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import server.CreateCourseHandler;
import server.Server;
import server.Course;
import server.User;

public class CreateCourseListener implements ActionListener {
	DashboardFrame theFrame;
	User user;
	Socket aSocket;
	PrintWriter socketOut;

	CreateCourseListener(DashboardFrame theFrame, User user, Socket aSocket)
	{
		this.theFrame = theFrame;
		this.user = user;
		this.aSocket = aSocket;
		try {
			socketOut = new PrintWriter(aSocket.getOutputStream(),true);

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
			socketOut.println("create");
			String name = theFrame.createCourses.getName();
			socketOut.println(name);
			String number = theFrame.createCourses.getNumber();
			socketOut.println(number);
			int prof_ID = theFrame.user.getID();
			socketOut.println(prof_ID);
			if (theFrame.createCourses.rdbtnYes.isSelected())	//course is set to be active
			{
				socketOut.println("active");
			}
			else
			{
				socketOut.println("inactive");
			}
			
		}
		
	}

}
