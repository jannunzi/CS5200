<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.io.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
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
	<a href="selectScreen.jsp?screen=<%=file.getAbsolutePath()%>"><%=file.getName()%></a>
<%}
%>	</ul>

<img src="<%= screen %>"/>

</body>
</html>