package edu.neu.cs5200.experiment.weather.weather;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WeatherDao {
	private static WeatherDao instance = null;
	protected WeatherDao() {}
	public static WeatherDao getInstance() {
		if(instance == null)
			instance = new WeatherDao();
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
	public List<Weather> selectWeatherForUsername(String username) {
		List<Weather> ws = new ArrayList<Weather>();
		String sql = "select * from weather, w2u, user where weather.id = w2u.weather_id and user.id = w2u.user_id and user.username=?";
		Connection connection = getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, username);
			ResultSet results = statement.executeQuery();
			while(results.next()) {
				int id = results.getInt("id");
				String zip = results.getString("zip");
				String locationName = results.getString("locationName");
				Weather w = new Weather(id, zip, locationName);
				ws.add(w);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(connection);
		}
		return ws;
	}
	public static void main(String[] args){
		WeatherDao dao = WeatherDao.getInstance();
		List<Weather> ws = dao.selectWeatherForUsername("user1");
		System.out.println(ws);
		for(Weather w:ws) {
			System.out.println(w);
		}
	}
}
