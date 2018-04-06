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
				if(enrollment.next())
				{
					enrolled = enrollment.getInt("course_id") == course;	//check if that student exists and the course matches
				}
			} catch (SQLException e) { e.printStackTrace(); }
			return enrolled;
	}
	
}
