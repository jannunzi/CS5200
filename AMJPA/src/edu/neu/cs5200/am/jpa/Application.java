package edu.neu.cs5200.am.jpa;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Entity;
import javax.persistence.Id;

import java.util.List;

@Entity
@NamedQueries(value = {
	@NamedQuery(name = "findAllApplications", query = "select e from Application e")
})
public class Application {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String name;
	@ManyToOne
	@JoinColumn(name="developerId")
	private Developer developer;
	
	@OneToMany(mappedBy="application")
	private List<Page> pages;
	
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
	public Developer getDeveloper() {
		return developer;
	}
	public void setDeveloper(Developer developer) {
		this.developer = developer;
	}
	public Application() {
		super();
	}
	public Application(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public Application(String name) {
		super();
		this.name = name;
	}
}
