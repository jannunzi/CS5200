package edu.neu.cs5200.service.json.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpXmlClient {
	public static void main(String[] args) {
		try {
			URL url = new URL("https://api.flickr.com/services/rest/?method=flickr.photos.search&api_key=5da2ddf6e55e6529b2c3211d124182ea&tags=avatar");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			InputStream stream = connection.getInputStream();
			InputStreamReader reader = new InputStreamReader(stream);
			BufferedReader buffer = new BufferedReader(reader);
			String xmlString;
			while((xmlString = buffer.readLine()) != null ) {
				System.out.println(xmlString);
			}
			//System.out.println(xmlString);
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
