package frontEnd;
import javax.swing.*;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Component;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Font;

public class SideViewPanel extends JPanel
{
	JButton btnStudent;
	JButton btnGrades;
	JButton btnDropbox;
	JButton btnAssignments;
	JButton btnBack;
	public SideViewPanel ()
	{
		setBackground(new Color(0, 153, 153));
		setForeground(Color.CYAN);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		btnBack = new JButton("back");
		add(btnBack);
		
		Component verticalStrut = Box.createVerticalStrut(15);
		add(verticalStrut);
		
		JLabel courseName = new JLabel("<course name>");
		courseName.setFont(new Font("Arial Black", Font.BOLD, 20));
		courseName.setForeground(new Color(255, 255, 255));
		add(courseName);
		
		Component verticalStrut_1 = Box.createVerticalStrut(20);
		verticalStrut_1.setBackground(new Color(51, 153, 153));
		add(verticalStrut_1);
		
		btnAssignments = new JButton("assignments");
		add(btnAssignments);
		
		Component verticalStrut_2 = Box.createVerticalStrut(20);
		add(verticalStrut_2);
		
		btnDropbox = new JButton("dropbox");
		add(btnDropbox);
		
		Component verticalStrut_3 = Box.createVerticalStrut(20);
		add(verticalStrut_3);
		
		btnGrades = new JButton("grades");
		add(btnGrades);
		
		Component verticalStrut_4 = Box.createVerticalStrut(20);
		add(verticalStrut_4);
		
		btnStudent = new JButton("student");
		add(btnStudent);
		
	
	}
}
