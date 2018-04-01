package frontEnd;

import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.JButton;

public class CoursePanel extends JPanel {
	public CoursePanel() {
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnMyCourses = new JButton("My Courses");
		add(btnMyCourses);
		
		JButton btnNewButton = new JButton("Create Course");
		add(btnNewButton);
	}
	
}
