package edu.neu.cs5200.weather;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// DAO Design Pattern
public class WeatherDao {
	
	// Singleton Design Pattern
	private static WeatherDao instance = null;
	protected WeatherDao() {}
	public static WeatherDao getInstance() {
		if(instance == null)
			instance = new WeatherDao();
		return instance;
	}	
	
	public Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			String connectionUrl = "jdbc:mysql://localhost:3306/weatherApp2";
			connection = DriverManager.getConnection(connectionUrl, "root", null);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	public void closeConnection(Connection connection) {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void create(Weather weather) {
		String sql = "insert into weather (zip, locationName) values (?,?)";
		Connection connection = getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, weather.getZip());
			statement.setString(2, weather.getLocationName());
			statement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<Weather> selectFromUserId(int id) {
		List<Weather> ws = new ArrayList<Weather>();
		
		String sql = "select zip, locationName, weather.id from weather, user2weather, user where user.id = ? AND user.id = user2weather.userId AND weather.id = user2weather.weatherId";

		Connection connection = getConnection();
		
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet results = statement.executeQuery();
			while(results.next()) {
				id = results.getInt("id");
				String zip = results.getString("zip");
				String locationName = results.getString("locationName");
				Weather weather = new Weather(id, zip, locationName);
				ws.add(weather);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ws;
	}
	
	public static void main(String[] args) {
		WeatherDao dao = WeatherDao.getInstance();
		
		Weather weather = new Weather("02115", "Boston");
		
		dao.create(weather);
	}
}
