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
 * @author raemc
 *
 */
public class CourseViewPanel extends JPanel
{
	AssignmentPanel assignmentpanel;
	StudentPanel studentpanel;
	DropboxPanel dropboxpanel;
	SideViewPanel svpanel;
	CardLayout c;
	JPanel selection = new JPanel();
	Course course;
	ObjectInputStream in = null;
	ObjectOutputStream out = null;
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
		
		
		c = new CardLayout();
		selection.setLayout(c);
		selection.add(assignmentpanel,"assignment");
		selection.add(studentpanel,"student");
		selection.add(dropboxpanel, "dropbox");
		
		c.show(selection, "student");
		
		svpanel.btnStudent.addActionListener(new SideViewListener(this, c, selection));
		svpanel.btnAssignments.addActionListener(new SideViewListener(this, c, selection));
		svpanel.btnDropbox.addActionListener(new SideViewListener(this, c, selection));

		svpanel.btnBack.addActionListener(new AssignmentListener(this, out, in, course, theFrame));
		
		add("Center", selection);
	}

}
