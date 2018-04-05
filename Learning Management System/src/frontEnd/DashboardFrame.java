package frontEnd;

import javax.swing.*;

import server.User;

import java.awt.GridLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
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
	/**
	 * content Panel to be set visible by default
	 */
	protected MyCoursesPanel myCourses;
	/**
	 * content Panel to be set visible if user presses the createCourse button
	 */
	protected CreateCoursePanel createCourses;
	/**
	 * 
	 */
	protected User user;
	/**
	 * 
	 */
	protected CardLayout cardLayout;
	/**
	 * the panel that displays everything
	 * @param user
	 */
	ContentPanel content;
	
	public DashboardFrame(User user) {
		this.user = user;
		setSize(450,276);
		mainPanel = new JPanel();
		this.setContentPane(mainPanel);
		
		cardLayout = new CardLayout();
		
		upperPanel = new JPanel();
		lowerPanel = new JPanel();
		lowerPanel.setLayout(new GridLayout(1, 0, 0, 0));
		
		title = new TitlePanel(user.getFirstname(), user.getLastname());
		
		content = new ContentPanel();
		middleBar = new CoursePanel();
		content.setLayout(cardLayout);
		
		myCourses = new MyCoursesPanel();
		createCourses = new CreateCoursePanel();
		content.add(myCourses, "courses");
		content.add(createCourses, "createCourses");
		cardLayout.show(content, "courses");

		lowerPanel.add(content);
		//lowerPanel.add(createCourses);

		createCourses.setVisible(false);
		createCourses.setEnabled(false);
		createCourses.setLayout(new BoxLayout(createCourses, BoxLayout.Y_AXIS));
		addDividers();
		addListeners();
		pack();
	}
	
	public void addDividers()
	{
		upperMiddle = new JSplitPane(JSplitPane.VERTICAL_SPLIT, title, middleBar);
		upperPanel.add(upperMiddle);
		
		middle = new JSplitPane(JSplitPane.VERTICAL_SPLIT, upperPanel, lowerPanel);
		mainPanel.add(middle);

	}
	
	public void addListeners()
	{
		MiddleBarListener listenerI = new MiddleBarListener(this);
		middleBar.btnMyCourses.addActionListener(listenerI);
		middleBar.btnNewButton.addActionListener(listenerI);
	}
	
}
