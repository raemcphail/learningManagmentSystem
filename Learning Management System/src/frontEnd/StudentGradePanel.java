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
 * @author Louis, Raemc
 * @version 1.1
 * @since April 9, 2018
 */
public class StudentGradePanel extends JPanel {
	/**
	 * their course
	 */
	Course course;
	/**
	 * the curretn user
	 */
	User user;
	/**
	 * the main frame
	 */
	DashboardFrame theFrame;

	public StudentGradePanel(Course c, User u, DashboardFrame theFrame)
	{
		course = c;
		user = u;
		this.theFrame = theFrame;
	}
}
