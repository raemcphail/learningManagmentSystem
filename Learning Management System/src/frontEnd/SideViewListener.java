package frontEnd;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

public class SideViewListener implements ActionListener 
{
	SideViewPanel sideView;
	CardLayout clayout;
	JPanel selection;
	
	SideViewListener (SideViewPanel p, CardLayout l, JPanel s)
	{
		sideView = p;
		clayout = l;
		selection = s;
	}
	
	public void actionPerformed(ActionEvent e) {
		System.out.println("help?");

		if (e.getSource() == sideView.btnAssignments)
		{
			System.out.println("Assignment");
			clayout.show(selection, "assignment");
		}
		if (e.getSource() == sideView.btnStudent)
		{
			System.out.println("Student");
			clayout.show(selection, "student");
		}
	
		
	}
	
}
