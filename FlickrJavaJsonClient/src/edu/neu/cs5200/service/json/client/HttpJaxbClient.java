package edu.neu.cs5200.service.json.client;

import java.io.*;
import java.net.*;
import java.util.*;
import javax.xml.bind.*;
import edu.neu.cs5200.service.json.client.model.*;

public class HttpJaxbClient {
	public static void main(String[] args) {
		try {
			URL url = new URL("https://api.flickr.com/services/rest/?method=flickr.photos.search&api_key=5da2ddf6e55e6529b2c3211d124182ea&tags=avatar");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			InputStream stream = connection.getInputStream();
			
			JAXBContext jaxb = JAXBContext.newInstance(Rsp.class);
			Unmarshaller unmarshaller = jaxb.createUnmarshaller();
			Rsp response = (Rsp) unmarshaller.unmarshal(stream);
			List<Photo> photos = response.getPhotos().getPhoto();
			int count = 0;
			for(Photo photo : photos) {
				System.out.print(count);
				System.out.println(" " + photo.getTitle());
				count++;
			}
			stream.close();
			connection.disconnect();
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
