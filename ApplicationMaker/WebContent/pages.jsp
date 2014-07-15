<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="edu.neu.cs5200.am.jpa.*, java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link href="css/bootstrap.css" rel="stylesheet"/>
</head>
<body>

<form action="pages.jsp">
<div class="container">

<%	
	String developerId   = request.getParameter("developerId");
	String applicationId = request.getParameter("applicationId");
	String id = request.getParameter("id");
	String name = request.getParameter("name");
%>

	<h1>
		<a href="applications.jsp?developerId=<%= developerId %>" class="btn btn-default">Back</a>
		Pages
	</h1>

</div>
</form>

</body>
</html>