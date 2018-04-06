package dbManagers;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EnrollmentManager extends Manager {
	private final String tableName = "studentenrollmenttable";
	public EnrollmentManager()
	{
		super();
	}
	
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
