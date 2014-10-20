package edu.neu.cs5200.experiment.weather.user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
	
	private static UserDao instance = null;
	
	protected UserDao() {}
	
	public static UserDao getInstance() {
		if(instance == null)
			instance = new UserDao();
		return instance;
	}
	
	private Connection getConnection() {
        try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/weather3?user=root");
		    return connection;
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return null;
	}
	
	private void closeConnection(Connection connection) {
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void create(User user) {
		String sql = "insert into user (username, password) values (?,?)";
		Connection connection = getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, user.getUsername());
			statement.setString(2, user.getPassword());
			statement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConnection(connection);
		}
	}
	

	public User selectByUsernameAndPassword(String username, String password) {
		String sql = "select * from user where username=? and password=?";
		Connection connection = getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, username);
			statement.setString(2, password);
			ResultSet results = statement.executeQuery();
			if(results.next()) {
				username = results.getString("username");
				password = results.getString("password");
				User user = new User(username, password);
				return user;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConnection(connection);
		}
		return null;
	}
	
	public static void main(String[] args) {
		UserDao dao = UserDao.getInstance();
		Connection connection = dao.getConnection();
		
		User user = dao.selectByUsernameAndPassword("user12", "p1");
		System.out.println(user);
	}

}
