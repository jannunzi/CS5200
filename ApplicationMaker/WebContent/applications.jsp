<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="edu.neu.cs5200.project.am.*,java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<form action="applications.jsp">

<h1>
	<a href="developers.jsp">Back</a>
Applications</h1>

<%
	String developerIdStr = request.getParameter("developerId");

	String idStr = request.getParameter("id");
	String name = request.getParameter("name");
	String description = request.getParameter("description");
	String action = request.getParameter("action");
	
	if(name == null) name = "";
	if(description  == null) description = "";
	
	ApplicationDao dao = new ApplicationDao();

	int developerId = Integer.parseInt(developerIdStr);

	if("add".equals(action)) {
		Application newEntity = new Application(name, description, developerId);
		dao.create(developerId, newEntity);
	} else if("delete".equals(action)) {
		int id = Integer.parseInt(idStr);
		dao.delete(id);
	} else if("update".equals(action)) {
		int id = Integer.parseInt(idStr);
		Application newEntity = new Application(name, description, developerId);
		dao.update(id, newEntity);
	}

	List<Application> entities = dao.read(developerId);
%>	
	<table>
		<thead>
			<tr>
				<th>Id</th>
				<th>Name</th>
				<th>Description</th>
				<th>&nbsp;</th>				
			</tr>
			<tr>
				<th>&nbsp;</th>
				<th><input name="name" value="<%= name%>"/></th>
				<th><input name="description" value="<%= description%>"/></th>
				<th>
					<input type="hidden" name="id" value="<%= idStr %>"/>
					<input type="hidden" name="developerId" value="<%= developerIdStr %>"/>
					<button name="action" value="add">Add</button>
					<button name="action" value="update">Update</button>
				</th>
			</tr>
		</thead>
		<tbody>
<%			for(Application entity : entities) {
%>			<tr>
				<td><%= entity.getId() %>
				<td><%= entity.getName() %>
				<td><%= entity.getDescription() %>
				<td>
					<a href="applications.jsp?developerId=<%=developerIdStr %>&action=delete&id=<%= entity.getId() %>">Delete</a>
					<a href="applications.jsp?developerId=<%=developerIdStr %>&action=select&id=<%= entity.getId() %>&name=<%= entity.getName() %>&description=<%= entity.getDescription() %>">Select</a>
				</td>
			</tr>
<%
			}
%>
		</tbody>
	</table>
</form>
</body>
</html>