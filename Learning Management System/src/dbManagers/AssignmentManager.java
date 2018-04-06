package dbManagers;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AssignmentManager extends Manager
{
	private final String tableName = "assignmenttable";
	public AssignmentManager()
	{
		super();
	}
	
	public void addPath(int assignID, String line)
	{
		try 
		{ 		
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
	
	public String findPath(int id)
	{
		String sql = "SELECT path FROM " + tableName + " WHERE ID=" + id;
		ResultSet assign;
		String s = null;
		try
		{
			statement = connection.prepareStatement(sql);
			assign = statement.executeQuery();
			if(assign.next())
			{
				s = assign.getString("path");
			}
		} catch (SQLException e) { e.printStackTrace(); }
		return s;
	}
	
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
		
		
	}

