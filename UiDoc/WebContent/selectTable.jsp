<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.io.*,com.atc.database.explore.*, java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="js/jquery-ui-1.10.4.custom.min.css"/>
<script type="text/javascript" src="js/jquery-1.10.2.js"></script>
<script type="text/javascript" src="js/jquery-ui-1.10.4.custom.min.js"></script>
</head>
<body>

<%
String jspPath = request.getServletPath();
File jsp = new File(request.getSession().getServletContext().getRealPath("/screens"));
//File directory = jsp.getParentFile();
File[] list = jsp.listFiles();
String screen = request.getParameter("screen");
%>	<ul>
<%
for(File file : list) {
%>
	<a href="selectTable.jsp?screen=<%=file.getName()%>"><%=file.getName()%></a>
<%}
%>	</ul>

	<select>
<%
	Database db = new Database("ShareGen",
			"com.microsoft.jdbc.sqlserver.SQLServerDriver",
			"microsoft","sqlserver","QCSMN01","1433",
			"semaan_app_user","qcdb01",null);
	TableService svc = new TableService(db);

	TableService tableService = new TableService(db);
	List<Table> tables = tableService.getTables();
	for(Table table: tables) {
%>		<option><%= table.name %></option>
<%	}
%>	</select>

<br/>

<img src="screens/<%=screen%>" class="screen"/>

<script type="text/javascript">
	$(function(){
		$(".screen").click(screenClick);
	});
	
	function screenClick(event) {
		$("<div>Hello</div>")
			.css({width: 200, height: 200, position: "absolute", left: 200, top: 200, "border" : "5px solid red"})
			.draggable()
			.resizable()
			.appendTo("body");
	}
</script>

</body>
</html>