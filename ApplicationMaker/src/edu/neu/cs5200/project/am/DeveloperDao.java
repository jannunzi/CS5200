package edu.neu.cs5200.project.am;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DeveloperDao {
	Connection connection = null;
	DataSource dataSource = null;
	PreparedStatement statement = null;
	ResultSet results = null;
	
	public void create(Developer newDeveloper) {
		try {
			connection = dataSource.getConnection();
			statement = connection.prepareStatement("insert into developer (username, password, email) values (?,?,?)");
			statement.setString(1, newDeveloper.getUsername());
			statement.setString(2, newDeveloper.getPassword());
			statement.setString(3, newDeveloper.getEmail());
			statement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public List<Developer> read() {
		List<Developer> developers = new ArrayList<Developer>();
		try {
			connection = dataSource.getConnection();
			statement = connection.prepareStatement("select * from developer");
			results = statement.executeQuery();
			while(results.next()) {
				Developer dev = new Developer(results.getInt("id"), results.getString("username"), results.getString("password"), results.getString("email"));
				developers.add(dev);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return developers;
	}
	
	public void delete(int id) {
		try {
			connection = dataSource.getConnection();
			statement = connection.prepareStatement("delete from developer where id=?");
			statement.setInt(1, id);
			statement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void update(int id, Developer newDeveloper) {
		try {
			connection = dataSource.getConnection();
			statement = connection.prepareStatement("update developer set username=?, password=?, email=? where id=?");
			statement.setString(1, newDeveloper.getUsername());
			statement.setString(2, newDeveloper.getPassword());
			statement.setString(3, newDeveloper.getEmail());
			statement.setInt(4, id);
			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public DeveloperDao() {
		try {
			Context jndi = new InitialContext();
			dataSource = (DataSource) jndi.lookup("java:comp/env/jdbc/am");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
