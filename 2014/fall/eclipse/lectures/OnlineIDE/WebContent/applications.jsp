<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="edu.neu.cs5200.onlineide.*,java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Applications</title>
<link href="css/bootstrap.css" rel="stylesheet"/>
</head>
<body>
<div class="container">
	<h1>Applications</h1>

<%	ApplicationDAO dao = new ApplicationDAO();

	String action = request.getParameter("action");
	String name = request.getParameter("name");
	String price = request.getParameter("price");

	Application ap = new Application();
	if("create".equals(action)) {
		double pd = Double.parseDouble(price);
		ap = new Application(name, pd);
		dao.create(ap);
	}

	List<Application> applications = dao.selectAll();
%>
	<form action="applications.jsp">
	<table class="table">
		<tr>
			<th>Name</th>
			<th>Price</th>
		</tr>
		<tr>
			<td><input name="name" class="form-control"/></td>
			<td><input name="price" class="form-control"/></td>
			<td><button name="action" value="create" class="btn btn-success">Add</button>
			</td>
		</tr>
<%	for(Application app : applications) {
%>		<tr>
			<td><%= app.getName() %></td>
			<td>$<%= app.getPrice() %></td>
		</tr>
<%	}
%>	</table>
	</form>
</div>
</body>
</html>