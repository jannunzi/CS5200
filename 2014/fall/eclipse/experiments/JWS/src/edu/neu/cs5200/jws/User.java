package edu.neu.cs5200.jws;

public class User {
	public String username;
	public String firstName;
	public String lastName;
	public double salary;
	public User(){}
	public User(String firstName, String lastName, double salary)
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.salary = salary;
	}
	public User(String username, String firstName, String lastName, double salary)
	{
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.salary = salary;
	}
}
