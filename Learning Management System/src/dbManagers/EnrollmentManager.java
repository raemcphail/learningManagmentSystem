package dbManagers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import server.Course;
/**
 * EnrollmentManager is a sub class which extends Manager,
 * it is responsible for adding, checking, and updating
 * the StudentEnrollmentTable
 * @author Louis, Raemc
 * @version 1.0
 * @since April 2, 2018
 */
public class EnrollmentManager extends Manager {
	/**
	 * the table name
	 */
	private final String tableName = "studentenrollmenttable";
	public EnrollmentManager()
	{
		super();
	}
	/**
	 * add the below parameter to the table and generate an id
	 * @param student
	 * @param course
	 */
	public void addItem (int student, int course)
	{
		String sql = "INSERT IGNORE INTO " + tableName + "(student_id, course_id)" +
				 " VALUES ('" + student + "', '" + 
				 course + "');";
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
	 * return if that student is enrolled based on the student_id and the course_id exist
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
	 * delete the table for the given id
	 * @param ID
	 */
	public void removeItem(int ID)
	{
		try
		{
		String sql = "DELETE FROM " + tableName + " WHERE id=" + ID;
		statement = connection.prepareStatement(sql);
		statement.executeUpdate();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	/**
	 * return a list of courseID's based on the given user_id
	 * @param User_ID
	 * @return
	 */
	public ArrayList<Integer> getCourseID(int User_ID)
	{
		String sql = "SELECT * FROM " + tableName + " WHERE student_id=" + "'" + User_ID + "'";
		ResultSet enrollment;
		ArrayList<Integer> courseID = new ArrayList<Integer>();
		try {
			statement = connection.prepareStatement(sql);
			enrollment = statement.executeQuery();
			while(enrollment.next())
			{
				courseID.add(enrollment.getInt("course_id"));
			}
		} catch (SQLException e) {  }
		catch (NullPointerException e)
		{
			return null;
		}
		
		return courseID;
	}
	
	/**
	 * get the enrollment id if a userID and courseID exist in a row
	 * @param User_ID
	 * @param course_ID
	 * @return the enrollment id
	 */
	public int getEnrollID(int User_ID, int course_ID)
	{
		String sql = "SELECT * FROM " + tableName + " WHERE student_id like" + "'" + User_ID + "%'";
		ResultSet course;
		int s = -1;
		try
		{
			statement = connection.prepareStatement(sql);
			course = statement.executeQuery();
			while(course.next())
			{
				if (course.getInt("course_id") == course_ID)
				{
					s = course.getInt("id");
				}
			}
		} catch (SQLException e) { e.printStackTrace(); }
		catch (NullPointerException e)
		{
			return -1;
		}
		return s;
	}
}
