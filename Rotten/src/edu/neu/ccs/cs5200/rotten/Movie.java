package edu.neu.ccs.cs5200.rotten;

public class Movie {
	private int id;
	private String title;
	private String movieId;
	private String genre;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getMovieId() {
		return movieId;
	}
	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public Movie() {
		super();
	}
	public Movie(String title, String movieId, String genre) {
		super();
		this.title = title;
		this.movieId = movieId;
		this.genre = genre;
	}
	public Movie(int id, String title, String movieId, String genre) {
		super();
		this.id = id;
		this.title = title;
		this.movieId = movieId;
		this.genre = genre;
	}
}
