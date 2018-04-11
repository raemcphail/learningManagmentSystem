package frontEnd;

import javax.swing.JPanel;

import server.Course;
import server.User;

import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.DropMode;
import javax.swing.JTextArea;
import javax.swing.BoxLayout;
import javax.swing.SwingConstants;

/**
 * the panel that is displayed when a student presses the Grade button within their course
 * @author louis
 *
 */
public class ProfGradeFrame extends JPanel{
	Course course;
	User user;
	DashboardFrame theFrame;
	private JLabel Comments;
	private JLabel secondAssignment;	
	private JTextArea commentArea;
	private JTextField gradeField;
	public ProfGradeFrame(Course c, User u, DashboardFrame theFrame)
	{
		course = c;
		user = u;
		this.theFrame = theFrame;
		
		populateContent();
	}
	
	public void populateContent()
	{
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		Comments = new JLabel("Comments:");
		add(Comments);
		
		commentArea = new JTextArea();
		commentArea.setRows(5);
		commentArea.setLineWrap(true);
		commentArea.setColumns(5);
		add(commentArea);
		
		secondAssignment = new JLabel("Grade:");
		secondAssignment.setHorizontalAlignment(SwingConstants.LEFT);
		add(secondAssignment);
		
		gradeField = new JTextField();
		gradeField.setMaximumSize(new Dimension(60,20));
		add(gradeField);
		gradeField.setColumns(3);
	}
}
