package edu.neu.cs5200.experiment.weather.weather;

import java.util.Date;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Weather {
	private int id;
	private String zip;
	private String locationName;
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
	private Condition currentConditions;
	private List<Condition> forecast;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	public Weather() {
		super();
	}
	public Weather(String zip, String locationName) {
		super();
		this.zip = zip;
		this.locationName = locationName;
	}
	public Weather(int id, String zip, String locationName) {
		super();
		this.id = id;
		this.zip = zip;
		this.locationName = locationName;
	}
	public String toString() {
		String str = currentConditions.toString();
		return str;
	}
}
