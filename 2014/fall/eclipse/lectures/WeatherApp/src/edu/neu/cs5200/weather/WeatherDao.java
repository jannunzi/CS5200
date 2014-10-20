package edu.neu.cs5200.weather;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

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
		
		Context initContext;
		try {
			initContext = new InitialContext();
			Context envContext  = (Context)initContext.lookup("java:/comp/env");
			DataSource ds = (DataSource)envContext.lookup("jdbc/WeatherDB");
			connection = ds.getConnection();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, weather.getZip());
			statement.setString(2, weather.getLocationName());
			statement.execute();
			statement.close();
			statement = null;
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public List<Weather> selectFromUserId(int id) {
		List<Weather> ws = new ArrayList<Weather>();
		
		String sql = "select zip, locationName, weather.id from weather, user2weather, user where user.id = ? AND user.id = user2weather.userId AND weather.id = user2weather.weatherId";

		System.out.println("====123====");
		
		Connection connection = getConnection();
		PreparedStatement statement = null;
		ResultSet results = null;
		try {
			statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			results = statement.executeQuery();
			while(results.next()) {
				id = results.getInt("id");
				String zip = results.getString("zip");
				String locationName = results.getString("locationName");
				Weather weather = new Weather(id, zip, locationName);
				ws.add(weather);
			}
			results.close();
			results = null;
			statement.close();
			statement = null;
			connection.close();
			connection = null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(results != null) {
				try {
					results.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return ws;
	}
	
	public static void main(String[] args) {
		WeatherDao dao = WeatherDao.getInstance();
		
		Weather weather = new Weather("02115", "Boston");
		
		dao.create(weather);
	}
}
