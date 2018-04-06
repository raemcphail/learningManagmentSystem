package frontEnd;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.Component;
import java.awt.Font;

import javax.swing.Box;
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

public class StudentPanel extends JPanel
{
	protected JTextField searchBar;
	DefaultListModel list;
	JList results;
	JScrollPane scrollpane;
	JButton btnSearch;
	JLabel title;
	StudentListener listener;
	ObjectInputStream in = null;
	ObjectOutputStream out = null;

	public StudentPanel(ObjectInputStream in, ObjectOutputStream out) 
	{
		this.in = in;
		this.out = out;
		title = new JLabel ("Students");
		title.setFont(new Font("Tahoma", Font.BOLD, 19));
		searchBar = new JTextField();
		//add(searchBar);
		searchBar.setColumns(10);
		
		listener = new StudentListener(this, in, out);
		
		btnSearch = new JButton("search");
		btnSearch.addActionListener(listener);
		//add(btnSearch);
		
		JPanel upper = new JPanel();
		upper.add(searchBar);
		upper.add(btnSearch);
		
		list = new DefaultListModel();
		results = new JList(list);
		scrollpane = new JScrollPane(results);
		//this.add(scrollpane);
		
		JSplitPane middle = new JSplitPane(JSplitPane.VERTICAL_SPLIT, upper, scrollpane);
		JSplitPane top = new JSplitPane(JSplitPane.VERTICAL_SPLIT, title, middle);
		this.add(top);
		
	}
}
