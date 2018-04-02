package dbManagers;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;

public class Manager {
	protected final String username = "root";
	protected String password = "ensf409";
	protected Connection connection;
	protected String dbName = "mydb";
	protected Statement statement;
	
	public Manager()
	{
		try {
			Driver driver = new com.mysql.jdbc.Driver();
			DriverManager.registerDriver(driver);
			
			connection = DriverManager.getConnection("jdbc:mysql://localhost/mydb",
					username, password);
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
	
		
	}
	
}
