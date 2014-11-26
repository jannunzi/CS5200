package edu.neu.cs5200.ide.jpa.simple;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@NamedQueries({	@NamedQuery(name="Student.findStudentById", query="select s from Student s where s.name = :name"),
				@NamedQuery(name="Student.findAll", query="select s from Student s")})
public class Student {
	
	public String toString()
	{
		return id + " " + name + " " + gradDate;
	}
	
	public Student(String name, Date gradDate) {
		super();
		this.name = name;
		this.gradDate = gradDate;
	}
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String name;
	@Temporal(TemporalType.DATE)
	private Date gradDate;
	public Student() {
		super();
	}
	public Student(int id, String name, Date gradDate) {
		super();
		this.id = id;
		this.name = name;
		this.gradDate = gradDate;
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
	public Date getGradDate() {
		return gradDate;
	}
	public void setGradDate(Date gradDate) {
		this.gradDate = gradDate;
	}
}
