package frontEnd;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class CourseViewPanel extends JPanel
{
	AssignmentPanel assignmentpanel;
	StudentPanel studentpanel;
	SideViewPanel svpanel;
	CardLayout c;
	JPanel selection = new JPanel();
	public CourseViewPanel() 
	{
		setLayout(new BorderLayout(0, 0));
		svpanel = new SideViewPanel();
		add("West", svpanel);
		
		
		assignmentpanel = new AssignmentPanel();
		studentpanel = new StudentPanel();
		
		c = new CardLayout();
		selection.setLayout(c);
		selection.add(assignmentpanel,"assignment");
		selection.add(studentpanel,"student");
		
		c.show(selection, "student");
		
		svpanel.btnStudent.addActionListener(new SideViewListener(svpanel, c, selection));
		svpanel.btnAssignments.addActionListener(new SideViewListener(svpanel, c, selection));
		
		add("Center", selection);
	}
	
	//TESTING
	public static void main(String[] args)
	{
		JFrame frame = new JFrame();
		frame.setSize(500,500);
		frame.setContentPane(new CourseViewPanel());
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

}
