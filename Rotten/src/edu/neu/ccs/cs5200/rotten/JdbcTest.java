package edu.neu.ccs.cs5200.rotten;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcTest {

	public static void main(String[] args) {

		Statement stmt;
		System.out.println("Hello World");
		
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/rotten", "root", "");
			System.out.println(connection);
	
			String readAllMoviesSql = "SELECT * FROM MOVIE";
			stmt = connection.createStatement();
			ResultSet results = stmt.executeQuery(readAllMoviesSql);
			while(results.next())
			{
				String title = results.getString("title");
				System.out.println(title);
			}
			
//			String insertMovieSql = "INSERT INTO MOVIE VALUES (NULL, 'Star Trek', '321ewqdsa543')";
//			Statement insertMovieStmt = connection.createStatement();
//			insertMovieStmt.executeUpdate(insertMovieSql);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
