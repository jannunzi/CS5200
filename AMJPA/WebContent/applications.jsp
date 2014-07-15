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

<form action="applications.jsp" method="get">
<div class="container">
	
	<h1>
		<a href="developers.jsp" class="btn btn-default">Back</a>
		Applications
	</h1>

<%
	String developerId = request.getParameter("developerId");
	String id     = request.getParameter("id");
	String name   = request.getParameter("name");
	String action = request.getParameter("action");

	if(id == null) id = "";
	if(name == null) name = "";

	DeveloperDao dDao = new ApplicationDao();
	ApplicationDao dao = new ApplicationDao();

	if("add".equals(action)) {
		Application entity = new Application(name);
		dao.create(entity);
	} if("update".equals(action)) {
		int idInt = Integer.parseInt(id);
		Application entity = new Application(idInt, name);
		dao.update(entity);
	} if("delete".equals(action)) {
		int idInt = Integer.parseInt(id);
		dao.delete(idInt);
	}

	Developer parentEntity = dDao.read(developerId);
	List<Application> entities = parentEntity.getApplications();
%>	<table class="table">
		<thead>
			<tr>
				<th>Id</th>
				<th>Name</th>
				<th>&nbsp;</th>
			</tr>
			<tr>
				<th></th>
				<th><input name="name" value="<%= name %>" class="form-control"/></th>
				<th>
					<input name="id"       value="<%= id %>"       class="form-control" type="hidden"/>
					<button class="btn btn-default" name="action" value="add">Add</button>
					<button class="btn btn-default" name="action" value="update">Update</button>
				</th>
			</tr>
		</thead>
<%	for(Application entity : entities) {
%>		<tr>
			<td><%= entity.getId() %></td>
			<td><%= entity.getName() %></td>
			<td>
				<a href="applications.jsp?action=delete&id=<%= entity.getId() %>" class="btn btn-default">Delete</a>
				<a href="applications.jsp?action=select&id=<%= entity.getId() %>&name=<%= entity.getName() %>" class="btn btn-default">Select</a>
				<a href="pages.jsp?" class="btn btn-default">Pages</a>
			</td>
		</tr>
<%	}
%>	</table>

</div>
</form>

</body>
</html>