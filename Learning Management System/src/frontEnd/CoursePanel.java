package frontEnd;

import javax.swing.JPanel;

import java.awt.CardLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;

public class CoursePanel extends JPanel {
	protected JButton btnMyCourses;
	protected JButton btnNewButton;
	
	public CoursePanel() {
		
		btnMyCourses = new JButton("My Courses");
		add(btnMyCourses);
		
		btnNewButton = new JButton("Create Course");
		add(btnNewButton);
	}
	
}
