package edu.neu.ccs.cs5200.rotten;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpTest {

	public String searchMovie(String movieName) {
		StringBuffer buffer = new StringBuffer();
		try {
			String key = "umgs9aw92awmyuw6qvmgqkgv";
			movieName = movieName.replaceAll(" ", "%20");
			String url = "http://api.rottentomatoes.com/api/public/v1.0/movies.json?apikey=API_KEY&q=MOVIE_NAME";
			url = url.replace("API_KEY", key);
			url = url.replace("MOVIE_NAME", movieName);

			URL rottenUrl = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) rottenUrl.openConnection();
			connection.setRequestMethod("GET");
			InputStreamReader reader = new InputStreamReader(connection.getInputStream());
			BufferedReader in = new BufferedReader(reader);
			String inputLine;
			while((inputLine = in.readLine()) != null) {
				buffer.append(inputLine);
			}
			in.close();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return buffer.toString();
	}
	
	public String searchMovie2(String movieName) {
		String urlString = "http://api.rottentomatoes.com/api/public/v1.0/movies.json?apikey=API_KEY&q=MOVIE_NAME";
		String key = "umgs9aw92awmyuw6qvmgqkgv";
		movieName = movieName.replace(" ", "%20");
		urlString = urlString.replace("MOVIE_NAME", movieName);
		urlString = urlString.replace("API_KEY", key);
		try {
			URL url = new URL(urlString);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			InputStream is = connection.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader reader = new BufferedReader(isr);
			String line;
			while((line = reader.readLine())!=null) {
				System.out.println(line);
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
	
	public String searchMovie3(String  movieName) {
		String urlString = "http://api.rottentomatoes.com/api/public/v1.0/movies.json?apikey=API_KEY&q=MOVIE_NAME";
		String key = "umgs9aw92awmyuw6qvmgqkgv";
		urlString = urlString.replace("API_KEY", key);
		urlString = urlString.replace("MOVIE_NAME", movieName);
		StringBuffer buffer = new StringBuffer();
		try {
			URL url = new URL(urlString);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			InputStream is = connection.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String line;
			while((line = br.readLine())!=null) {
				buffer.append(line);
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return buffer.toString();
	}
	
	public static void main(String[] args) {
		HttpTest test = new HttpTest();
		String json = test.searchMovie3("Avatar");
		System.out.println(json);
	}

}
