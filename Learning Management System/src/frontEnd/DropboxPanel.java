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
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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

public class DropboxPanel extends JPanel
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
	
	public DropboxPanel(CourseViewPanel courseView, ObjectInputStream i, ObjectOutputStream o, Course c, DashboardFrame theFrame) 
	{
		this.theFrame = theFrame;
		this.courseView = courseView;
		type = theFrame.user.getType();
		System.out.println(type);
		in = i;
		out = o;
		course = c;
		//setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		title = new JLabel ("Dropbox");
		title.setFont(new Font("Tahoma", Font.BOLD, 19));
		add(title);
		list = new DefaultListModel();
		results = new JList(list);
		results.addMouseListener(new MouseClicked());
		scrollpane = new JScrollPane(results);
		
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
//						out.writeObject("updateAssign");	//signal the AssignmentHandler.updateActive
//						String Title = (String)(results.getSelectedValue());
//						out.writeObject(Title);	//send the title
//						out.writeObject(course);//send the course
////						out.writeObject(course.toString());
					}
					else
					{
						out.writeObject("uploadSub");
						String Title = (String)(results.getSelectedValue());
						out.writeObject(Title);
						out.writeObject(course);
						out.writeObject(theFrame.user.getID());
						sendFile();
						
					
					}
					
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

			public void sendFile()
			{
				System.out.println("Sending file");
				JFileChooser fileBrowser = new JFileChooser();
				File selectedFile = null; 
					if(fileBrowser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
					{
						selectedFile = fileBrowser.getSelectedFile();
						String filename = selectedFile.getName();
						String ex = filename.substring(filename.length() -4, filename.length());//gets the extention from file name
						String name = filename.substring(0, filename.length()-4);//gets the extention from file name
						System.out.println(name);
						if(!ex.equals(".txt") && !ex.equals(".pdf"))
						{
							System.out.println("Unacceptable file");
							return;
						}
						try 
						{
						out.writeObject(ex);
						out.writeObject(name);
						}
						catch(IOException e)
						{
							e.getStackTrace();
						}
						long length = selectedFile.length();
						byte[] content = new byte [(int)length];
						try 
						{
							FileInputStream fis = new FileInputStream (selectedFile);
							BufferedInputStream bos = new BufferedInputStream (fis);
							bos.read(content, 0, (int)length);
							
							out.writeObject(content);
							out.flush();
							System.out.println("File sent");
						}catch(FileNotFoundException e)
						{
							e.printStackTrace();
						}
						catch(IOException e)
						{
							e.printStackTrace();
						}
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
					
//					FileOutputStream writer = new FileOutputStream(newFile);
//					BufferedOutputStream bos = new BufferedOutputStream(writer);
//					bos.write(content);
//					bos.close();
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

