package dbManagers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import server.Assignments;
import server.Submissions;

public class SubmissionManager  extends Manager
{
	private final String tableName = "submissiontable";
	
	public SubmissionManager()
	{
		super();
	}
	
	public ArrayList<Submissions> getSubmissions(int assignID)
	{
		String sql = "SELECT * FROM " + tableName + " WHERE assign_id like" + "'" + assignID + "%'";
		ResultSet submissionData;
		ArrayList<Submissions> submissions = new ArrayList<Submissions>();
		try
		{
			statement = connection.prepareStatement(sql);
			submissionData = statement.executeQuery();
			while(submissionData.next())
			{
				submissions.add(new Submissions(
								submissionData.getString("path"),
								submissionData.getInt("student_id"),
								submissionData.getString("title")));
			}
		} catch (SQLException e) {  }
		catch (NullPointerException e)
		{
			return null;
		}
		return submissions;
	}
	
	public void addItem (int assignment, String title, int student)
	{
		String sql = "INSERT IGNORE INTO " + tableName + "(assign_id, student_id, title)" +
				 " VALUES ('" + assignment + "', '" + 
			 		student + "', '" +  
			 		title + "');";
	try{
		statement = connection.prepareStatement(sql);
		statement.executeUpdate();
	}
	catch(SQLException e)
	{
		e.printStackTrace();
	}
	
	}//end of addItem
	
	public int recentID()
	{
		String sql = "SELECT MAX(id) AS largest FROM " + tableName;
		ResultSet submission;
		int s = -1;
		try
		{
			statement = connection.prepareStatement(sql);
			submission = statement.executeQuery();
			if(submission.next())
			{
				s = submission.getInt("largest");
			}
		} catch (SQLException e) { e.printStackTrace(); }
		return s;
	}	
	
	public void addPath(int subID, String line)
	{
		try 
		{ 		
			System.out.println(line);
			String sql = ("UPDATE " + tableName
					+ " SET path = '" + line + "' WHERE ID = " + subID + ";"); 		
			statement = connection.prepareStatement(sql);
			statement.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
	}
	
	
}//end of class
