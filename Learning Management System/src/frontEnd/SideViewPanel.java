package frontEnd;
import javax.swing.*;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Component;

public class SideViewPanel extends JPanel
{
	public SideViewPanel ()
	{
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JButton btnBack = new JButton("back");
		add(btnBack);
		
		Component verticalStrut = Box.createVerticalStrut(15);
		add(verticalStrut);
		
		JLabel courseName = new JLabel("<course name>");
		add(courseName);
		
		JButton btnAssignments = new JButton("assignments");
		add(btnAssignments);
		
		JButton btnDropbox = new JButton("dropbox");
		add(btnDropbox);
		
		JButton btnGrades = new JButton("grades");
		add(btnGrades);
		
		JButton btnStudent = new JButton("student");
		add(btnStudent);
		
	
	}
}
