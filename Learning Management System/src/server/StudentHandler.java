package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import dbManagers.CourseManager;
import dbManagers.EnrollmentManager;
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
				 
				 if (in.readObject().equals("selected"))	//if the user selected a label
				 {
					 System.out.println("still in student handler!");
					 int userID = (int)in.readObject();
					 String courseName = (String)in.readObject();
					 CourseManager courseDB = new CourseManager();
					 EnrollmentManager enrollDB = new EnrollmentManager();
					 int courseID =  courseDB.getCourseID(courseName);
					 boolean enrolled = enrollDB.checkEnrollment(userID, courseID);
					 if (enrolled)
					 {
						int choice = JOptionPane.showConfirmDialog(null, "This student is ENROLLED.\n Do you want to unenroll them?", courseName, JOptionPane.YES_NO_OPTION);
						if (choice == JOptionPane.YES_OPTION)
						{
							System.out.println("WOW");
						}
						else
						{
							
						}
						
					 }
					 else	//student is NOT enrolled
					 {
						int choice = JOptionPane.showConfirmDialog(null, "This student is NOT ENROLLED.\n Do you want to enroll them?", courseName, JOptionPane.YES_NO_OPTION);
						if (choice == JOptionPane.YES_OPTION)
						{
							enrollDB.addItem(userID, courseID);
						}
						else
						{
							
						}
					 }
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
