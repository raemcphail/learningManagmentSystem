package frontEnd;

import javax.swing.*;
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
	/**
	 * the title Panel to be added to the upperPanel
	 */
	TitlePanel title;
	/**
	 * the course, middle bar to be added to the upperPanel
	 */
	CoursePanel middleBar;
	/**
	 * the divider between the middlebar and the content pane
	 */
	JSplitPane middle;
	/**
	 * the divider between the title and the courseBar
	 */
	JSplitPane upperMiddle;
	
	public DashboardFrame() {
		setSize(700,700);
		mainPanel = new JPanel();
		this.setContentPane(mainPanel);
		
		upperPanel = new JPanel();
		lowerPanel = new JPanel();
		
		title = new TitlePanel();
		
		middleBar = new CoursePanel();
		
		addDividers();
		this.pack();
	}
	
	public void addDividers()
	{
		upperMiddle = new JSplitPane(JSplitPane.VERTICAL_SPLIT, title, middleBar);
		upperPanel.add(upperMiddle);
		
		middle = new JSplitPane(JSplitPane.VERTICAL_SPLIT, upperPanel, lowerPanel);
		mainPanel.add(middle);

	}
}
