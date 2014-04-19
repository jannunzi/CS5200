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
String tableName = request.getParameter("table");
%>	<ul>
<%
for(File file : list) {
%>
	<a id="<%=file.getName()%>" href="#" class="selectImage"><%=file.getName()%></a>
<%}
%>	</ul>

	<select class="tableSelector">
		<option></option>
	</select>

	<select class="fieldSelector">
		<option></option>
	</select>

<br/>

<img src="screens/<%=screen%>" class="screen"/>


<form action="selectField.jsp" class="form">
	<input type="hidden" name="table" class="table"/>
	<input type="hidden" name="field" class="field"/>
</form>

<script type="text/javascript">
	$(function(){
		populateTables();
		$(".selectImage").click(selectImage);
		$(".screen").click(screenClick);
		$(".tableSelector").change(tableSelected);
	});
	
	function populateTables() {
		$.ajax({
			url: "rest/table",
			success: function(response) {
				var select = $(".tableSelector");
				select.empty();
				for(var r in response) {
					$("<option>")
						.html(response[r].name)
						.appendTo(select);
				}
			}
		});
	}
	
	function selectImage(event) {
		var id = $(this).attr("id");
		$(".screen").attr("src", "screens/"+id);
	}
	
	function screenClick(event) {
		$("<div>Hello</div>")
			.css({width: 200, height: 200, position: "absolute", left: 200, top: 200, "border" : "5px solid red"})
			.draggable()
			.resizable()
			.appendTo("body");
	}
	
	function tableSelected(event) {
		var tableName = $(this).val();
		$.ajax({
			url: "rest/table/"+tableName,
			success: function(response) {
				var select = $(".fieldSelector");
				select.empty();
				for(var r in response) {
					$("<option>")
						.html(response[r].name)
						.appendTo(select);
				}
			}
		});

	}
</script>

</body>
</html>