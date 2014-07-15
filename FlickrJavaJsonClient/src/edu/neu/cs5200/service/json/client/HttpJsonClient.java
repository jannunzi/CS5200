package edu.neu.cs5200.service.json.client;

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

public class HttpJsonClient {
	public static void main(String[] args) {
		try {
			URL url = new URL("https://api.flickr.com/services/rest/?method=flickr.photos.search&api_key=5da2ddf6e55e6529b2c3211d124182ea&tags=avatar&format=json");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Accept", "json");
			InputStream stream = connection.getInputStream();
			InputStreamReader reader = new InputStreamReader(stream);
			BufferedReader buffer = new BufferedReader(reader);
			String response = buffer.readLine();
			System.out.println(response);
			
			response = response.replace("jsonFlickrApi(", "");
			response = response.replace(")", "");
			
			JSONParser parser = new JSONParser();
			JSONObject json = (JSONObject) parser.parse(response);
			
			JSONObject photos = (JSONObject) json.get("photos");
			Long pages = (Long) photos.get("pages");
			
			JSONArray photo = (JSONArray) photos.get("photo");
			
			for(int i=0; i<photo.size(); i++) {
				JSONObject instance = (JSONObject) photo.get(i);
				String id = (String) instance.get("id");
				String secret = (String) instance.get("secret");
				String server = (String) instance.get("server");
				Long farm   = (Long) instance.get("farm");
				String title  = (String) instance.get("title");
				System.out.println(title);
			}
			
			System.out.println(pages);
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
