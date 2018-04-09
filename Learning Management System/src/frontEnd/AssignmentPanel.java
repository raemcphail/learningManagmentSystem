package frontEnd; 

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import server.Course;

import javax.swing.JButton;
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
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import javax.swing.JSplitPane;
import javax.swing.JList;
import javax.swing.JSeparator;
import java.awt.Font;

public class AssignmentPanel extends JPanel
{
	JList results;
	JScrollPane scrollpane;
	JButton btnAdd;
	JLabel title;
	DefaultListModel list;
	ObjectOutputStream out = null;
	ObjectInputStream in = null;
	CourseViewPanel courseView;
	Course course;
	DashboardFrame theFrame;
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
		
	}
		class MouseClicked implements MouseListener
		{
			/**
			* required method to handle action, 
			* @Override
			*/
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
//						out.writeObject(course.toString());
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
					File newFile = new File("C:\\Users\\raemc\\Desktop\\Student\\assignment.txt");
					if(! newFile.exists())
					{
						newFile.createNewFile();
					}
					FileOutputStream writer = new FileOutputStream(newFile);
					BufferedOutputStream bos = new BufferedOutputStream(writer);
					bos.write(content);
					bos.close();
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
