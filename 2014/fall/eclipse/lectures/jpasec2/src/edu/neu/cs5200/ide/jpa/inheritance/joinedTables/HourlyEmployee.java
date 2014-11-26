package edu.neu.cs5200.ide.jpa.inheritance.joinedTables;

import javax.persistence.Entity;

@Entity
public class HourlyEmployee extends Employee {
	private int hours;
	private float rate;
	public int getHours() {
		return hours;
	}
	public void setHours(int hours) {
		this.hours = hours;
	}
	public HourlyEmployee(String name) {
		super(name);
	}
	public HourlyEmployee(String name, int hours, float rate) {
		super(name);
		this.hours = hours;
		this.rate = rate;
	}
	public HourlyEmployee() {
		super();
	}
	public float getRate() {
		return rate;
	}
	public void setRate(float rate) {
		this.rate = rate;
	}
}
