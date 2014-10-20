package edu.neu.cs5200.experiment.weather.weather;

import java.util.Date;

public class Condition {
	public float cloudCover;
	public float humidity;
	public Date observationTime;
	public float pressure;
	public float tempC;
	public float tempF;
	public float visibility;
	public String winddir16Point;
	public float winddirDegree;
	public float windspeedKmph;
	public float windspeedMiles;
	public String description;
	public String iconUrl;
	
	public String toString() {
		String str = description + " " + iconUrl + " " + cloudCover + " " + humidity + " " + observationTime + " " + pressure + " " + tempC + " " + tempF;
		return str;
	}
}
