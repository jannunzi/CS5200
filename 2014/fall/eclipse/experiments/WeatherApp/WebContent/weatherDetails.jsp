<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="edu.neu.cs5200.experiment.weather.weather.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link href="css/bootstrap.css" rel="stylesheet"/>
</head>
<body>
	<div class="container">
	
	<%
		Weather weather = (Weather)request.getAttribute("weather");
		Condition currentConditions = weather.getCurrentConditions();
	%>
	
	<form action="/WeatherApp/saveLocation">
		<input type="hidden" value="<%= weather.getZip() %>" name="zip"/>
	<h1>Weather Details for <%=weather.getZip() %></h1>
	<table class="table">
		<tbody>
			<tr><td><input class="form-control" name="locationName" placeholder="Name"/></td>
				<td><button class="btn btn-success">Add</button></td>
			<tr><td>Cloud Cover:</td><td><%= currentConditions.cloudCover %></tr>
			<tr><td>Description:</td><td><%= currentConditions.description %></tr>
			<tr><td>Image:</td><td><img src="<%= currentConditions.iconUrl %>"/></tr>
		</tbody>
	</table>
	</form>	
	</div>
</body>
</html>