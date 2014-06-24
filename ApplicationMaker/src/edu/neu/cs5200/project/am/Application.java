package edu.neu.cs5200.project.am;

public class Application {
	int id;
	String name;
	String description;
	int developerId;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getDeveloperId() {
		return developerId;
	}
	public void setDeveloperId(int developerId) {
		this.developerId = developerId;
	}
	public Application(int id, String name, String description, int developerId) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.developerId = developerId;
	}
	public Application() {
		super();
	}
	public Application(String name, String description, int developerId) {
		super();
		this.name = name;
		this.description = description;
		this.developerId = developerId;
	}
}
