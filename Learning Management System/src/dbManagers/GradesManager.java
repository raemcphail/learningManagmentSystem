package dbManagers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import server.Assignments;
import server.Student;

//GradesManager contains id, assign_id, student_id, course_id, assignment_grade. All int
public class GradesManager extends Manager {
	 
	 private final String tableName = "gradetable";
	 public GradesManager()
	 {
	 	super();
	 }
	 
	public void addItem (int assign_id, int student_id, int course_id, int assignment_grade)
	{
		String sql = "INSERT IGNORE INTO " + tableName + "(assign_id, student_id, course_id, assignment_grade)" +
				 " VALUES ('" + assign_id + "', '" + 
				 student_id + "', '" + 
			 	course_id + "', '" +
			 	assignment_grade + "');";
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
	 * with the student id, course_id, and assign_id, return a grade associated with those values
	 * @return - returns -1 if no matching student id, course number
	 */
	public int getGrade (int grade_id)
	{
		String sql = "SELECT assignment_grade FROM " + tableName + " WHERE ID=" + grade_id;
		ResultSet grade;
		int s = -1;
		try
		{
			statement = connection.prepareStatement(sql);
			grade = statement.executeQuery();
			if(grade.next())
			{
				s = grade.getInt("assignment_grade");
			}
		} catch (SQLException e) { e.printStackTrace(); }		
		
		return s;
	}
	
	gradeDB.UpdateGrades(assignID, StudentID, courseID, SubGrade);
	
	/**
	 * with the student id, course_id, and assign_id, return a grade_id associated with those values
	 * @return - returns -1 if no matching student id or course number
	 */
	public int getGradeID (int student_id, int course_id, int assign_id)
	{
		String sql = "SELECT * FROM " + tableName + " WHERE student_id like" + "'" + student_id + "%'";
		ResultSet grade;
		int s = -1;
		try
		{
			statement = connection.prepareStatement(sql);
			grade = statement.executeQuery();
			while(grade.next())
			{
				if (grade.getInt("course_id") == course_id && grade.getInt("assign_id") == assign_id)
				{
					s = grade.getInt("id");
				}
			}
		} catch (SQLException e) { e.printStackTrace(); }
		
		return s;
	}
	 
}
