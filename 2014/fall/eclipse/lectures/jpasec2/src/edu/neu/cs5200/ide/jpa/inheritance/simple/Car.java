package edu.neu.cs5200.ide.jpa.inheritance.simple;

import javax.persistence.Entity;

@Entity
public class Car extends Vehicle {
	private int wheels;

	public int getWheels() {
		return wheels;
	}

	public void setWheels(int wheels) {
		this.wheels = wheels;
	}

	public Car(String name, int wheels) {
		super(name);
		this.wheels = wheels;
	}

	public Car() {
		super();
	}
}
