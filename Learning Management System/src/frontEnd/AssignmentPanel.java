package frontEnd; 

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.Component;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
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
	
	public AssignmentPanel() 
	{
		//setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		title = new JLabel ("Assignments");
		title.setFont(new Font("Tahoma", Font.BOLD, 19));
		add(title);
		results = new JList(new DefaultListModel());
		scrollpane = new JScrollPane(results);
		btnAdd = new JButton("Add");
		
		
		JSplitPane split2 = new JSplitPane(JSplitPane.VERTICAL_SPLIT, scrollpane, btnAdd);
		JSplitPane split1 = new JSplitPane(JSplitPane.VERTICAL_SPLIT, title, split2);
		this.add(split1);
		
		
	}
}
