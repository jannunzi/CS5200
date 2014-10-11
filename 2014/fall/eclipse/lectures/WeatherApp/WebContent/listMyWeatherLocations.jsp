<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="edu.neu.cs5200.user.*, java.util.*, edu.neu.cs5200.weather.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link href="css/bootstrap.css" rel="stylesheet"/>
</head>
<body>

<div class="container">
<h1>List of My Weather Locations</h1>

<%

	User user = (User)request.getAttribute("user");
	System.out.println(user.getUsername());
	
	WeatherDao weatherDao = WeatherDao.getInstance();
	
	List<Weather> weathers = weatherDao.selectFromUserId(user.getId());
	%>
	
	<table class="table">
	<%
	for(Weather weather:weathers) {
		%>
		
		<tr>
			<td><%= weather.getZip() %></td>
			<td><%= weather.getLocationName() %></td>
		</tr>
		
		<%
	}
	
%>
</table>
<%= user.getUsername() %>
<%= user.getId() %>
</div>
</body>
</html>