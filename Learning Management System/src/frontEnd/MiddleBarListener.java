package frontEnd;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * listener to select the ContentPanel that displays the courses(or lack there of), or the 
 * create courses, **to add course catalog later
 * @author louis
 *
 */
public class MiddleBarListener implements ActionListener {
	DashboardFrame theFrame;
	MiddleBarListener(DashboardFrame theFrame)
	{
		this.theFrame = theFrame;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("help?");

		if (e.getSource() == theFrame.middleBar.btnMyCourses)
		{
			theFrame.cardLayout.show(theFrame.content, "courses");
			System.out.println("my courses");
		}
		if (e.getSource() == theFrame.middleBar.btnNewButton)
		{
			theFrame.cardLayout.show(theFrame.content, "createCourses");
			System.out.println("create courses");
		}
		/*	to add course catalog here****
		if (e.getSource() == "MyCourses")
		{
			theFrame.cardLayout.show(theFrame.content, "courses");
		}*/
		
	}

}
