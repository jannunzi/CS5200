<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*,edu.neu.cs5200.experiment.weather.weather.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link href="css/bootstrap.css" rel="stylesheet"/>
</head>
<body>
	<div class="container">
		<h1>Weather List !!!</h1>

		<form action="/WeatherApp/searchZip">
			<div class="input-group">
				<input placeholder="ZIP" type="text" class="form-control" name="zip">
				<span class="input-group-btn">
					<button class="btn btn-default" type="submit">Go!</button>
				</span>
			</div>
		</form>
	
		<ul>
		<%
			List<Weather> ws = (List<Weather>)request.getAttribute("weathers");		
			for(Weather w:ws) {
		%>
				<li><%= w.getLocationName() %></li>
		<%	}
		%>
		</ul>
	</div>
</body>
</html>