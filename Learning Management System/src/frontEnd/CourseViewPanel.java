package frontEnd;

import javax.swing.JPanel;
import java.awt.BorderLayout;

public class CourseViewPanel extends JPanel
{
	public CourseViewPanel() 
	{
		setLayout(new BorderLayout(0, 0));
		add("West", new SideViewPanel());
		add("Center", new AssignmentPanel());
	}

}
