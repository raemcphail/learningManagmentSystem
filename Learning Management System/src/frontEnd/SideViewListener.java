package frontEnd;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

public class SideViewListener implements ActionListener 
{
	CourseViewPanel coursePanel;
	CardLayout clayout;
	JPanel selection;
	
	SideViewListener (CourseViewPanel p, CardLayout l, JPanel s)
	{
		coursePanel = p;
		clayout = l;
		selection = s;
	}
	
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == coursePanel.svpanel.btnAssignments)
		{
			System.out.println("Assignment");
			clayout.show(selection, "assignment");
		}
		if (e.getSource() == coursePanel.svpanel.btnStudent)
		{
			System.out.println("Student");
			clayout.show(selection, "student");
		}
		if (e.getSource() == coursePanel.svpanel.btnDropbox)
		{
			System.out.println("Dropbox");
			clayout.show(selection, "dropbox");
		}
		
	}
	
}
