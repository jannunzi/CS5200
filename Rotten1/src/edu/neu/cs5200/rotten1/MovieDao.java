package edu.neu.cs5200.rotten1;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MovieDao {
	
	String createMovieSql = "INSERT INTO MOVIE VALUES (NULL, ?, ?)";
	PreparedStatement statement;
	ResultSet results;
	public void createMovie(Movie movie) {
		Connection connection = getConnection();
		try {
			statement = connection.prepareStatement(createMovieSql);
			statement.setString(1, movie.getMovieId());
			statement.setString(2, movie.getTitle());
			statement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		closeConnection(connection);
	}
	public List<Movie> readAllMovies() {
		List<Movie> movies = new ArrayList<Movie>();
		String readAllMoviesSql = "SELECT * FROM MOVIE";
		Connection connection = getConnection();
		try {
			statement = connection.prepareStatement(readAllMoviesSql);
			results = statement.executeQuery();
			while(results.next()) {
				int id = results.getInt("id");
				String movieId = results.getString("movieId");
				String title = results.getString("title");
				Movie movie = new Movie(id, movieId, title);
				movies.add(movie);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		closeConnection(connection);
		return movies;
	}
	public Movie readMovieById(int id) {
		Movie movie = null;
		Connection connection = getConnection();
		String readMovieByIdSql = "SELECT * FROM MOVIE WHERE ID=?";
		try {
			statement = connection.prepareStatement(readMovieByIdSql);
			statement.setInt(1, id);
			results = statement.executeQuery();
			if(results.next()) {
				String movieId = results.getString("movieId");
				String title = results.getString("title");
				movie = new Movie(id, movieId, title);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		closeConnection(connection);
		return movie;
	}
	public void updateMovie(int id, Movie updatedMovie) {
		String updateMovieSql = "UPDATE MOVIE SET TITLE=? WHERE ID=?";
		Connection connection = getConnection();
		try {
			statement = connection.prepareStatement(updateMovieSql);
			statement.setInt(2, id);
			statement.setString(1, updatedMovie.getTitle());
			statement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		closeConnection(connection);
	}
	public void deleteMovie(int id) {
		String deleteMovieSql = "DELETE FROM MOVIE WHERE ID=?";
		Connection connection = getConnection();
		try {
			statement = connection.prepareStatement(deleteMovieSql);
			statement.setInt(1, id);
			statement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		closeConnection(connection);
	}
	public static void main(String[] args) {
		MovieDao dao = new MovieDao();

		dao.deleteMovie(2);
	//	Movie updatedMovie = new Movie("Tron 2");
	//	dao.updateMovie(2, updatedMovie);
	//	Movie movie = dao.readMovieById(2);
	//	System.out.println(movie.getTitle());
	//	List<Movie> movies = dao.readAllMovies();
	//	for(Movie movie : movies) {
	//		System.out.println(movie.getTitle());
	//	}
	//	Movie avatar = new Movie("432", "Tron");
	//	dao.createMovie(avatar);
	}
	private Connection getConnection() {
		try {
			Connection connection;
			Driver driver = (Driver)Class.forName("com.mysql.jdbc.Driver").newInstance();
			connection = DriverManager.getConnection("jdbc:mysql://localhost/rotten1", "root", "");
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
}
