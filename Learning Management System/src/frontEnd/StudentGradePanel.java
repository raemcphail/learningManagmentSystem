package frontEnd;

import javax.swing.JPanel;

import server.Course;
import server.User;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.DropMode;
import java.awt.Component;
import javax.swing.Box;

/**
 * the panel that is displayed when a student presses the Grade button within their course
 * @author louis
 *
 */
public class StudentGradePanel extends JPanel{
	Course course;
	User user;
	DashboardFrame theFrame;
	private JLabel firstAssignment;
	private JLabel secondAssignment;	
	private JTextField firstGrade;
	public StudentGradePanel(Course c, User u, DashboardFrame theFrame)
	{
		course = c;
		user = u;
		this.theFrame = theFrame;
		//setLayout(new GridLayout(12, 2, 0, 0));
		
		
		
		
	}
	
	
}
