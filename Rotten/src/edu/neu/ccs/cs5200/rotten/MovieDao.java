package edu.neu.ccs.cs5200.rotten;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MovieDao {

	String createMovieSql = "INSERT INTO MOVIE (id, title, movie_id, genre) VALUES (NULL, ?, ?, ?)";
	String readAllMoviesSql = "SELECT * FROM MOVIE";
	String updateMovieSql = "UPDATE MOVIE SET title=?, movie_id=?, genre=? WHERE id=?";
	PreparedStatement stmt;
	ResultSet results;
	
	public void createMovie(Movie movie)
	{
		Connection connection = getConnection();
		
		try {
			stmt = connection.prepareStatement(createMovieSql);
			stmt.setString(1, movie.getTitle());
			stmt.setString(2, movie.getMovieId());
			stmt.setString(3, movie.getGenre());
			stmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		closeConnection(connection);
	}
	
	public List<Movie> readAllMovies()
	{
		List<Movie> movies = new ArrayList<Movie>();
		
		Connection connection = getConnection();		
		try {
			stmt = connection.prepareStatement(readAllMoviesSql);
			results = stmt.executeQuery();
			while(results.next())
			{
				Movie movie = new Movie(
						results.getInt("id"),
						results.getString("title"),
						results.getString("movie_id"),
						results.getString("genre")
				);
				movies.add(movie);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		closeConnection(connection);
		
		return movies;
	}
	
	void updateMovie(int id, Movie movie)
	{
		Connection connection = getConnection();
		try {
			stmt = connection.prepareStatement(updateMovieSql);
			stmt.setInt(4, id);
			stmt.setString(1, movie.getTitle());
			stmt.setString(2, movie.getMovieId());
			stmt.setString(3, movie.getGenre());
			stmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		closeConnection(connection);
	}
	
	Connection getConnection()
	{
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			connection = DriverManager.getConnection("jdbc:mysql://localhost/rotten", "root", "");
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
		return connection;
	}
	
	void closeConnection(Connection connection)
	{
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args)
	{
		MovieDao dao = new MovieDao();
	
		Movie lotr = new Movie("LOTR", "kasjdhfkajshdf", "FANTASY");
		dao.updateMovie(3, lotr);
		
		List<Movie> movies = dao.readAllMovies();
		for(Movie movie: movies)
		{
			System.out.println(movie.getTitle());
		}
		
//		Movie lotr = new Movie("Lord of the Rings", "kasjdhfkajshdf", "FANTASY");
//		dao.createMovie(lotr);
	}
	
}
