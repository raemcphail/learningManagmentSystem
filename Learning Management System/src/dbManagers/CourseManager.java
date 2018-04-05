package dbManagers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import server.Course;

public class CourseManager  extends Manager
{
	private final String tableName = "coursetable";
	public CourseManager()
	{
		super();
	}
	
	public void addItem (int prof, String name, boolean active)
	{
		String sql = "INSERT IGNORE INTO " + tableName + "(prof_id, name, active)" +
				 " VALUES ('" + prof + "', '" + 
			 		name + "', '" + 
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
	
	public int findCourseProf(int id)
	{
		String sql = "SELECT prof_id FROM " + tableName + " WHERE ID=" + id;
		ResultSet course;
		int s = -1;
		try
		{
			statement = connection.prepareStatement(sql);
			course = statement.executeQuery();
			if(course.next())
			{
				s = course.getInt("prof_id");
			}
		} catch (SQLException e) { e.printStackTrace(); }
		return s;
	}
	
	public String findCourseName(int id)
	{
		String sql = "SELECT name FROM " + tableName + " WHERE ID=" + id;
		ResultSet course;
		String s = null;
		try
		{
			statement = connection.prepareStatement(sql);
			course = statement.executeQuery();
			if(course.next())
			{
				s = course.getString("name");
			}
		} catch (SQLException e) { e.printStackTrace(); }
		return s;
	}
	public String findCourseName(String Possiblename)
	{
		String sql = "SELECT * FROM " + tableName + " WHERE name like" + "'" + Possiblename + "%'";
		ResultSet course;
		String s = null;
		try
		{
			statement = connection.prepareStatement(sql);
			course = statement.executeQuery();
			if(course.next())
			{
				s = course.getString("name");
			}
		} catch (SQLException e) { e.printStackTrace(); }
		catch (NullPointerException e)
		{
			return null;
		}
		return s;
	}
	
	public boolean findCourseActive(int id)
	{
		String sql = "SELECT active FROM " + tableName + " WHERE ID=" + id;
		ResultSet course;
		boolean s = false;
		try
		{
			statement = connection.prepareStatement(sql);
			course = statement.executeQuery();
			if(course.next())
			{
				s = course.getBoolean("active");
			}
		} catch (SQLException e) { e.printStackTrace(); }
		return s;
	}
	
	/**
	 * gets the Courses in the database with the User_ID
	 * @param User_ID
	 * @return - returns a course that is related to that course ID
	 */
	public ArrayList<Course> getUserCourses(int User_ID)
	{
		String sql = "SELECT * FROM " + tableName + " WHERE prof_id=" + "'" + User_ID + "'";
		ResultSet course;
		ArrayList<Course> courses = new ArrayList<Course>();
		try {
			statement = connection.prepareStatement(sql);
			course = statement.executeQuery();
			while(course.next())
			{
				courses.add(new Course(	//String name, boolean active, int prof_id
						course.getString("name"), 
						course.getBoolean("active"), 
						course.getInt("prof_id")));
			}
		} catch (SQLException e) {  }
		catch (NullPointerException e)
		{
			return null;
		}
		return courses;
	}
	
}
 
