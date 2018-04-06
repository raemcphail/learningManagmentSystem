package frontEnd;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;

import server.Student;

public class StudentListener implements ActionListener{

	StudentPanel studentPanel;
	ObjectInputStream in = null;
	ObjectOutputStream out = null;

	public StudentListener(StudentPanel student, ObjectInputStream in, ObjectOutputStream out) 
	{
		studentPanel = student;
		this.in = in;
		this.out = out;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void actionPerformed(ActionEvent obj) {
		studentPanel.list.clear();
		System.out.println("pressed search");
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
