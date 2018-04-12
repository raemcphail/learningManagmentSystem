package frontEnd;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import server.Course;

import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.Component;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.DefaultListModel;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import javax.swing.JSplitPane;
import javax.swing.JList;
import javax.swing.JSeparator;
/**
 * Panel to display the students, and enroll or unenroll them
 * @author Louis, Raemc
 * @version 1.1
 * @since April 9, 2018
 */
public class StudentPanel extends JPanel
{
	/**
	 * the field by which the user will search
	 */
	protected JTextField searchBar;
	/**
	 * the list within the JList
	 */
	DefaultListModel list;
	/**
	 * used to display student results
	 */
	JList results;
	/**
	 * the scrollpane the above items are contained within
	 */
	JScrollPane scrollpane;
	/**
	 * the search button
	 */
	JButton btnSearch;
	/**
	 * The 'Student" title
	 */
	JLabel title;
	/**
	 * an instance of the listener object when the search button is pressed
	 */
	StudentListener listener;
	/**
	 * Object IO used to send objects across socket
	 */
	ObjectInputStream in = null;
	/**
	 * Object IO used to send objects across socket
	 */
	ObjectOutputStream out = null;
	/**
	 * the current course
	 */
	Course course;

	public StudentPanel(Course course, ObjectInputStream in, ObjectOutputStream out) 
	{
		this.in = in;
		this.out = out;
		this.course = course;
		title = new JLabel ("Students");
		title.setFont(new Font("Tahoma", Font.BOLD, 19));
		searchBar = new JTextField();
		//add(searchBar);
		searchBar.setColumns(10);
		
		listener = new StudentListener(this, in, out);
		
		btnSearch = new JButton("search");
		btnSearch.addActionListener(listener);
		//add(btnSearch);
		
		JPanel upper = new JPanel();
		upper.add(searchBar);
		upper.add(btnSearch);
		
		list = new DefaultListModel();
		results = new JList(list);
		results.addMouseListener(new MouseClicked());
		scrollpane = new JScrollPane(results);
		//this.add(scrollpane);
		
		JSplitPane middle = new JSplitPane(JSplitPane.VERTICAL_SPLIT, upper, scrollpane);
		JSplitPane top = new JSplitPane(JSplitPane.VERTICAL_SPLIT, title, middle);
		this.add(top);
	}
	
	/**
	* Inner class to handle when Prof selects a Student.
	* 
	*/
	class MouseClicked implements MouseListener
	{
		/**
		* required method to handle action, take the student id and send to database,
		* to update studentenrollmenttable depending on selection made by prof
		* @Override
		*/
		public void mouseClicked(MouseEvent e) {
			if (results.isSelectionEmpty()) {
				return;
			}
			try {
			out.writeObject("selected");	//signal the StudentEnrollmentHandler
			String User = (String)(results.getSelectedValue());
			String UserID = User.substring(User.length() - 8, User.length());
			out.writeObject(Integer.parseInt(UserID));	//send the user and coursename
			out.writeObject(course.toString());
				
				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
	}
	
}
