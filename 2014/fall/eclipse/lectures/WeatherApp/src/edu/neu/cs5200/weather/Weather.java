package edu.neu.cs5200.weather;

import java.util.List;

public class Weather {
	private int id;
	private String zip;
	public Weather(String zip, String locationName) {
		super();
		this.zip = zip;
		this.locationName = locationName;
	}
	private String locationName;
	private Condition currentConditions;
	public Weather() {
		super();
	}
	public Weather(String locationName, String zip,
			Condition currentConditions, List<Condition> forecast) {
		super();
		this.locationName = locationName;
		this.zip = zip;
		this.currentConditions = currentConditions;
		this.forecast = forecast;
	}
	public Weather(int id, String zip, String locationName) {
		super();
		this.id = id;
		this.zip = zip;
		this.locationName = locationName;
	}
	public Weather(int id, String locationName, String zip,
			Condition currentConditions, List<Condition> forecast) {
		super();
		this.id = id;
		this.locationName = locationName;
		this.zip = zip;
		this.currentConditions = currentConditions;
		this.forecast = forecast;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public Condition getCurrentConditions() {
		return currentConditions;
	}
	public void setCurrentConditions(Condition currentConditions) {
		this.currentConditions = currentConditions;
	}
	public List<Condition> getForecast() {
		return forecast;
	}
	public void setForecast(List<Condition> forecast) {
		this.forecast = forecast;
	}
	private List<Condition> forecast;
}
