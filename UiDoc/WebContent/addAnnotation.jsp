<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.io.*"%>
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
	// Select Image
	String jspPath = request.getServletPath();
	File jsp = new File(request.getSession().getServletContext().getRealPath("/screens"));
	//File directory = jsp.getParentFile();
	File[] list = jsp.listFiles();
	
	String screen = request.getParameter("screen");

%>	<ul>
<%
	for(File file : list) {
%>	<a href="addAnnotation.jsp?screen=<%=file.getName()%>"><%=file.getName()%></a>
<%	}
%>	</ul>

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