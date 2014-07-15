package edu.neu.cs5200.flickr.client.xml;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import edu.neu.cs5200.flickr.client.xml.model.Photo;
import edu.neu.cs5200.flickr.client.xml.model.Photos;
import edu.neu.cs5200.flickr.client.xml.model.Rsp;

public class XmlClient {
	public static void main(String[] args) {
		URL url;
		try {
			url = new URL("https://api.flickr.com/services/rest/?method=flickr.photos.search&api_key=5da2ddf6e55e6529b2c3211d124182ea&tags=welcome");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			InputStream stream = connection.getInputStream();
			
			JAXBContext jaxb = JAXBContext.newInstance(Rsp.class);
			Unmarshaller unmarshaller = jaxb.createUnmarshaller();
			Rsp response = (Rsp) unmarshaller.unmarshal(stream);
			
			System.out.println(response.getStat());
			
			Photos photos = response.getPhotos();
			
			List<Photo> photo = photos.getPhoto();
			
			for(Photo p : photo) {
				System.out.println(p.getTitle());
			}
			
			
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
