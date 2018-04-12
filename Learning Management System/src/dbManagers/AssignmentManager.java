package dbManagers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import server.Assignments;
import server.Student;
/**
 * AssignmentManager is a sub class which extends Manager,
 * it is responsible for adding, checking, and updating
 * the AssignmentTable
 * @author Louis, Raemc
 * @version 1.0
 * @since April 2, 2018
 */
public class AssignmentManager extends Manager
{
	/**
	 * the table name
	 */
	private final String tableName = "assignmenttable";
	public AssignmentManager()
	{
		super();
	}
	/**
	 * updates the path within the assignment table
	 * @param assignID
	 * @param line
	 */
	public void addPath(int assignID, String line)
	{
		try 
		{ 		
			System.out.println(line);
			String sql = ("UPDATE " + tableName
					+ " SET path = '" + line + "' WHERE ID = " + assignID + ";"); 		
			statement = connection.prepareStatement(sql);
			statement.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
	}
	/**
	 * retrieves the path at the given table id
	 * @param id - primary id of the table
	 * @return - the path as a String
	 */
	public String getPath(int id)
	{
		String sql = "SELECT path FROM " + tableName + " WHERE ID=" + id;
		ResultSet assignments;
		String s = new String();
		try
		{
			statement = connection.prepareStatement(sql);
			assignments = statement.executeQuery();
			if(assignments.next())
			{
				s = assignments.getString("path");
			}
		} catch (SQLException e) { e.printStackTrace(); }
		return s;
	}
	/**
	 * update the value of the active bit at the given id
	 * @param value - either 1 or 0
	 * @param AssignID - id of the table
	 */
	public void changeActive(int value, int AssignID)
	{
		try 
		{ 		
			String sql = "UPDATE " + tableName
					+ " SET ACTIVE = " + value + " WHERE id=" + AssignID; 		
			statement = connection.prepareStatement(sql);
			statement.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	
	}
	/**
	 * add the below parameters to the table and generate an id
	 * @param course
	 * @param title
	 * @param active
	 */
	public void addItem (int course, String title, boolean active)
	{
		String sql = "INSERT IGNORE INTO " + tableName + "(course_id, title, active)" +
				 " VALUES ('" + course + "', '" + 
			 		title + "', '" +  
			 		active + "');";
	try{
		statement = connection.prepareStatement(sql);
		statement.executeUpdate();
	}
	catch(SQLException e)
	{
		e.printStackTrace();
	}
	
 }
	/**
	 * return true if there is a matching course and student id,
	 * in the table. false otherwise
	 * @param student
	 * @param course
	 * @return
	 */
	public boolean checkEnrollment (int student, int course)
	{
			String sql = "SELECT * FROM " + tableName + " WHERE student_id=" + student;
			ResultSet enrollment;
			boolean enrolled = false;
			try
			{
				statement = connection.prepareStatement(sql);
				enrollment = statement.executeQuery();
				while(enrollment.next())
				{
					enrolled = enrollment.getInt("course_id") == course;	//check if that student exists and the course matches
					if (enrolled)
						return enrolled;
				}
			} catch (SQLException e) { e.printStackTrace(); }
			return enrolled;
	}
	/**
	 * returns the active value of the user at that id
	 * @param i - the table id
	 * @return
	 */
	public boolean isActive (int i)
	{
		String sql = "SELECT * FROM " + tableName + " WHERE id like" + "'" + i + "%'";
		ResultSet assignment;
		try
		{
			statement = connection.prepareStatement(sql);
			assignment = statement.executeQuery();
			if (assignment.next())
			{
				return assignment.getBoolean("active");
			} 
		}	catch (SQLException e) { e.printStackTrace(); }
		return false;
	}
	/**
	 * retrieve the assignment id from the assignment title and courseid
	 * @param title
	 * @param courseID
	 * @return
	 */
	public int GETAssignID (String title, int courseID)
	{
		String sql = "SELECT * FROM " + tableName + " WHERE title like" + "'" + title + "%' AND course_id like " + 
					 "'" + courseID + "%'";
		ResultSet enrollment;
		int s = -1;
		try
		{
			statement = connection.prepareStatement(sql);
			enrollment = statement.executeQuery();
			if (enrollment.next())
			{
				s = enrollment.getInt("id");
			} 
		}	catch (SQLException e) { e.printStackTrace(); }
		System.out.println(s);
		return s;
	}
	/**
	 * get the most recent table id
	 * @return
	 */
	public int recentID()
	{
		String sql = "SELECT MAX(id) AS largest FROM " + tableName;
			ResultSet assignment;
			int s = -1;
			try
			{
				statement = connection.prepareStatement(sql);
				assignment = statement.executeQuery();
				if(assignment.next())
				{
					s = assignment.getInt("largest");
				}
			} catch (SQLException e) { e.printStackTrace(); }
			return s;
	}
	/**
	 * create and return all assignment objects based on data in table at
	 * the given course_ID
	 * @param Course_ID
	 * @return
	 */
	public ArrayList<Assignments> getAssignments(int Course_ID)
	{
		String sql = "SELECT * FROM " + tableName + " WHERE course_id like" + "'" + Course_ID + "%'";
		ResultSet assignmentData;
		ArrayList<Assignments> assignments = new ArrayList<Assignments>();
		try {
			statement = connection.prepareStatement(sql);
			assignmentData = statement.executeQuery();
			while(assignmentData.next())
			{
				assignments.add(new Assignments(
						null, 
						assignmentData.getString("path"),
						assignmentData.getString("title")));
			}
		} catch (SQLException e) {  }
		catch (NullPointerException e)
		{
			return null;
		}
		return assignments;
	}
	/**
	 * return an arraylist of assignment titles at the corresponding id values
	 * @param ids - arraylist of table id's
	 * @return
	 */
	public ArrayList<String> getNames (ArrayList<Integer> ids)
	{
		Iterator it = ids.iterator();
		ArrayList<String> names = new ArrayList<String>();
		while(it.hasNext())
		{
			String sql = "SELECT * FROM " + tableName + " WHERE id="  + it.next();
			ResultSet assignmentData;
			try 
			{
				statement = connection.prepareStatement(sql);
				assignmentData = statement.executeQuery();
				if(assignmentData.next())
				{
					names.add(assignmentData.getString("title"));
				}
			}catch (SQLException e) {  }
		}
		return names;
	}
	/**
	 * return an arraylist of active assignments, for that given course_ID
	 * @param Course_ID
	 * @return
	 */
	public ArrayList<Assignments> getActiveAssignments(int Course_ID)
	{
		String sql = "SELECT * FROM " + tableName + " WHERE course_id like" + "'" + Course_ID + "%'";
		ResultSet assignmentData;
		ArrayList<Assignments> assignments = new ArrayList<Assignments>();
		try {
			statement = connection.prepareStatement(sql);
			assignmentData = statement.executeQuery();
			while(assignmentData.next())
			{
				if(assignmentData.getBoolean("active"))
				{	
				assignments.add(new Assignments(
						null, 
						assignmentData.getString("path"),
						assignmentData.getString("title")));
				}
			}
		} catch (SQLException e) {  }
		catch (NullPointerException e)
		{
			return null;
		}
		return assignments;
	}
}

