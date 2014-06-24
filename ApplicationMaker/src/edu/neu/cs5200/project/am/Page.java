package edu.neu.cs5200.project.am;

public class Page {
	int id;
	String name;
	int applicationId;
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
	public int getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(int applicationId) {
		this.applicationId = applicationId;
	}
	public Page(int id, String name, int applicationId) {
		super();
		this.id = id;
		this.name = name;
		this.applicationId = applicationId;
	}
	public Page(String name, int applicationId) {
		super();
		this.name = name;
		this.applicationId = applicationId;
	}
	public Page() {
		super();
	}
}
