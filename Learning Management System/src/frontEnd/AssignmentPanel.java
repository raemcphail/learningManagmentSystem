package frontEnd; 

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.DefaultListModel;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import javax.swing.JSplitPane;
import javax.swing.JList;
import javax.swing.JSeparator;

public class AssignmentPanel extends JPanel
{
	JList results;
	JScrollPane scrollpane;
	JButton btnAdd;
	
	public AssignmentPanel() 
	{
		
		results = new JList(new DefaultListModel());
		scrollpane = new JScrollPane(results);
		btnAdd = new JButton("Add");
		
		JSplitPane middle = new JSplitPane(JSplitPane.VERTICAL_SPLIT, scrollpane, btnAdd);
		this.add(middle);
		
	}
}
