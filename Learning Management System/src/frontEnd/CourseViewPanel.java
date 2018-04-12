package frontEnd;

import javax.swing.*;

import server.Course;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * CourseView Panel displays the information for when you select one of your courses
 * @author Louis, Raemc
 * @version 1.1
 * @since April 9, 2018
 */
public class CourseViewPanel extends JPanel
{
	/**
	 * the assignment panel
	 */
	AssignmentPanel assignmentpanel;
	/**
	 * the student panel
	 */
	StudentPanel studentpanel;
	/**
	 * the dropbox panel
	 */
	DropboxPanel dropboxpanel;
	/**
	 * the StudentGradePanel
	 */
	StudentGradePanel studentGrade;
	/**
	 * the sideViewPanel
	 */
	SideViewPanel svpanel;
	/**
	 * the inner card layout to switch between the above panels
	 */
	CardLayout c;
	/**
	 * the jpanel that will be displayed through the cardlayout
	 */
	JPanel selection = new JPanel();
	/**
	 * the course object that the user has clicked on
	 */
	Course course;
	/**
	 * Object IO used to send objects across socket
	 */
	ObjectInputStream in = null;
	/**
	 * Object IO used to send objects across socket
	 */
	ObjectOutputStream out = null;
	/**
	 * the main frame
	 */
	DashboardFrame theFrame;
	public CourseViewPanel(DashboardFrame theFrame, Course course, ObjectInputStream in, ObjectOutputStream out) 
	{
		this.theFrame = theFrame;
		this.in = in;
		this.out = out;
		this.course = course;
		setLayout(new BorderLayout(0, 0));
		svpanel = new SideViewPanel(this, out, in, course, theFrame);
		add("West", svpanel);
		

		studentpanel = new StudentPanel(course, in, out);
		assignmentpanel = new AssignmentPanel(this, in, out, course, theFrame);
		dropboxpanel = new DropboxPanel(this, in, out, course, theFrame);
		studentGrade = new StudentGradePanel(course, theFrame.user, theFrame);
		
		
		c = new CardLayout();
		selection.setLayout(c);
		selection.add(assignmentpanel,"assignment");
		selection.add(studentpanel,"student");
		selection.add(dropboxpanel, "dropbox");
		selection.add(studentGrade, "studentGrade");
		
		
		c.show(selection, "assignment");
		if (theFrame.user.getType() == 'P')
		{
			svpanel.btnStudent.addActionListener(new SideViewListener(this, c, selection));			
		}
		else
		{
			svpanel.btnGrades.addActionListener(new SideViewListener(this, c, selection));
		}
		svpanel.btnAssignments.addActionListener(new SideViewListener(this, c, selection));
		svpanel.btnDropbox.addActionListener(new SideViewListener(this, c, selection));
		svpanel.btnBack.addActionListener(new AssignmentListener(this, out, in, course, theFrame));
		
		add("Center", selection);
	}

}
