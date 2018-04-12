package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import dbManagers.CourseManager;
import dbManagers.EnrollmentManager;
import dbManagers.UserManager;

/**
 * handler responsible for student/database related actions
 * @author louis rae
 * @version 1.0
 * @since April 11, 2018
 */
public class StudentHandler {

	/**
	 * objectoutputstream to send objects
	 */
	ObjectOutputStream out = null;
	/**
	 * objectinputstream to receive objects
	 */
	ObjectInputStream in = null;
	
		public StudentHandler(ObjectOutputStream out, ObjectInputStream in)
		{
			this.out = out;
			this.in = in;
		}

		/**
		 * method that searches database for students and sends an arraylist of the students 
		 * found back over the socket to the GUI
		 */
		public void runHandler()
		{
			try {
				String query = (String)in.readObject();
				UserManager userDB = new UserManager();
				 if (userDB.getStudents(query).size() != 0)		//check if the query is a matching lastname
				{
					ArrayList<Student> students = userDB.getStudents(query);
					out.writeObject(students);
				}
				else if (userDB.getStudents(Integer.parseInt(query)).size() != 0)	//check if the query is a matching id
				{
					ArrayList<Student> theStudent = userDB.getStudents(Integer.parseInt(query));
					out.writeObject(theStudent);
				} else
				{
					ArrayList<Student> empty = new ArrayList<Student>();
					out.writeObject(empty); //if the query is not found
					JOptionPane.showMessageDialog(null, "search not found", "Not Found", JOptionPane.INFORMATION_MESSAGE);
				}
				 
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			} catch (NumberFormatException e)
			{
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
