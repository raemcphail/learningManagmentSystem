package server;

import java.io.Serializable;
import java.util.ArrayList;
/**
 * This is a class to represent all the data for a course that the student is 
 * enrolled in or the professor has created
 * @author louis rae
 * @version 1.0
 * @since April 11, 2018
 *
 */
public class Course implements Serializable
{
	/**
	 * The field to keep track of assignments in the course
	 * is an ArrayList so the number of assignments can vary from course to course
	 */
	ArrayList <Assignments> assignments;
	/**
	 * The course name and number together
	 */
	public String name;
	/**
	 * The course is active
	 */
	boolean active;
	/**
	 * The prof ID
	 */
	int prof_ID;
	
	public Course (String name, boolean active, int prof_id)
	{
		this.name = name;
		this.active = active;
		this.prof_ID = prof_id;
		assignments = new ArrayList<Assignments>();
	}
	
	/**
	 * method that returns course name
	 */
	@Override
	public String toString()
	{
		return this.name;
	}
	
	/**
	 * method that makes active courses inactive
	 * and makes inactive course active when called
	 */
	public void toggleActive()
	{
		if (active)
		{
			active = false;
		}
		else
		{
			active = true;
		}
	}
	
	
	public boolean getActive()
	{
		return active;
	}
	
	
	public void setAssignments(ArrayList<Assignments> assignments)
	{
		this.assignments = assignments;
	}
}
