package edu.neu.cs5200.fm1;

public class Developer {
	int id;
	String username;
	String email;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Developer(int id, String username, String email) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
	}
	public Developer(String username, String email) {
		super();
		this.username = username;
		this.email = email;
	}
	public Developer() {
		super();
	}
	public String toString() {
		return id + " " + username + " " + email;
	}
}
