<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.io.*,java.net.*,org.json.simple.*,org.json.simple.parser.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>


<%

try {
	URL url = new URL("https://api.flickr.com/services/rest/?method=flickr.photos.search&api_key=5da2ddf6e55e6529b2c3211d124182ea&tags=welcome&format=json");
	HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	InputStream stream = connection.getInputStream();
	InputStreamReader reader = new InputStreamReader(stream);
	BufferedReader buffer = new BufferedReader(reader);
	String resp = buffer.readLine();
	
	resp = resp.replace("jsonFlickrApi(", "");
	resp = resp.replace(")", "");
	
	String photoUrl = "https://farm{farm-id}.staticflickr.com/{server-id}/{id}_{secret}_m.jpg";
	
	JSONParser parser = new JSONParser();
	JSONObject object = (JSONObject) parser.parse(resp);
	JSONObject photos = (JSONObject) object.get("photos");
	JSONArray photo = (JSONArray) photos.get("photo");
	for(int i=0; i<photo.size(); i++) {
		JSONObject thePhoto = (JSONObject) photo.get(i);

		String imageUrl = photoUrl.replace("{farm-id}", (Long)thePhoto.get("farm")+"");
		imageUrl = imageUrl.replace("{server-id}", (String)thePhoto.get("server"));
		imageUrl = imageUrl.replace("{id}", (String)thePhoto.get("id"));
		imageUrl = imageUrl.replace("{secret}", (String)thePhoto.get("secret"));
		%>
			<img src="<%= imageUrl %>"/>
		<%
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


%>

</body>
</html>