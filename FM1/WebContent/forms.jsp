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

	<h1>
		<a class="btn btn-default" href="developers.jsp">Back</a>
		Forms
	</h1>
	
	<form action="forms.jsp">
	
	<%
		String developerId = request.getParameter("developerId");		
		String name = request.getParameter("name");
		String id = request.getParameter("id");
		String action = request.getParameter("action");
		
		if(name == null) {
			name = "";
		}		
		if("null".equals(developerId) || developerId == null) {
			developerId = "";
		}
		
		FormDao dao = new FormDao();
		List<Form> forms = null;
		
		if("add".equals(action)) {
			Form newForm = new Form(name);
			int developerIdInt = Integer.parseInt(developerId);
			forms = dao.create(developerIdInt, newForm);
		} else if("delete".equals(action)) {
			int idInt = Integer.parseInt(id);
			forms = dao.delete(idInt);
		} else if("update".equals(action)) {
			int idInt = Integer.parseInt(id);
			Form newForm = new Form(name);
			forms = dao.update(idInt, newForm);
		}

		int developerIdInt = Integer.parseInt(developerId);
		forms = dao.readForParentId(developerIdInt);
		
	%>
		<table class="table">
			<thead>
				<tr>
					<th>Id</th>
					<th>Name</th>
					<th>Developer Id</th>
					<th>&nbsp;</th>
				</tr>
				<tr>
					<th>&nbsp;</th>
					<th><input class="form-control" name="name" value="<%= name %>"/></th>
					<th><%=developerId %></th>
					<th>
						<input type="hidden" name="id" value="<%=id%>"/>
						<input type="hidden" name="developerId" value="<%=developerId%>"/>
						<button class="btn btn-default" name="action" value="add">Add</button>
						<button class="btn btn-default" name="action" value="update">Update</button>
					</th>
				</tr>
			</thead>
			<tbody>
	<%
		for(Form form : forms) {
	%>		<tr>
				<td><%= form.getId() %></td>
				<td><%= form.getName() %></td>
				<td><%= form.getDeveloperId()%></td>
				<td>
					<a class="btn btn-default" href="forms.jsp?action=delete&id=<%= form.getId() %>&developerId=<%= form.getDeveloperId() %>">
						Delete
					</a>
					<a class="btn btn-default" href="forms.jsp?action=select&id=<%= form.getId() %>&developerId=<%= form.getDeveloperId() %>&name=<%= form.getName() %>">
						Select
					</a>
					<a class="btn btn-default" href="fields.jsp?formId=<%= form.getId() %>&developerId=<%=form.getDeveloperId()%>">Fields</a>
				</td>
			</tr>
	<%	}
	%>
			</tbody>
		</table>
	<%
	%>
	</form>	
	</div>
</body>
</html>