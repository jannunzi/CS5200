<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.io.*,java.net.*,org.json.simple.*,org.json.simple.parser.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<form action="client.jsp">
	<input name="tags"/>
	<input type="submit" value="Search"/>

<%
	String tags = request.getParameter("tags");
	if(tags == null || tags.equals("")) {
		tags = "avatar";
	}
	URL url = new URL("https://api.flickr.com/services/rest/?per_page=10&method=flickr.photos.search&api_key=5da2ddf6e55e6529b2c3211d124182ea&tags="+tags+"&format=json");
	HttpURLConnection connection = (HttpURLConnection)url.openConnection();
	connection.setRequestMethod("GET");
	connection.setRequestProperty("Accept", "application/json");
	InputStream stream = connection.getInputStream();
	InputStreamReader reader = new InputStreamReader(stream);
	BufferedReader buffer = new BufferedReader(reader);
	String res = buffer.readLine();
	
	res = res.replace("jsonFlickrApi(", "");
	res = res.replace(")", "");
	
	//out.println(res);
	
	JSONParser parser = new JSONParser();
	JSONObject json = (JSONObject)parser.parse(res);

	JSONObject photos = (JSONObject)json.get("photos");
	JSONArray photo = (JSONArray)photos.get("photo");
	
	String title = null;
	String photoSource = "https://farm{farm-id}.staticflickr.com/{server-id}/{id}_{secret}_m.jpg";
%>	<ul>
<%	for(int i=0; i<photo.size(); i++) {
		JSONObject photoInstance = (JSONObject)photo.get(i);
		title = (String)photoInstance.get("title");

		String id = (String)photoInstance.get("id");
		Long farm = (Long)photoInstance.get("farm");
		String server = (String)photoInstance.get("server");
		String secret = (String)photoInstance.get("secret");
		String photoUrl = photoSource.replace("{farm-id}", farm+"");
		photoUrl = photoUrl.replace("{id}", id);
		photoUrl = photoUrl.replace("{server-id}", server);
		photoUrl = photoUrl.replace("{secret}", secret);
%>		<li>
			<img src="<%= photoUrl %>"/>
		</li>
<%
	}
%>	</ul>

</form>

</body>
</html>