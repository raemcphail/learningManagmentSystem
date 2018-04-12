package dbManagers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import server.Course;
import server.Student;
import server.User;
/**
 * UserManager is a sub class which extends Manager,
 * it is responsible for adding, checking, and updating
 * the UserTable
 * @author Louis, Raemc
 * @version 1.0
 * @since April 2, 2018
 */
public class UserManager extends Manager {
	/**
	 * the table name
	 */
	private final String tableName = "usertable";

	/**
	 * default constructor connects to the database using the code in Managers		
	 */
	public UserManager()
	{
		super();
	}
	/**
	 * returns the client password given the table id
	 * @param UserID
	 * @return
	 */
	public String findClientPassword(int UserID)
	{
		String sql = "SELECT password FROM " + tableName + " WHERE ID=" + UserID;
		ResultSet client;
		String s = null;
		try {
			statement = connection.prepareStatement(sql);
			client = statement.executeQuery();
			if(client.next())
			{
				s = client.getString("password");
			}
		} catch (SQLException e) { e.printStackTrace(); }
		return s;
	}
	/**
	 * returns the student with the user_id, in the form of an ArrayList
	 * @param User_ID
	 * @return
	 */
	public ArrayList<Student> getStudents(int User_ID)
	{
		String sql = "SELECT * FROM " + tableName + " WHERE id=" + User_ID;
		ResultSet users;
		ArrayList<Student> students = new ArrayList<Student>();
		try {
			statement = connection.prepareStatement(sql);
			users = statement.executeQuery();
			while(users.next())
			{
				if (users.getString("type").charAt(0) == 'P')	//if the User is a professor, abort
				{
					continue;
				}	//int id, String password, String email,String first, String last, char type)
				students.add(new Student(
						User_ID, 
						users.getString("password"), 
						users.getString("email"),
						users.getString("firstname"),
						users.getString("lastname"),
						users.getString("type").charAt(0)));
			}
		} catch (SQLException e) {  }
		catch (NullPointerException e)
		{
			return null;
		}
		return students;
	}
	/**
	 * returns the students with the same lastname, in the form of an ArrayList
	 * @param last
	 * @return
	 */
	public ArrayList<Student> getStudents(String last)
	{
		String sql = "SELECT * FROM " + tableName + " WHERE lastname like" + "'" + last + "%'";
		ResultSet users;
		ArrayList<Student> students = new ArrayList<Student>();
		try {
			statement = connection.prepareStatement(sql);
			users = statement.executeQuery();
			while(users.next())
			{
				if (users.getString("type").charAt(0) == 'P')	//ignore the professors
				{
					continue;
				}	//int id, String password, String email,String first, String last, char type)
				students.add(new Student(
						users.getInt("id"), 
						users.getString("password"), 
						users.getString("email"),
						users.getString("firstname"),
						users.getString("lastname"),
						users.getString("type").charAt(0)));
			}
		} catch (SQLException e) {  }
		catch (NullPointerException e)
		{
			return null;
		}
		return students;
	}
	/**
	 * retrieves the client email, given the userID
	 * @param UserID
	 * @return
	 */
	public String findClientEmail(int UserID)
	{
		String sql = "SELECT email FROM " + tableName + " WHERE ID=" + UserID;
		ResultSet client;
		String s = null;
		try {
			statement = connection.prepareStatement(sql);
			client = statement.executeQuery();
			if (client.next())
			{
				s = client.getString("email");
			}
		} catch (SQLException e) { e.printStackTrace(); }
		return s;
	}
	/**
	 * retrieves the users firstname, given the table id
	 * @param UserID
	 * @return
	 */
	public String findClientFirstname(int UserID)
	{
		String sql = "SELECT firstname FROM " + tableName + " WHERE ID=" + UserID;
		ResultSet client;
		String s = null;
		try {
			statement = connection.prepareStatement(sql);
			client = statement.executeQuery();
			if (client.next())
			{
				s = client.getString("firstname");
			}
		} catch (SQLException e) { e.printStackTrace(); }
		return s;
	}
	/**
	 * retrieves the users lastname, given the table id
	 * @param UserID
	 * @return
	 */
	public String findClientLastname(int UserID)
	{
		String sql = "SELECT lastname FROM " + tableName + " WHERE ID=" + UserID;
		ResultSet client;
		String s = null;
		try {
			statement = connection.prepareStatement(sql);
			client = statement.executeQuery();
			if (client.next())
			{
				s = client.getString("lastname");
			}
		} catch (SQLException e) { e.printStackTrace(); }
		return s;
	}
	/**
	 * returns the client type, P or S, given the table id
	 * @param UserID
	 * @return
	 */
	public char[] findClientType(int UserID)
	{
		String sql = "SELECT type FROM " + tableName + " WHERE ID=" + UserID;
		ResultSet client;
		char s [] = null;
		try {
			statement = connection.prepareStatement(sql);
			client = statement.executeQuery();
			if (client.next())
			{
				s = client.getString("type").toCharArray();
			}
		} catch (SQLException e) { e.printStackTrace(); }
		return s;
	}
	
}
