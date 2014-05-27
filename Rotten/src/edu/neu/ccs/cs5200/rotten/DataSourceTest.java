package edu.neu.ccs.cs5200.rotten;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DataSourceTest {

	Statement stmt;
	
	public void testConnection()
	{
		Connection connection = getConnection();
		System.out.println(connection);

		String readAllMoviesSql = "SELECT * FROM MOVIE";
		try {
			stmt = connection.createStatement();
			ResultSet results = stmt.executeQuery(readAllMoviesSql);
			while(results.next())
			{
				String title = results.getString("title");
				System.out.println(title);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		closeConnection(connection);
	}
	
	public void closeConnection(Connection connection)
	{
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Connection getConnection()
	{
		Connection connection = null;
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/rotten");
			connection = ds.getConnection();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}
}
