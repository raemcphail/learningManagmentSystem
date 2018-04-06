package frontEnd;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import server.Course;

public class MyCoursesPanel extends JPanel {

	DashboardFrame theFrame;
	JLabel emptyCourseMessage;
	ObjectOutputStream out = null;
	ObjectInputStream in = null;
	ArrayList<Course> courses;
	JButton [] courseButtons;
	
	MyCoursesPanel(DashboardFrame theFrame, ObjectOutputStream out, ObjectInputStream in)
	{
		this.in = in;
		this.out = out;
		this.theFrame = theFrame;
		courses = new ArrayList<Course>();
		emptyCourseMessage = new JLabel("You have no courses at the moment!");
		emptyCourseMessage.setHorizontalAlignment(SwingConstants.CENTER);
		showCourses();
		
	}
	
	@SuppressWarnings("unchecked")
	public void showCourses()
	{
		try
		{
			courses = (ArrayList<Course>)in.readObject();
			if (courses.size() != 0)
			{
				remove(emptyCourseMessage);
				courseButtons = new JButton[courses.size()];
				Iterator it = courses.iterator();
				int i = 0;
				while (it.hasNext())
				{
					Course temp = (Course)it.next();
					courseButtons[i] = new JButton(temp.toString());
					courseButtons[i].addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							CourseViewPanel courseView = new CourseViewPanel(theFrame, temp, in, out);
							theFrame.content.add((courseView), "theCourse");
							theFrame.cardLayout.show(theFrame.content, "theCourse");
						}
					});
					add(courseButtons[i]);	//add to panel
					i++;
				}
			}
			else 
			{
				add(emptyCourseMessage);
			}
			
		} catch (ClassNotFoundException | IOException e)
		{
			e.printStackTrace();
			System.err.println("error in my courses panel");
		}
	}
	
	protected void setVisible() {
		this.setVisible(true);
	}
	

}
