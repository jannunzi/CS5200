package edu.neu.db.jws.movies;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries(value = { @NamedQuery(name = "findAllMovies", query = "select movie from Movie movie") })
public class Movie {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int id;
	String name = "Indiana Jones";
	public Movie() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
