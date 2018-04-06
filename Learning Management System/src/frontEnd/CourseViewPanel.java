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
	SideViewPanel svpanel;
	CardLayout c;
	JPanel selection = new JPanel();
	Course course;
	ObjectInputStream in = null;
	ObjectOutputStream out = null;
	public CourseViewPanel(Course course, ObjectInputStream in, ObjectOutputStream out) 
	{
		this.in = in;
		this.out = out;
		this.course = course;
		setLayout(new BorderLayout(0, 0));
		svpanel = new SideViewPanel(course);
		add("West", svpanel);
		

		studentpanel = new StudentPanel(course, in, out);
		assignmentpanel = new AssignmentPanel(in, out, course);
		
		c = new CardLayout();
		selection.setLayout(c);
		selection.add(assignmentpanel,"assignment");
		selection.add(studentpanel,"student");
		
		c.show(selection, "student");
		
		svpanel.btnStudent.addActionListener(new SideViewListener(svpanel, c, selection));
		svpanel.btnAssignments.addActionListener(new SideViewListener(svpanel, c, selection));
		
		add("Center", selection);
	}

}
