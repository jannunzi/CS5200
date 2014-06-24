<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="edu.neu.cs5200.project.am.*,java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<form action="developers.jsp">

<h1>Developers</h1>

<%
	String idStr = request.getParameter("id");
	String username = request.getParameter("username");
	String password = request.getParameter("password");
	String email = request.getParameter("email");
	String action = request.getParameter("action");
	
	if(username == null) username = "";
	if(password == null) password = "";
	if(email == null) email = "";
	
	DeveloperDao dao = new DeveloperDao();

	if("add".equals(action)) {
		Developer newDeveloper = new Developer(username, password, email);
		dao.create(newDeveloper);
	} else if("delete".equals(action)) {
		int id = Integer.parseInt(idStr);
		dao.delete(id);
	} else if("update".equals(action)) {
		int id = Integer.parseInt(idStr);
		Developer newDeveloper = new Developer(id, username, password, email);
		dao.update(id, newDeveloper);
	}

	List<Developer> developers = dao.read();
%>	
	<table>
		<thead>
			<tr>
				<th>Id</th>
				<th>Username</th>
				<th>Password</th>
				<th>Email</th>				
				<th>&nbsp;</th>				
			</tr>
			<tr>
				<th>&nbsp;</th>
				<th><input name="username" value="<%= username%>"/></th>
				<th><input name="password" value="<%= password%>"/></th>
				<th><input name="email"  value="<%= email%>"/></th>
				<th>
					<input type="hidden" name="id" value="<%= idStr %>"/>
					<button name="action" value="add">Add</button>
					<button name="action" value="update">Update</button>
				</th>
			</tr>
		</thead>
		<tbody>
<%			for(Developer developer : developers) {
%>			<tr>
				<td><%= developer.getId() %>
				<td><%= developer.getUsername() %>
				<td><%= developer.getPassword() %>
				<td><%= developer.getEmail() %>
				<td>
					<a href="developers.jsp?action=delete&id=<%= developer.getId() %>">Delete</a>
					<a href="developers.jsp?action=select&id=<%= developer.getId() %>&username=<%= developer.getUsername() %>&password=<%= developer.getPassword() %>&email=<%= developer.getEmail() %>">Select</a>
					<a href="applications.jsp?developerId=<%= developer.getId() %>">Applications</a>
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