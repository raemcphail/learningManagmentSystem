package server;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * This class represents all the data for a submission, each submission can be graded by
 * the professor and the grade can be viewed by the student.
 * @author louis rae
 * @version 1.0
 * @since April 11, 2018
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
	/**
	 * the student id of the student who made the submission
	 */
	int student_id;
	/**
	 * name of the file the student sbumitted
	 */
	String title;
	/**
	 * the time the submission was made
	 */
	String timestamp;
	public Submissions (String p, int student, String title, String time)
	{
		grade = -1;
		path = p;
		student_id = student;
		this.title = title;
		timestamp = time;
		
	}
	
	public Submissions (String p, int student, String t, int g)
	{
		path = p;
		student_id = student;
		title = t;
		grade = g;
		
	}
	
	/**
	 * method that returns student id and timestamp as a String
	 */
	@Override
	public String toString()
	{
		String s = Integer.toString(student_id) + " " + timestamp;
		return s;
	}
}
