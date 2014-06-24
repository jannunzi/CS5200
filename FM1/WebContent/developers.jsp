<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="edu.neu.cs5200.fm1.*,java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link href="css/bootstrap.css" rel="stylesheet"/>
</head>
<body>
	<div class="container">
	<h1>Developers</h1>
	
	<form action="developers.jsp">
	
	<%
		String action = request.getParameter("action");
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String id = request.getParameter("id");
		Developer newDeveloper = null;
		
		if(username == null) {
			username = "";
		}
		if(email == null) {
			email = "";
		}
		
		DeveloperDao dao = new DeveloperDao();
		
		if("add".equals(action)) {
			newDeveloper = new Developer(username, email);
			dao.create(newDeveloper);
		} else if("delete".equals(action)) {
			int idInt = Integer.parseInt(id);
			dao.delete(idInt);
		} else if("update".equals(action)) {
			newDeveloper = new Developer(username, email);
			int idInt = Integer.parseInt(id);
			dao.update(idInt, newDeveloper);
		}
	
		List<Developer> developers = dao.read();
	%>
		<table class="table">
			<thead>
				<tr>
					<th>Id</th>
					<th>Username</th>
					<th>Email</th>
					<th>&nbsp;</th>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><input class="form-control" name="username" value="<%=username%>"/></td>
					<td><input class="form-control" name="email" value="<%=email%>"/></td>
					<td>
						<input name="id" value="<%=id%>" type="hidden"/>
						<button class="btn btn-default" name="action" value="add">Add</button>
						<button class="btn btn-default" name="action" value="update">Update</button>
					</td>
				</tr>
			</thead>
			<tbody>
	<%	for(Developer developer : developers) {
	%>		<tr>
				<td><%= developer.getId() %></td>
				<td><%= developer.getUsername() %></td>
				<td><%= developer.getEmail() %></td>
				<td>
					<a class="btn btn-default" href="developers.jsp?action=delete&id=<%=developer.getId()%>">Delete</a>
					<a class="btn btn-default" href="developers.jsp?action=select&id=<%=developer.getId()%>&username=<%=developer.getUsername()%>&email=<%=developer.getEmail()%>">Select</a>
					<a class="btn btn-default" href="forms.jsp?developerId=<%=developer.getId()%>">Forms</a>
				</td>
			</tr>	
	<%	}
	%>		</tbody>
		</table>

	</form>	
	</div>
</body>
</html>