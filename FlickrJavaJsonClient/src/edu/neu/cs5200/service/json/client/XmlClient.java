package edu.neu.cs5200.service.json.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class XmlClient {

	public static void main(String[] args) {
		try {
			URL url = new URL("https://api.flickr.com/services/rest/?method=flickr.photos.search&api_key=5da2ddf6e55e6529b2c3211d124182ea&tags=avatar");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			InputStream stream = connection.getInputStream();
			
			JAXBContext jaxb = JAXBContext.newInstance(Rsp.class);
			Unmarshaller unmarshaller = jaxb.createUnmarshaller();
			Rsp response = (Rsp) unmarshaller.unmarshal(stream);
			System.out.println(response.getStat());
			
			for(Photo photo : response.getPhotos().getPhoto()) {
				System.out.println(photo.getTitle());
			}
			
//			InputStreamReader reader = new InputStreamReader(stream);
//			BufferedReader buffer = new BufferedReader(reader);
//			String response = buffer.readLine();
//			System.out.println(response);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
