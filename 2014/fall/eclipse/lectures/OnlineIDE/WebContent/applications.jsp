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
	String priceStr = request.getParameter("price");
	String idStr = request.getParameter("id");
	
	Application ap = new Application();
	if("create".equals(action)) {
		double price = Double.parseDouble(priceStr);
		ap = new Application(name, price);
		dao.create(ap);
	} else if("remove".equals(action)) {
		int id = Integer.parseInt(idStr);
		dao.remove(id);
	} else if("select".equals(action)) {
		int id = Integer.parseInt(idStr);
		ap = dao.selectOne(id);
	} else if("update".equals(action)) {
		int id = Integer.parseInt(idStr);
		double price = Double.parseDouble(priceStr);
		ap = new Application(name, price);
		dao.update(id, ap);
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
			<td><input name="name" class="form-control" value="<%= ap.getName() %>"/></td>
			<td><input name="price" class="form-control" value="<%= ap.getPrice() %>"/></td>
			<td><button name="action" value="create" class="btn btn-success">Add</button>
				<button name="action" value="update" class="btn btn-primary">Update</button>
				<input  name="id" value="<%= ap.getId() %>" type="hidden"/>
			</td>
		</tr>
<%	for(Application app : applications) {
%>		<tr>
			<td><%= app.getName() %></td>
			<td>$<%= app.getPrice() %></td>
			<td><a href="applications.jsp?action=remove&id=<%= app.getId() %>" class="btn btn-danger">Delete</a>
				<a href="applications.jsp?action=select&id=<%= app.getId() %>" class="btn btn-warning">Select</a>
			</td>
		</tr>
<%	}
%>	</table>
	</form>
</div>
</body>
</html>