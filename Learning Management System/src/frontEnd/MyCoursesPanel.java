package frontEnd;
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

	JLabel emptyCourseMessage;
	ObjectOutputStream out = null;
	ObjectInputStream in = null;
	ArrayList<Course> courses;
	JButton [] courseButtons;
	
	MyCoursesPanel(ObjectOutputStream out, ObjectInputStream in)
	{
		this.in = in;
		this.out = out;
		courses = new ArrayList<Course>();
		showCourses();
		
	}
	
	@SuppressWarnings("unchecked")
	public void showCourses()
	{
		try
		{
			courses = (ArrayList<Course>)in.readObject();
			if (courses != null)
			{
				courseButtons = new JButton[courses.size()];
				Iterator it = courses.iterator();
				int i = 0;
				while (it.hasNext())
				{
					courseButtons[i] = new JButton(it.next().toString());
					//add action listners here
					add(courseButtons[i]);	//add to panel
					i++;
				}
			}
			else 
			{
				emptyCourseMessage = new JLabel("You have no courses at the moment!");
				emptyCourseMessage.setHorizontalAlignment(SwingConstants.CENTER);
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
