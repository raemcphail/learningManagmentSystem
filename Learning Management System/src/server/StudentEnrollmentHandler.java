package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import dbManagers.CourseManager;
import dbManagers.EnrollmentManager;
import dbManagers.UserManager;

public class StudentEnrollmentHandler {
	ObjectOutputStream out = null;
	ObjectInputStream in = null;
	
		public StudentEnrollmentHandler(ObjectOutputStream out, ObjectInputStream in)
		{
			this.out = out;
			this.in = in;
		}
		
		public void runHandler()
		{
					 int userID;
			try {
					userID = (int)in.readObject();
					
					 String courseName = (String)in.readObject();
					 CourseManager courseDB = new CourseManager();
					 EnrollmentManager enrollDB = new EnrollmentManager();
					 int courseID =  courseDB.findCourseID(courseName);
					 boolean enrolled = enrollDB.checkEnrollment(userID, courseID);
					 if (enrolled)
					 {
						int choice = JOptionPane.showConfirmDialog(null, "This student is ENROLLED.\n Do you want to unenroll them?", courseName, JOptionPane.YES_NO_OPTION);
						if (choice == JOptionPane.YES_OPTION)
						{
							int enrollID = enrollDB.getEnrollID(userID, courseID);
							enrollDB.removeItem(enrollID);
						}
						else
						{
							return;
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
							return;
						}
					 }
		}
		catch (ClassNotFoundException | IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
			}
		}
		
		public void getEnrolledStudents()
		{
			 try {
				String courseName = (String)in.readObject();
				System.out.println(courseName);
				ArrayList<Student> students = new ArrayList<Student>();
				CourseManager courseDB = new CourseManager();
				EnrollmentManager enrollDB = new EnrollmentManager();
				UserManager userDB = new UserManager();
				int courseID =  courseDB.findCourseID(courseName);
				boolean enrolled = enrollDB.checkEnrollment(30000000, courseID);
				if (enrolled)
				{
					students = userDB.getStudents(30000000);
				}
				enrolled = enrollDB.checkEnrollment(30000001, courseID);
				if (enrolled)
				{
					ArrayList<Student> student = userDB.getStudents(30000001);
					students.add(student.get(0));
				}
				System.out.println(students.get(0));
				out.writeObject(students);
				
			} catch (ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
}
