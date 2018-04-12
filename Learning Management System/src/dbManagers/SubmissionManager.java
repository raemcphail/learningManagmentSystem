package dbManagers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import server.Assignments;
import server.Submissions;
/**
 * SubbmissionManager is a sub class which extends Manager,
 * it is responsible for adding, checking, and updating
 * the SubmissionTable
 * @author Louis, Raemc
 * @version 1.0
 * @since April 2, 2018
 */
public class SubmissionManager  extends Manager
{
	private final String tableName = "submissiontable";
	
	public SubmissionManager()
	{
		super();
	}
	/**
	 * getSubmission array where assignID matches
	 * @param assignID
	 * @return
	 */
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
								submissionData.getString("title"),
								submissionData.getString("timestamp")));
			}
		} catch (SQLException e) {  }
		catch (NullPointerException e)
		{
			return null;
		}
		return submissions;
	}
	/**
	 * insert an item with the below parameters, and generate an id
	 * @param assignment
	 * @param title
	 * @param student
	 */
	public void addItem (int assignment, String title, int student)
	{
		LocalDateTime obj = LocalDateTime.now();
		obj =  LocalDateTime.of(obj.getYear(), obj.getMonth(), obj.getDayOfMonth(), obj.getHour(), obj.getMinute(), obj.getSecond());
		String s = obj.toString();
		//s = s.replace("-", "");
		//s = s.replace(":", "");
		System.out.println(s);
		String sql = "INSERT IGNORE INTO " + tableName + "(assign_id, student_id, title, timestamp)" +
				 " VALUES ('" + assignment + "', '" + 
			 		student + "', '" +  
			 		title + "', '"+
			 		s + "');";
	try{
		statement = connection.prepareStatement(sql);
		statement.executeUpdate();
	}
	catch(SQLException e)
	{
		e.printStackTrace();
	}
	
	}//end of addItem
	/**
	 * update grade when given the student_ID and timeStamp, with the grade and comment
	 * @param assignGrade
	 * @param comment
	 * @param timeStamp
	 * @param student_ID
	 */
	public void UpdateGrade (int submissionGrade, String comment , String timeStamp, int student_ID)
	{
		String sql = ("UPDATE " + tableName
				+ " SET submission_grade= " + submissionGrade + ", comments= '" + comment
				+ "' WHERE student_id= " + student_ID + " AND timestamp= " + "'" + timeStamp + "'" + ";");
	try{
		statement = connection.prepareStatement(sql);
		statement.executeUpdate();
	}
	catch(SQLException e)
	{
		e.printStackTrace();
	}
	
	}//end of addItem
	/**
	 * get the largest id
	 * @return
	 */
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
	/**
	 * return the assignment id, given a matching student_id and timestamp
	 * @param studentID
	 * @param time
	 * @return
	 */
	public int getAssignID(int studentID, String time)
	{
		String sql = "SELECT assign_id FROM " + tableName + " WHERE student_id=" + studentID + " AND timestamp like '" + time + "';";
		ResultSet submission;
		int s = -1;
		try
		{
			statement = connection.prepareStatement(sql);
			submission = statement.executeQuery();
			if(submission.next())
			{
				s = submission.getInt("assign_id");
				System.out.println(s);
			}
		} catch (SQLException e) { e.printStackTrace(); }
		return s;
	}
	/**
	 * return the string path, given a matching student_id and timestamp
	 * @param studentID
	 * @param time
	 * @return
	 */
	public String getPath (int studentID, String time)
	{
		String sql = "SELECT path FROM " + tableName + " WHERE student_id=" + studentID + " AND timestamp= '" + time + "'";
		ResultSet submission;
		String s = new String();
		try
		{
			statement = connection.prepareStatement(sql);
			submission = statement.executeQuery();
			if(submission.next())
			{
				s = submission.getString("path");
				System.out.println(s);
			}
		} catch (SQLException e) { e.printStackTrace(); }
		return s;
	}
	/**
	 * update path given table id, with line parameter
	 * @param subID
	 * @param line
	 */
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
