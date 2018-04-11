package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import dbManagers.AssignmentManager;
import dbManagers.CourseManager;
import dbManagers.GradesManager;
import dbManagers.SubmissionManager;

public class GradesHandler {
	ObjectOutputStream out = null;
	ObjectInputStream in = null;
	
	GradesHandler(ObjectInputStream in, ObjectOutputStream out)
	{
		this.out = out;
		this.in = in;
	}
	
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
	
	public void updateSubGrade()
	{
		/*out.writeObject("updateGrade");	//send opCode
						out.writeObject(studentID);	//sends the id as an int
						out.writeObject(timeStamp);	//sends the timeStamp as a String
						out.writeObject(Integer.parseInt(gradeField.getText())); //sends the assignment grade
						out.writeObject(commentArea.getText());	//send the comment*/
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