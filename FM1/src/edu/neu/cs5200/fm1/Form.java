package edu.neu.cs5200.fm1;

public class Form {
	private int id;
	private String name;
	private int developerId;
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
	public Form(int id, String name, int developerId) {
		super();
		this.id = id;
		this.name = name;
		this.developerId = developerId;
	}
	public Form(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public Form(String name) {
		super();
		this.name = name;
	}
	public Form() {
		super();
	}
	public int getDeveloperId() {
		return developerId;
	}
	public void setDeveloperId(int developerId) {
		this.developerId = developerId;
	}
}
