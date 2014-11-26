package edu.neu.cs5200.ide.jpa;

import java.io.Serializable;
import java.lang.String;
import java.util.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Developer
 *
 */
@Entity

public class Developer implements Serializable {

	   
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String first;
	private String last;
	private String email;
	@Temporal(TemporalType.DATE)
	private Date dob;
	private static final long serialVersionUID = 1L;

	public Developer() {
		super();
	}   
	public Developer(String first, String last, String email, Date date) {
		this.first = first;
		this.last = last;
		this.email = email;
		this.dob = date;
	}
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}   
	public String getFirst() {
		return this.first;
	}

	public void setFirst(String first) {
		this.first = first;
	}   
	public String getLast() {
		return this.last;
	}

	public void setLast(String last) {
		this.last = last;
	}   
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
   
}
