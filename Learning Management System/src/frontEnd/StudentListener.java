package frontEnd;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;

import server.Student;
/**
 * Listener to handle when the student search button is pressed
 * @author Louis, Raemc
 * @version 1.1
 * @since April 9, 2018
 */
public class StudentListener implements ActionListener{
	/**
	 * the student panel
	 */
	StudentPanel studentPanel;
	/**
	 * Object IO used to send objects across socket
	 */
	ObjectInputStream in = null;
	/**
	 * Object IO used to send objects across socket
	 */
	ObjectOutputStream out = null;

	public StudentListener(StudentPanel student, ObjectInputStream in, ObjectOutputStream out) 
	{
		studentPanel = student;
		this.in = in;
		this.out = out;
	}
	/**
	 * searches for the student in the database based on the query, and returns the result on the screen
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void actionPerformed(ActionEvent obj) {
		studentPanel.list.clear();
		try {
			out.writeObject("student");
			String query = studentPanel.searchBar.getText();
			if (query != "")	//if there is something to check
			{
				out.writeObject(query);
			}
			try {
				ArrayList<Student> students = (ArrayList<Student>)in.readObject();
				if (students.size() == 0)	//if search failed, do nothing
				{
					return;
				}
				
				Iterator it = students.iterator();
				int i = 0;
				while (it.hasNext())
				{
					studentPanel.list.addElement(it.next().toString());
				}
				
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
