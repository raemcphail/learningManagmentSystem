package dbManagers;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CourseManager  extends Manager
{
	private final String tableName = "coursetable";
	public CourseManager()
	{
		super();
	}
	
	public void addItem (String prof, String name, String active)
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
	
	public String findCourseProf(int id)
	{
		String sql = "SELECT prof_id FROM " + tableName + " WHERE ID=" + id;
		ResultSet course;
		String s = null;
		try
		{
			statement = connection.prepareStatement(sql);
			course = statement.executeQuery();
			if(course.next())
			{
				s = course.getString("prof_id");
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
	
	public boolean findCourseActive(int id)
	{
		String sql = "SELECT active FROM " + tableName + " WHERE ID=" + id;
		ResultSet course;
		boolean s;
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
}
 
