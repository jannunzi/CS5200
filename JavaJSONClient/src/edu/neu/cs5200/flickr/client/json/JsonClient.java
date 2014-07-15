package edu.neu.cs5200.flickr.client.json;

import java.io.*;
import java.net.*;
import org.json.simple.*;
import org.json.simple.parser.*;

public class JsonClient {

	public static void main(String[] args) {
		try {
			URL url = new URL("https://api.flickr.com/services/rest/?method=flickr.photos.search&api_key=5da2ddf6e55e6529b2c3211d124182ea&tags=avatar&format=json");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			InputStream stream = connection.getInputStream();
			InputStreamReader reader = new InputStreamReader(stream);
			BufferedReader buffer = new BufferedReader(reader);
			String response = buffer.readLine();
			
			response = response.replace("jsonFlickrApi(", "");
			response = response.replace(")", "");
			
			System.out.println(response);
			
			String photoUrl = "https://farm{farm-id}.staticflickr.com/{server-id}/{id}_{secret}_m.jpg";
			
			JSONParser parser = new JSONParser();
			JSONObject object = (JSONObject) parser.parse(response);
			JSONObject photos = (JSONObject) object.get("photos");
			JSONArray photo = (JSONArray) photos.get("photo");
			for(int i=0; i<photo.size(); i++) {
				JSONObject thePhoto = (JSONObject) photo.get(i);
//				System.out.println(thePhoto.get("title"));
				Photo p = new Photo();
				p.setId((String)thePhoto.get("id"));
				p.setTitle((String)thePhoto.get("title"));
				
				String imageUrl = photoUrl.replace("{farm-id}", (Long)thePhoto.get("farm")+"");
				imageUrl = imageUrl.replace("{server-id}", (String)thePhoto.get("server"));
				imageUrl = imageUrl.replace("{id}", (String)thePhoto.get("id"));
				imageUrl = imageUrl.replace("{secret}", (String)thePhoto.get("secret"));
				System.out.println(imageUrl);
				
			}
			
			System.out.println(photos);
			
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
