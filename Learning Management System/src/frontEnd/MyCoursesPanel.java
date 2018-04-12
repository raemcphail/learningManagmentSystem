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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import server.Course;

/**
 * Displays the options for courses to select when the 'MyCourses'
 * button is selected, and populates the contentPanel area.
 * @author Louis, Raemc
 * @version 1.1
 * @since April 9, 2018
 */
public class MyCoursesPanel extends JPanel {
	/**
	 * the main frame
	 */
	DashboardFrame theFrame;
	/**
	 * an empty course message, if that student has no courses
	 */
	JLabel emptyCourseMessage;
	/**
	 * Object IO used to send objects across socket
	 */
	ObjectOutputStream out = null;
	/**
	 * Object IO used to send objects across socket
	 */
	ObjectInputStream in = null;
	/**
	 * the courses that user has
	 */
	ArrayList<Course> courses;
	/**
	 * the corresponding buttons for those courses
	 */
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
	/**
	 * method to actually display courses on GUI
	 */
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
					theFrame.user.getCourses().add(temp);	//add the course to user
					courseButtons[i] = new JButton(temp.toString());
					
					if (theFrame.user.getType() == 'P')	//the user is a prof
					{
						System.out.println("we a prof!!");
						courseButtons[i].setActionCommand("prof");
					}
					else if (temp.getActive())	//if the course is active
					{
						courseButtons[i].setActionCommand("active");
					} 
					else
					{
						courseButtons[i].setActionCommand("notActive");
					}
					CourseListener listener = new CourseListener(temp);
					courseButtons[i].addActionListener(listener);
					
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
	/**
	 * inner action listener class to handle the functionality of the course selection
	 * displays the course if active(student), and gives them their functionality
	 * 
	 * @author louis Rae
	 */
	public class CourseListener implements ActionListener
	{
		Course thatCourse;
		public CourseListener(Course course)
		{
			thatCourse = course;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			boolean active = true;
			try {
				out.writeObject("getBool");	//send opcode
				out.writeObject(thatCourse);
				active = (boolean)in.readObject();
			} catch (IOException | ClassNotFoundException e1) {
				e1.printStackTrace();
			}	
			if (theFrame.user.getType() == 'S' && !active)	//if the course has been deactivated by prof
			{
				JOptionPane.showMessageDialog(null, "This course is not active.", "Not Active", JOptionPane.ERROR_MESSAGE);
				return;
			}
			else if (theFrame.user.getType() == 'S' && active)
			{
				CourseViewPanel courseView = new CourseViewPanel(theFrame, thatCourse, in, out);
				theFrame.middleBar.emailButton.setActionCommand("courseSelected");   //set the emailbutton to include the prof
				theFrame.content.add((courseView), "theCourse");
				theFrame.cardLayout.show(theFrame.content, "theCourse");
			}
			
			
			
			else if (e.getActionCommand().equals("active"))	//if the course is active
			{
				CourseViewPanel courseView = new CourseViewPanel(theFrame, thatCourse, in, out);
				theFrame.middleBar.emailButton.setActionCommand("courseSelected");   //set the emailbutton to include the prof
				theFrame.content.add((courseView), "theCourse");
				theFrame.cardLayout.show(theFrame.content, "theCourse");
			}
			else if (e.getActionCommand().equals("prof"))
			{
				CourseViewPanel courseView = new CourseViewPanel(theFrame, thatCourse, in, out);
				theFrame.middleBar.emailButton.setActionCommand(thatCourse.name);   //set the emailbutton to include the prof
				theFrame.content.add((courseView), "theCourse");
				theFrame.cardLayout.show(theFrame.content, "theCourse");
			}
			else if (e.getActionCommand().equals("notActive"))
			{
				JOptionPane.showMessageDialog(null, "This course is not active.", "Not Active", JOptionPane.ERROR_MESSAGE);
			}
			
		}
		
	}
	
	protected void setVisible() {
		this.setVisible(true);
	}
	

}
