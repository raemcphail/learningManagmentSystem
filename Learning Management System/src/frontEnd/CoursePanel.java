package frontEnd;

import javax.swing.JPanel;
import javax.swing.AbstractButton;

import server.Student;
import server.User;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class CoursePanel extends JPanel {
	protected JButton btnMyCourses;
	protected JButton btnNewButton;
	protected JButton emailButton;
	User user;
	char type;
	ObjectInputStream in;
	ObjectOutputStream out;
	
	public CoursePanel(User u, ObjectInputStream in, ObjectOutputStream out, DashboardFrame Frame) {
		user = u;
		this.in = in;
		this.out = out;
		type = user.getType();
		
		btnMyCourses = new JButton("My Courses");
		add(btnMyCourses);
		
		
		if(type =='P')
		{
			btnNewButton = new JButton("Create Course");
			add(btnNewButton);
		}
		//scale the icon down
		ImageIcon mail = createImageIcon("mail.gif");
		Image BigImage = mail.getImage();
		Image smallImage = BigImage.getScaledInstance(35, 18, java.awt.Image.SCALE_AREA_AVERAGING);
		mail = new ImageIcon(smallImage);
		
		emailButton = new JButton(mail);
		add(emailButton);
		emailButton.setActionCommand("default");
		emailButton.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e)
				{
					if (e.getActionCommand().equals("default"))
					{
						EmailFrame emailFrame = new EmailFrame(user, in, out, Frame);
						emailFrame.receiver.setText("");
					}
					else if (e.getActionCommand().equals("courseSelected"))
					{
						EmailFrame emailFrame = new EmailFrame(user, in, out, Frame);
						emailFrame.receiver.setText("moshipotter@gmail.com");
					}
					else if (e.getActionCommand().equals(user.getCourses().get(0).name))	//if a prof is in a course, send the opCode to enrollment handler
					{
						try {
							System.out.println("in prof action in coursePanel");
							EmailFrame emailFrame = new EmailFrame(user, in, out, Frame);
							out.writeObject("sendEnrolled");
							out.writeObject(user.getCourses().get(0).name);
							ArrayList<Student> students = (ArrayList<Student>)in.readObject();
							if (students.size() == 0)
							{
								emailFrame.receiver.setText("");
							}
							else if (students.size() == 1)
							{
								emailFrame.receiver.setText(students.get(0).getEmail());
							}
							else if (students.size() == 2)
							{
								emailFrame.receiver.setText(students.get(0).getEmail() + ";" + students.get(1).getEmail());								
							}
							
						} catch (IOException | ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					else if (e.getActionCommand().equals(user.getCourses().get(1).name))	//if a prof is in a course, send the opCode to enrollment handler
					{
						try {
							System.out.println("in prof action in coursePanel");
							EmailFrame emailFrame = new EmailFrame(user, in, out, Frame);
							out.writeObject("sendEnrolled");
							out.writeObject(user.getCourses().get(1).name);
							ArrayList<Student> students = (ArrayList<Student>)in.readObject();
							if (students.size() == 0)
							{
								emailFrame.receiver.setText("");
							}
							else if (students.size() == 1)
							{
								emailFrame.receiver.setText(students.get(0).getEmail());
							}
							else if (students.size() == 2)
							{
								emailFrame.receiver.setText(students.get(0).getEmail() + ";" + students.get(1).getEmail());								
							}
							
						} catch (IOException | ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
			
				});
		
	}
	
	protected static ImageIcon createImageIcon(String path)
	{
		java.net.URL imgURL = CoursePanel.class.getResource(path);
		if (imgURL != null)
		{
			return new ImageIcon(imgURL);
		}
		else {
			System.err.println("image not found");
			return null;
		}
	}
	
}
