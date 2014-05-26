package edu.neu.cs5200.rotten1;

public class Movie {
	private int id;
	private String movieId;
	private String title;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMovieId() {
		return movieId;
	}
	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Movie() {
		super();
	}
	public Movie(String movieId, String title) {
		super();
		this.movieId = movieId;
		this.title = title;
	}
	public Movie(int id, String movieId, String title) {
		super();
		this.id = id;
		this.movieId = movieId;
		this.title = title;
	}
	public Movie(String title) {
		super();
		this.title = title;
	}
}
