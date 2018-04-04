package server;

import java.util.ArrayList;
/**
 * This is a class to represent all the data for a course that the student is 
 * enrolled in or the professor has created
 * @author raemc
 *
 */
public class Course 
{
	/**
	 * The field to keep track of assignments in the course
	 * is an ArrayList so the number of assignments can vary from course to course
	 */
	ArrayList <Assignments> assignments;
	
	public Course ()
	{
		assignments = new <Assignments> ArrayList();
	}
}
