package frontEnd;

import server.Course;
import server.User;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.DropMode;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.BoxLayout;
import javax.swing.SwingConstants;
import javax.swing.JButton;

/**
 * the panel that is displayed when a student presses the Grade button within their course
 * @author louis
 *
 */
public class ProfGradeFrame extends JFrame{
	Course course;
	User user;
	DashboardFrame theFrame;
	private JLabel Comments;
	private JLabel gradeLabel;	
	private JTextArea commentArea;
	private JTextField gradeField;
	private JButton btnSubmit;
	private int studentID;
	private String timeStamp;
	ObjectInputStream in = null;
	ObjectOutputStream out = null;
	public ProfGradeFrame(ObjectInputStream in, ObjectOutputStream out, Course c, User u, DashboardFrame theFrame, String studentID, String timeStamp)
	{
		this.in = in;
		this.out = out;
		course = c;
		user = u;
		this.theFrame = theFrame;
		this.studentID = Integer.parseInt(studentID);
		this.timeStamp = timeStamp;
		
		populateContent();
		setTitle("Report");
		setSize(400,300);
		setResizable(false);
		setVisible(true);
	}
	
	public void populateContent()
	{
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		Comments = new JLabel("Comments:");
		getContentPane().add(Comments);
		
		commentArea = new JTextArea();
		commentArea.setRows(5);
		commentArea.setLineWrap(true);
		commentArea.setColumns(5);
		getContentPane().add(commentArea);
		
		gradeLabel = new JLabel("Grade:");
		gradeLabel.setHorizontalAlignment(SwingConstants.LEFT);
		getContentPane().add(gradeLabel);
		
		gradeField = new JTextField();
		gradeField.setMaximumSize(new Dimension(60,20));
		getContentPane().add(gradeField);
		gradeField.setColumns(3);
		
		btnSubmit = new JButton("Submit");
		getContentPane().add(btnSubmit);
		btnSubmit.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e)
				{
					try {
						if (gradeField.getText().equals(""))
						{
							JOptionPane.showMessageDialog(null, "Please enter a grade", "Need Grade", JOptionPane.ERROR_MESSAGE);
							return;
						}
						if (Integer.parseInt(gradeField.getText()) < 0 || Integer.parseInt(gradeField.getText()) > 100)
						{
							JOptionPane.showMessageDialog(null, "Please enter a value between 0 and 100", "Grade Error", JOptionPane.ERROR_MESSAGE);							
							return;
						}	
						out.writeObject("updateGrade");	//send opCode
						out.writeObject(studentID);	//sends the id as an int
						out.writeObject(timeStamp);	//sends the timeStamp as a String
						out.writeObject(Integer.parseInt(gradeField.getText())); //sends the assignment grade as an int
						out.writeObject(commentArea.getText());	//send the comment
						disposeFrame();
					} catch (IOException e1) {
						e1.printStackTrace();
					} catch (NumberFormatException ex)
					{
						JOptionPane.showMessageDialog(null, "Please enter a number!", "Grade Error", JOptionPane.ERROR_MESSAGE);
						return;
					}
					
				}});
	}
	public void disposeFrame()
	{
		this.dispose();
	}
}
