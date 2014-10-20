package edu.neu.cs5200.experiment.weather.weather;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class WeatherJsonParser {	
	private static WeatherJsonParser instance = null;
	protected WeatherJsonParser() {}
	public static WeatherJsonParser getInstance() {
		if(instance == null)
			instance = new WeatherJsonParser();
		return instance;
	}
	
	DateFormat observationTimeFormat = new SimpleDateFormat("hh:mm a");
	
	public Weather parseFromJsonString(String json) {
		Weather weatherObj = new Weather();
		JSONParser parser = new JSONParser();
		try {
			JSONObject object = (JSONObject) parser.parse(json);
			object = (JSONObject) object.get("data");
			JSONArray array = (JSONArray) object.get("current_condition");
			JSONObject currentCondition = (JSONObject) array.get(0);
			
			// parse current condition
			System.out.println(currentCondition);
			Condition condition = new Condition();
			condition.cloudCover = Float.parseFloat(currentCondition.get("cloudcover").toString());
			condition.humidity = Float.parseFloat(currentCondition.get("humidity").toString());
			condition.pressure = Float.parseFloat(currentCondition.get("pressure").toString());
			condition.tempC = Float.parseFloat(currentCondition.get("temp_C").toString());
			condition.tempF = Float.parseFloat(currentCondition.get("temp_F").toString());
			String observationTime = currentCondition.get("observation_time").toString();
			condition.observationTime = observationTimeFormat.parse(observationTime);
			condition.visibility = Float.parseFloat(currentCondition.get("visibility").toString());
			condition.winddir16Point = currentCondition.get("winddir16Point").toString();
			condition.winddirDegree = Float.parseFloat(currentCondition.get("winddirDegree").toString());
			condition.windspeedKmph = Float.parseFloat(currentCondition.get("windspeedKmph").toString());
			condition.windspeedMiles = Float.parseFloat(currentCondition.get("windspeedMiles").toString());

			array = (JSONArray) currentCondition.get("weatherDesc");
			object = (JSONObject) array.get(0);
			condition.description = object.get("value").toString();
			
			array = (JSONArray) currentCondition.get("weatherIconUrl");
			object = (JSONObject) array.get(0);
			condition.iconUrl = object.get("value").toString();
			
			weatherObj.setCurrentConditions(condition);
			
			JSONArray weather = (JSONArray) object.get("weather");
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return weatherObj;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}
