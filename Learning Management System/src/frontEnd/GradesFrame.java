package frontEnd;

import javax.swing.JPanel;

import server.Course;
import server.User;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.DropMode;

/**
 * the panel that is displayed when a student presses the Grade button within their course
 * @author louis
 *
 */
public class GradesFrame extends JPanel{
	Course course;
	User user;
	DashboardFrame theFrame;
	private JLabel firstAssignment;
	private JLabel secondAssignment;	
	private JTextField firstGrade;
	public GradesFrame(Course c, User u, DashboardFrame theFrame)
	{
		course = c;
		user = u;
		this.theFrame = theFrame;
		setLayout(new GridLayout(12, 2, 0, 0));
		
		
		populateContent();
	}
	
	public void populateContent()
	{
		firstAssignment = new JLabel("Assignment_name");
		add(firstAssignment);
		
		firstGrade = new JTextField();
		firstGrade.setDropMode(DropMode.INSERT_COLS);
		firstGrade.setText("<grade>");
		add(firstGrade);
		firstGrade.setColumns(3);
		
		secondAssignment = new JLabel("New label");
		add(secondAssignment);
	}
}
