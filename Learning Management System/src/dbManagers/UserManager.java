package dbManagers;

import java.sql.ResultSet;
import java.sql.SQLException;

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
