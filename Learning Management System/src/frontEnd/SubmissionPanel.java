package frontEnd;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import server.Course;
import server.Submissions;
import server.User;

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
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import javax.swing.JSplitPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import java.awt.Font;

public class SubmissionPanel extends JPanel
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
	String assignName;
	char type;
	
	public SubmissionPanel(CourseViewPanel courseView, ObjectInputStream i, ObjectOutputStream o, Course c, DashboardFrame theFrame, String AssignName) 
	{
		this.theFrame = theFrame;
		this.courseView = courseView;
		type = theFrame.user.getType();
		System.out.println(type);
		in = i;
		out = o;
		assignName = AssignName;
		course = c;
		//setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		title = new JLabel ("Submissions for " + AssignName);
		title.setFont(new Font("Tahoma", Font.BOLD, 19));
		add(title);
		list = new DefaultListModel();
		results = new JList(list);
		results.addMouseListener(new MouseClicked());
		scrollpane = new JScrollPane(results);
		
		JSplitPane split2 = new JSplitPane(JSplitPane.VERTICAL_SPLIT, scrollpane, btnAdd);
		JSplitPane split1 = new JSplitPane(JSplitPane.VERTICAL_SPLIT, title, split2);
		this.add(split1);
		
		try 
		{
			list.clear();
			out.writeObject("getSubs");	//signal the AssignmentHandler.updateActive
			//need to find assignmentid
			out.writeObject(assignName);	//send the name of the assignment
			out.writeObject(course);//send the course
			ArrayList<Submissions> submissions = (ArrayList<Submissions>)in.readObject();	
			if(submissions.size()!=0)
			{
				System.out.println("submissions is NOT empty!");

			Iterator it = submissions.iterator();
			while(it.hasNext())
			{
				list.addElement(it.next().toString());
			}
			}
			else
			{
				System.out.println("submissions is empty!");
			}
		}
		catch (IOException | ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
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
				try 
				{
					String sub = (String)(results.getSelectedValue());//contains studentID followed by space then date
					String [] subdata = sub.split(" ");
					
					int choice = JOptionPane.showConfirmDialog(null, "Would you like to download this submission?", "Download", JOptionPane.YES_NO_OPTION);
					if (choice == JOptionPane.YES_OPTION)
					{
						out.writeObject("downloadSub");
						out.writeObject(subdata[0]);
						out.writeObject(subdata[1]);
						recieveFile();
					}
					choice = JOptionPane.showConfirmDialog(null, "Would you like to grade this submission?", "Grade", JOptionPane.YES_NO_OPTION);
					if (choice == JOptionPane.YES_OPTION)
					{
						//	public ProfGradeFrame(Course c, User u, DashboardFrame theFrame)
						ProfGradeFrame grade = new ProfGradeFrame(in, out, course, theFrame.user, theFrame, subdata[0], subdata[1]);
						grade.setVisible(true);
					}
					
				}
				catch (IOException e1) {
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
