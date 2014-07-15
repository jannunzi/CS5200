<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.io.*,java.net.*,java.util.*,javax.xml.bind.*,edu.neu.cs5200.service.json.client.model.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<form action="httpJaxbClient.jsp">

	<input name="tags"/>
	<button>Search</button>
	<br/>
<%
	String tags = request.getParameter("tags");
	if(tags == null || tags.equals(""))
		tags = "avatar";
	URL url = new URL("https://api.flickr.com/services/rest/?method=flickr.photos.search&api_key=5da2ddf6e55e6529b2c3211d124182ea&tags="+tags);
	HttpURLConnection connection = (HttpURLConnection)url.openConnection();
	InputStream stream = connection.getInputStream();
	
	JAXBContext jaxb = JAXBContext.newInstance(Rsp.class);
	Unmarshaller unmarshaller = jaxb.createUnmarshaller();
	Rsp rsp = (Rsp)unmarshaller.unmarshal(stream);
	List<Photo> photos = rsp.getPhotos().getPhoto();
	
	String photoUrl = "https://farm{farm-id}.staticflickr.com/{server-id}/{id}_{secret}_s.jpg";
	
	for(Photo photo : photos) {
		String u = photoUrl.replace("{farm-id}", photo.getFarm());
		u = u.replace("{server-id}", photo.getServer());
		u = u.replace("{id}", photo.getId());
		u = u.replace("{secret}", photo.getSecret());
%>		<img src="<%=u%>"/>
<%
	}
%>

</form>

</body>
</html>