package frontEnd;
import javax.swing.*;

import server.Course;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.awt.event.ActionEvent;
import java.awt.Component;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Font;
/**
 * Panel which is on the CourseView Panel and displays options for the user,
 * such as assignments, dropbox, and Student.
 * @author raemc
 *
 */
public class SideViewPanel extends JPanel
{
	JButton btnStudent;
	JButton btnGrades;
	JButton btnDropbox;
	JButton btnAssignments;
	JButton btnBack;
	Course course;
	CourseViewPanel panel;
	ObjectOutputStream out = null;
	ObjectInputStream in = null;
	
	public SideViewPanel (CourseViewPanel p, ObjectOutputStream out, ObjectInputStream in, Course course)
	{
		panel = p;
		this.course = course;
		this.out = out;
		this.in = in;
		setBackground(new Color(0, 153, 153));
		setForeground(Color.CYAN);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		btnBack = new JButton("back");
		add(btnBack);
		
		Component verticalStrut = Box.createVerticalStrut(15);
		add(verticalStrut);
		
		JLabel courseName = new JLabel(course.name);
		courseName.setFont(new Font("Arial Black", Font.BOLD, 20));
		courseName.setForeground(new Color(255, 255, 255));
		add(courseName);
		
		Component verticalStrut_1 = Box.createVerticalStrut(20);
		verticalStrut_1.setBackground(new Color(51, 153, 153));
		add(verticalStrut_1);
		
		btnAssignments = new JButton("assignments");//	AssignmentListener(CourseViewPanel p, ObjectOutputStream out, ObjectInputStream in, Course c)

		btnAssignments.addActionListener(new AssignmentListener(panel, out, in, course));
		add(btnAssignments);
		
		Component verticalStrut_2 = Box.createVerticalStrut(20);
		add(verticalStrut_2);
		
		btnDropbox = new JButton("dropbox");
		add(btnDropbox);
		
		Component verticalStrut_3 = Box.createVerticalStrut(20);
		add(verticalStrut_3);
		
		btnGrades = new JButton("grades");
		add(btnGrades);
		
		Component verticalStrut_4 = Box.createVerticalStrut(20);
		add(verticalStrut_4);
		
		btnStudent = new JButton("student");
		add(btnStudent);
		
	
	}
}
