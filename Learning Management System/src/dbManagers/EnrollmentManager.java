package dbManagers;

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
}
