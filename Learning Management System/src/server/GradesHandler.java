package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import dbManagers.AssignmentManager;
import dbManagers.CourseManager;
import dbManagers.GradesManager;
import dbManagers.SubmissionManager;

/**
 * handler responsible for assignment/database related actions, including:
 * adding an assignment to database,
 * @author louis rae
 * @version 1.0
 * @since April 11, 2018
 */
public class GradesHandler {
	
	/**
	 * objectoutputstream to send objects
	 */
	ObjectOutputStream out = null;
	/**
	 * objectinputstream to receive objects
	 */
	ObjectInputStream in = null;
	
	GradesHandler(ObjectInputStream in, ObjectOutputStream out)
	{
		this.out = out;
		this.in = in;
	}
	
	/**
	 * method that sends an ArrayList of grades over the socket for the student's 
	 * grade panel to display
	 */
	public void getGrades()
	{
		GradesManager g = new GradesManager();
		try {
			int studentID = (int)in.readObject();
			ArrayList<Integer> assignIDs = g.getAssignIDs(studentID);
			ArrayList<Integer> grades = g.getGrades(studentID);
			AssignmentManager a = new AssignmentManager();
			ArrayList<String> names = a.getNames(assignIDs);
			out.writeObject(names);
			out.writeObject(grades);
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * method that updates database with the grade and comment the prof assigned a submission
	 */
	public void updateSubGrade()
	{
		try {
			int StudentID = (int)in.readObject();
			String timeStamp = (String)in.readObject();
			int SubGrade = (int)in.readObject();
			String comment = (String)in.readObject();
			Course course = (Course)in.readObject();
			
			SubmissionManager subDB = new SubmissionManager();
			subDB.UpdateGrade(SubGrade, comment, timeStamp, StudentID);	//update the grade in the dataTable
			GradesManager gradeDB = new GradesManager();
			CourseManager c = new CourseManager();
			int courseID = c.findCourseID(course.name);
			int assignID = subDB.getAssignID(StudentID, timeStamp);
			gradeDB.UpdateGrades(assignID, StudentID, courseID, SubGrade);
			
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
	}
}
