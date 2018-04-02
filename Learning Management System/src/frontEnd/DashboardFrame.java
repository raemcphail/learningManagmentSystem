package frontEnd;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class DashboardFrame extends JFrame {
	/**
	 * the overlaying panel
	 */
	JPanel mainPanel;
	/**
	 * the upper portion of the frame, containing the title and course bar always, only variable is if
	 * the user is prof or student, since prof implies create course button, and student implies courseCatalog
	 */
	JPanel upperPanel;
	/**
	 * the lower portion of the frame, which is an instance of contentPanel, and varies based on user input
	 */
	JPanel lowerPanel;
	
	public DashboardFrame() {
		setSize(700,700);
		mainPanel = new JPanel();
		this.setContentPane(mainPanel);
		TitlePanel title = new TitlePanel();
		mainPanel.add(title);
		CoursePanel middleBar = new CoursePanel();
		mainPanel.add(middleBar);
		//this.pack();
	}
}
