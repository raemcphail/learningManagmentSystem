package dbManagers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import server.Course;
import server.Student;
import server.User;

public class UserManager extends Manager {
	private final String tableName = "usertable";
	/**
	* Returns a string containing the password of the client
	* corresponding to that ID, to display in the search results.
	*/
	
	/**
	 * default constructor connects to the database using the code in Managers		
	 */
	public UserManager()
	{
		super();
	}
	
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
	/**
	********* NOT USED- TO BE COPIED TO OTHER MANAGERS****
	* adds the user to the table.
	* @param - user to add to the dataBase.
	
	public void addItem(User client)
	{
		System.out.println(client.getPhoneNumber());
		String sql = "INSERT IGNORE INTO " + tableName + "(id, password, email, , PHONENUMBER, CLIENTTYPE)" +
					 " VALUES ('" + client.getFirstName() + "', '" + 
					 		client.getLastName() + "', '" + 
					 		client.getAddress() + "', '" + 
					 		client.getPostalCode() + "', '" + 
					 		client.getPhoneNumber() + "', '" + 
					 		client.getClientType() + "');";
		
		try{
			statement = connection.prepareStatement(sql);
			statement.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}*/
	
}
