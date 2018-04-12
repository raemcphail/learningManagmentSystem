package dbManagers;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;
/**
 * Manager is a super class which connects to the database
 * @author Louis, Raemc
 * @version 1.0
 * @since April 2, 2018
 */
public class Manager {
	/**
	 * the database server username
	 */
	protected final String username = "root";
	/**
	 * the database server password
	 */
	protected String password = "ensf409";
	/**
	 * the connection object
	 */
	public static Connection connection;
	/**
	 * the database name
	 */
	protected String dbName = "awesomed2lproject";
	/**
	 * a prepared statement to execute queries
	 */
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
