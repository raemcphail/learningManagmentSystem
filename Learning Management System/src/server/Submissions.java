package server;

import java.io.Serializable;

/**
 * This class represents all the data for a submission, each submission can be graded by
 * the professor and the grade can be viewed by the student.
 */
public class Submissions implements Serializable
{
	/**
	 * The field to keep track of the grade as a percentage that the submission recieved or
	 * -1 if it has not been graded yet
	 */
	int grade;
	/**
	 * This field represents the path where the submission is saved. When a submission is 
	 * uploaded it is sent to the server's machine, there it will be downloaded onto the server
	 * machine. The path keeps track of where it is downloaded, so it can be retrieved when a
	 * professor requests to view that submission the server can send that file to the professor.
	 */
	String path;
	
	int student_id;
	String title;
	public Submissions (String p, int student, String t)
	{
		grade = -1;
		path = p;
		student_id = student;
		title = t;
		
	}
	
	public Submissions (String p, int student, String t, int g)
	{
		path = p;
		student_id = student;
		title = t;
		grade = g;
		
	}
}
