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
/**
 * Listener for when you press the create button in the CreateCoursePanel
 * @author Louis, Raemc
 * @version 1.1
 * @since April 9, 2018
 */
public class CreateCourseListener implements ActionListener {
	/**
	 * the main frame
	 */
	DashboardFrame theFrame;
	/**
	 * the user
	 */
	User user;
	/**
	 * Object IO used to send objects across socket
	 */
	ObjectOutputStream out = null;
	/**
	 * Object IO used to send objects across socket
	 */
	ObjectInputStream in = null;
	CreateCourseListener(DashboardFrame theFrame, User user, ObjectOutputStream out, ObjectInputStream in)
	{
		this.theFrame = theFrame;
		this.user = user;
		this.out = out;
		this.in = in;
	}
	/**
	 *  if there is information in coursename and coursenumber, this action calls 
	 *  the handler to add that course to the database, and creates the courseview panel
	 *  with that unique course
	 * 	@Override
	 */
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
				JButton aCourse = new JButton(newCourse.toString());
				theFrame.myCourses.add(aCourse);
				aCourse.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						CourseViewPanel courseView = new CourseViewPanel(theFrame, newCourse, in, out);

						theFrame.content.add((courseView), "theCourse");
						theFrame.cardLayout.show(theFrame.content, "theCourse");
					}
				});
			}
		} catch (ClassNotFoundException | IOException e1) {
			e1.printStackTrace();
		}
		}
		
	}

}
