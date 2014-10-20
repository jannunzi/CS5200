package edu.neu.cs5200.experiment.weather.weather;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class WeatherWebServiceClient {
	private static WeatherWebServiceClient instance = null;
	protected WeatherWebServiceClient() {}
	public static WeatherWebServiceClient getInstance() {
		if(instance == null)
			instance = new WeatherWebServiceClient();
		return instance;
	}
	
	public Weather getWeatherForZip(String zip) {
		String urlApi = "http://api.worldweatheronline.com/free/v1/weather.ashx?q={{ZIP}},USA&format=json&num_of_days=2&key=s3uv4fjbaw4pqtp26rh48afd";
		
		try {
			URL url = new URL(urlApi.replace("{{ZIP}}", zip));
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			InputStreamReader isr = new InputStreamReader(connection.getInputStream());
			BufferedReader br = new BufferedReader(isr);
			String inputLine;
			StringBuffer buffer = new StringBuffer();
			while((inputLine = br.readLine()) != null)
			{
				buffer.append(inputLine);
			}
			br.close();
			
			Weather weather = WeatherJsonParser.getInstance().parseFromJsonString(buffer.toString());
			weather.setZip(zip);
			return weather;
		} catch (MalformedURLException | ProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WeatherWebServiceClient client = WeatherWebServiceClient.getInstance();
		client.getWeatherForZip("01827");
	}
}
