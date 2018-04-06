package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import dbManagers.UserManager;

public class StudentHandler {

	ObjectOutputStream out = null;
	ObjectInputStream in = null;
	
		public StudentHandler(ObjectOutputStream out, ObjectInputStream in)
		{
			this.out = out;
			this.in = in;
		}
		
		public void runHandler()
		{
			try {
				System.out.println("in StudentrunHandler");
				String query = (String)in.readObject();
				UserManager userDB = new UserManager();
				 if (userDB.getStudents(query).size() != 0)		//check if the query is a matching lastname
				{
					 System.out.println("2");
					ArrayList<Student> students = userDB.getStudents(query);
					out.writeObject(students);
				}
				else if (userDB.getStudents(Integer.parseInt(query)).size() != 0)	//check if the query is a matching id
				{
					System.out.println("1");
					ArrayList<Student> theStudent = userDB.getStudents(Integer.parseInt(query));
					out.writeObject(theStudent);
				} else
				{
					System.out.println("3");
					ArrayList<Student> empty = new ArrayList<Student>();
					out.writeObject(empty); //if the query is not found
					JOptionPane.showMessageDialog(null, "search not found", "Not Found", JOptionPane.INFORMATION_MESSAGE);
				}
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			} catch (NumberFormatException e)
			{
				System.out.println("3");
				ArrayList<Student> empty = new ArrayList<Student>();
				try {
					out.writeObject(empty); //if the query is not found
				} catch (IOException e1) {
					e1.printStackTrace();
				}	
				JOptionPane.showMessageDialog(null, "search not found", "Not Found", JOptionPane.INFORMATION_MESSAGE);
			}
		}

}
