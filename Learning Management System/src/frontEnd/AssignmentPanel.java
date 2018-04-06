package frontEnd; 

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import server.Course;

import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.Component;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import javax.swing.JSplitPane;
import javax.swing.JList;
import javax.swing.JSeparator;
import java.awt.Font;

public class AssignmentPanel extends JPanel
{
	JList results;
	JScrollPane scrollpane;
	JButton btnAdd;
	JLabel title;
	ObjectOutputStream out = null;
	ObjectInputStream in = null;
	Course course;
	
	public AssignmentPanel(ObjectInputStream i, ObjectOutputStream o, Course c) 
	{
		in = i;
		out = o;
		course = c;
		//setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		title = new JLabel ("Assignments");
		title.setFont(new Font("Tahoma", Font.BOLD, 19));
		add(title);
		results = new JList(new DefaultListModel());
		scrollpane = new JScrollPane(results);
		btnAdd = new JButton("Add");
		btnAdd.addActionListener(new AssignmentListener(this, out, in, course));
		
		
		JSplitPane split2 = new JSplitPane(JSplitPane.VERTICAL_SPLIT, scrollpane, btnAdd);
		JSplitPane split1 = new JSplitPane(JSplitPane.VERTICAL_SPLIT, title, split2);
		this.add(split1);
		
		
	}
}
