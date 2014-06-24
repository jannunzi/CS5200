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

	<form action="fields.jsp">
	<div class="container">
		<%
			String formId = request.getParameter("formId");
			String developerId = request.getParameter("developerId");
		%>
		<h1>
			<a href="forms.jsp?developerId=<%= developerId %>" class="btn btn-default">Back</a>
			Fields
		</h1>
		<%
			String id = request.getParameter("id");
			String name = request.getParameter("name");
			String fieldType = request.getParameter("fieldType");
			
			FieldDao dao = new FieldDao();
			int formIdInt = Integer.parseInt(formId);
			List<Field> fields = dao.readForParentId(formIdInt);
		%>
		<table class="table">
			<thead>
				<tr>
					<th>Id</th>
					<th>Name</th>
					<th>Default Value</th>
					<th>Type</th>
					<th>Form Id</th>
					<th>&nbsp;</th>
				</tr>
				<tr>
					<th>&nbsp;</th>
					<th><input name="name" class="form-control"/></th>
					<th><input name="defaultValue" class="form-control"/></th>
					<th>
						<select class="form-control">
							<option value="TEXT">Text</option>
							<option value="NUMBER">Number</option>
							<option value="TEXTAREA">Text Area</option>
							<option value="DATE">Date</option>
						</select>
					</th>
					<th>&nbsp;</th>
					<th>
						<button class="btn btn-default" name="action" value="add">Add</button>
						<button class="btn btn-default" name="action" value="update">Update</button>
					</th>
				</tr>
			</thead>
			<tbody>
			</tbody>
		</table>
	</div>
	</form>

</body>
</html>