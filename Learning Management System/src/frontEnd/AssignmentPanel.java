package frontEnd; 

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import server.Course;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;

import java.awt.Component;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import javax.swing.JSplitPane;
import javax.swing.JList;
import javax.swing.JSeparator;
import java.awt.Font;
/**
 * Assignment panel displays when the assignment button is pressed,
 *  and displays a list of assignments, active ones if student,
 *  all if prof, and also displays add button if prof.
 * @author Louis, Raemc
 * @version 1.1
 * @since April 9, 2018
 */
public class AssignmentPanel extends JPanel
{
	/**
	 * the list to show the assignment name
	 */
	JList results;
	/**
	 * the scollpane contains the result list
	 */
	JScrollPane scrollpane;
	/**
	 * the add button shows on the prof view, adds an assignment from file
	 */
	JButton btnAdd;
	/**
	 * displays 'Assignments"
	 */
	JLabel title;
	/**
	 * list is contained with results
	 */
	DefaultListModel list;
	/**
	 * Object IO used to send objects across socket
	 */
	ObjectOutputStream out = null;
	/**
	 * Object IO used to send objects across socket
	 */
	ObjectInputStream in = null;
	/**
	 * the current course view panel
	 */
	CourseViewPanel courseView;
	/**
	 * the course object that the user is currently on
	 */
	Course course;
	/**
	 * the overall dashboard frame that contains everything
	 */
	DashboardFrame theFrame;
	/**
	 * the user type- Student or Prof
	 */
	char type;
	
	public AssignmentPanel(CourseViewPanel courseView, ObjectInputStream i, ObjectOutputStream o, Course c, DashboardFrame theFrame) 
	{
		this.theFrame = theFrame;
		this.courseView = courseView;
		type = theFrame.user.getType();
		System.out.println(type);
		in = i;
		out = o;
		course = c;
		//setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		title = new JLabel ("Assignments");
		title.setFont(new Font("Tahoma", Font.BOLD, 19));
		add(title);
		list = new DefaultListModel();
		results = new JList(list);
		results.addMouseListener(new MouseClicked());
		scrollpane = new JScrollPane(results);
		
		btnAdd = new JButton("Add");
		btnAdd.addActionListener(new AssignmentListener(courseView, out, in, course, theFrame));
		
		JSplitPane split2 = new JSplitPane(JSplitPane.VERTICAL_SPLIT, scrollpane, btnAdd);
		JSplitPane split1 = new JSplitPane(JSplitPane.VERTICAL_SPLIT, title, split2);
		this.add(split1);
		if (theFrame.user.getType() == 'S')
		{
			btnAdd.setVisible(false);
		}
		
	}
	/**
	 * if the user selects an assignment, if they are a prof, it prompts them to
	 * either activate or deactivate that assignment, otherwise if student,
	 * to download.
	 */
		class MouseClicked implements MouseListener
		{
			@Override
			public void mouseClicked(MouseEvent e) {
				if (results.isSelectionEmpty()) {
					return;
				}
				try {
					if(theFrame.user.getType() == 'P')
					{	
						out.writeObject("updateAssign");	//signal the AssignmentHandler.updateActive
						String Title = (String)(results.getSelectedValue());
						out.writeObject(Title);	//send the title
						out.writeObject(course);//send the course
					}
					else
					{
						out.writeObject("downloadAssign");
						String Title = (String)(results.getSelectedValue());
						out.writeObject(Title);
						out.writeObject(course);
						recieveFile();
						
					
					}
					
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

			public void recieveFile()
			{
				try
				{
					byte[] content = (byte[]) in.readObject();

					JFileChooser fileBrowser = new JFileChooser();
					fileBrowser.setCurrentDirectory(new File("C:\\"));
					int retrival = fileBrowser.showSaveDialog(null);
					if(retrival == JFileChooser.APPROVE_OPTION)
					{
						try
						{
							FileOutputStream writer = new FileOutputStream(fileBrowser.getSelectedFile());
							BufferedOutputStream bos = new BufferedOutputStream(writer);
							bos.write(content);
							bos.close();
						}catch(IOException e)
						{
							e.printStackTrace();
						}
					}
					
				}catch(IOException e)
				{
					e.printStackTrace();
				}catch(ClassNotFoundException e)
				{
					e.printStackTrace();
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
