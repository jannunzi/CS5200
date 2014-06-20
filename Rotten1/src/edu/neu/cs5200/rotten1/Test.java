package edu.neu.cs5200.rotten1;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Test {

	static Connection connection;
	static Statement statement;
	static PreparedStatement pStatement;
	static String insertAccountSql = "INSERT INTO ACCOUNT VALUES (NULL, ?,?)";
	public static void main(String[] args)
	{
		try {
			Driver d = (Driver)Class.forName("com.mysql.jdbc.Driver").newInstance();
			connection = DriverManager.getConnection("jdbc:mysql://localhost/rotten1", "root", "");
			pStatement = connection.prepareStatement(insertAccountSql);
			pStatement.setString(1, "bob");
			pStatement.setString(2, "marley");
			pStatement.executeUpdate();			
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

}
