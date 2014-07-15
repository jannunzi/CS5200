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

<form action="developers.jsp" method="get">
<div class="container">
	
	<h1>Developers</h1>

<%
	String id       = request.getParameter("id");
	String username = request.getParameter("username");
	String password = request.getParameter("password");
	String email    = request.getParameter("email");
	String action   = request.getParameter("action");

	if(id == null) id = "";
	if(username == null) username = "";
	if(password == null) password = "";
	if(email == null) email = "";

	DeveloperDao dao = new DeveloperDao();

	if("add".equals(action)) {
		Developer entity = new Developer(username, password, email);
		dao.create(entity);
	} if("update".equals(action)) {
		int idInt = Integer.parseInt(id);
		Developer entity = new Developer(idInt, username, password, email);
		dao.update(entity);
	} if("delete".equals(action)) {
		int idInt = Integer.parseInt(id);
		dao.delete(idInt);
	}

	List<Developer> entities = dao.read();
%>	<table class="table">
		<thead>
			<tr>
				<th>Id</th>
				<th>Username</th>
				<th>Password</th>
				<th>Email</th>
				<th>&nbsp;</th>
			</tr>
			<tr>
				<th></th>
				<th><input name="username" value="<%= username %>" class="form-control"/></th>
				<th><input name="password" value="<%= password %>" class="form-control"/></th>
				<th><input name="email"    value="<%= email    %>" class="form-control"/></th>
				<th>
					<input name="id"       value="<%= id %>"       class="form-control" type="hidden"/>
					<button class="btn btn-default" name="action" value="add">Add</button>
					<button class="btn btn-default" name="action" value="update">Update</button>
				</th>
			</tr>
		</thead>
<%	for(Developer entity : entities) {
%>		<tr>
			<td><%= entity.getId() %></td>
			<td><%= entity.getUsername() %></td>
			<td><%= entity.getPassword() %></td>
			<td><%= entity.getEmail() %></td>
			<td>
				<a href="developers.jsp?action=delete&id=<%= entity.getId() %>" class="btn btn-default">Delete</a>
				<a href="developers.jsp?action=select&id=<%= entity.getId() %>&username=<%= entity.getUsername() %>&password=<%= entity.getPassword() %>&email=<%= entity.getEmail() %>" class="btn btn-default">Select</a>
				<a href="applications.jsp?developerId=<%= entity.getId() %>" class="btn btn-default">Applications</a>
			</td>
		</tr>
<%	}
%>	</table>

</div>
</form>

</body>
</html>