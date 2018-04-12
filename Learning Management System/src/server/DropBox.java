package server;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * This class represents a table in the database, each assignment 
 * the professor uploads will have a specific table. The table holds all the information
 * about a submission from each student
 * @author louis rae
 * @version 1.0
 * @since April 11, 2018
 */
public class DropBox implements Serializable
{
	/**
	 * The field to keep track of submissions for that assignment
	 * is an ArrayList so the number of submissions can vary from assignment to assignment
	 */
	ArrayList <Submissions> submissions;
	
	public DropBox()
	{
		submissions = new <Submissions> ArrayList();
	}

}
