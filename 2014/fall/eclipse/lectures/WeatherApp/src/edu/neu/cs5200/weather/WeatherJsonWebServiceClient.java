package edu.neu.cs5200.weather;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.sun.net.ssl.HttpsURLConnection;

public class WeatherJsonWebServiceClient {
	
	private String urlApi = "http://api.worldweatheronline.com/free/v1/weather.ashx?q={{ZIP}},USA&format=json&num_of_days=2&key=s3uv4fjbaw4pqtp26rh48afd";
	
	public Weather getWeatherForZip(String zip) {
		
		String urlStr = urlApi.replace("{{ZIP}}", zip);
		
		System.out.println(urlStr);
		
		try {
			URL url = new URL(urlStr);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			InputStream stream = connection.getInputStream();
			InputStreamReader reader = new InputStreamReader(stream);
			BufferedReader buffer = new BufferedReader(reader);
			String line;
			String json = "";
			while((line = buffer.readLine()) != null) {
				json += line;
			}
			
			JSONParser parser = new JSONParser();
			try {
				JSONObject root = (JSONObject) parser.parse(json);
				JSONObject data = (JSONObject) root.get("data");
				JSONArray current_condition = (JSONArray) data.get("current_condition");
				JSONObject firstCondition = (JSONObject) current_condition.get(0);
				JSONArray weatherDescArray = (JSONArray) firstCondition.get("weatherDesc");
				JSONObject weatherDesc = (JSONObject) weatherDescArray.get(0);
				JSONArray weatherIconUrlArray = (JSONArray) firstCondition.get("weatherIconUrl");
				JSONObject weatherIconUrl = (JSONObject) weatherIconUrlArray.get(0);
				
				Condition currentCondition = new Condition();
				currentCondition.cloudcover = Float.parseFloat(firstCondition.get("cloudcover").toString());
				currentCondition.pressure = Float.parseFloat(firstCondition.get("pressure").toString());
				currentCondition.humidity = Float.parseFloat(firstCondition.get("humidity").toString());
				currentCondition.weatherDesc = weatherDesc.get("value").toString();
				currentCondition.weatherIconUrl = weatherIconUrl.get("value").toString();
				
				JSONArray weather = (JSONArray) data.get("weather");

				Weather weatherObject = new Weather("Dunstable", "01827", currentCondition, null);
				
				return weatherObject;
				
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return null;
	}
	
	public static void main(String[] args) {
		WeatherJsonWebServiceClient client = new WeatherJsonWebServiceClient();
		client.getWeatherForZip("01827");
	}

}
