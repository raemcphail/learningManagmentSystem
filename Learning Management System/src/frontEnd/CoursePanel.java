package frontEnd;

import javax.swing.JPanel;

import server.User;

import java.awt.CardLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;

public class CoursePanel extends JPanel {
	protected JButton btnMyCourses;
	protected JButton btnNewButton;
	char type;
	
	public CoursePanel(char t) {
		
		type = t;
		
		btnMyCourses = new JButton("My Courses");
		add(btnMyCourses);
		
		if(type =='P')
		{
			btnNewButton = new JButton("Create Course");
			add(btnNewButton);
		}
	}
	
}
