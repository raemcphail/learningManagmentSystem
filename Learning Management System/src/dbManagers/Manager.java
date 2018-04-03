package dbManagers;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;

public class Manager {
	protected final String username = "root";
	protected String password = "Xcountry381";	//to be adjusted******
	protected Connection connection;
	protected String dbName = "awesomed2lproject";
	protected PreparedStatement statement;
	
	public Manager()
	{
		try {
			Driver driver = new com.mysql.jdbc.Driver();
			DriverManager.registerDriver(driver);
			
			connection = DriverManager.getConnection("jdbc:mysql://localhost/" + dbName,
					username, password);
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		
	}
	
}
