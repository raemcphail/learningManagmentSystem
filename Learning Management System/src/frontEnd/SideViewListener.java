package frontEnd;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

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
		if (e.getSource() == coursePanel.svpanel.btnGrades)
		{
			try
			{
				coursePanel.out.writeObject("showGrades");
				coursePanel.out.writeObject(coursePanel.theFrame.user.getID());
				ArrayList<String> names = (ArrayList<String>)coursePanel.in.readObject();
				ArrayList<Integer> grades = (ArrayList<Integer>)coursePanel.in.readObject();
				if(names.size()!=0 && names.size()==grades.size())
				{
					Iterator nit = names.iterator();
					Iterator git = grades.iterator();
					while(nit.hasNext())
					{
						Component verticalStrut = Box.createVerticalStrut(60);
						coursePanel.studentGrade.add(verticalStrut);
						coursePanel.studentGrade.add(new JLabel(nit.next().toString()));
						coursePanel.studentGrade.add(verticalStrut);
						JTextArea grade = new JTextArea(git.next().toString());
						grade.setEditable(false);
						coursePanel.studentGrade.add(grade);
						coursePanel.studentGrade.add(verticalStrut);
					}
						
				}
				else
				{
					coursePanel.studentGrade.add(new JLabel("There are no marked assignments"));
				}
				clayout.show(selection, "studentGrade");
			}catch(IOException|ClassNotFoundException i)
			{
				i.printStackTrace();
			}
		}
		
	}
	
}
