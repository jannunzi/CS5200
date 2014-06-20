<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="edu.neu.cs5200.rotten1.*,java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="css/bootstrap.css"/>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<h1>Rotten 1</h1>

	<form action="rotten1.jsp">
		<%
			MovieDao dao = new MovieDao();
		
			String action = request.getParameter("action");
			String idStr = request.getParameter("id");
			String movieId = request.getParameter("movieId");
			String movieTitle = request.getParameter("movieTitle");
			
			String selectedMovieId = "";
			String selectedMovieTitle = "";
			
			if("add".equals(action)) {
				Movie movie = new Movie(movieId, movieTitle);
				dao.createMovie(movie);
			} else if("update".equals(action)) {
				int id = Integer.parseInt(idStr);
				Movie updatedMovie = new Movie(movieId, movieTitle);
				dao.updateMovie(id, updatedMovie);
			} else if("select".equals(action)) {
				int id = Integer.parseInt(idStr);
				Movie selectedMovie = dao.readMovieById(id);
				selectedMovieId = selectedMovie.getMovieId();
				selectedMovieTitle = selectedMovie.getTitle();
			} else if("delete".equals(action)) {
				int id = Integer.parseInt(idStr);
				dao.deleteMovie(id);
			}
		%>
		
		<table class="table">
			<tr>
				<th>	Movie Id
				</th>
				<th>	Movie Title
				</th>
				<th>
				</th>
			</tr>
			<tr>
				<td>	<input name="movieId" class="form-control" value="<%=selectedMovieId%>"/>
						<input name="id" type="hidden" value="<%=idStr%>"/>
				</td>
				<td>	<input name="movieTitle" class="form-control" value="<%=selectedMovieTitle%>"/>
				</td>
				<td>	
						<button class="btn btn-primary" name="action" value="update">Update</button>
						<button class="btn btn-success" name="action" value="add">Add</button>
				</td>
			</tr>

		<%		
			List<Movie> allMovies = dao.readAllMovies();
			for(Movie movie : allMovies) {%>
				<tr>
					<td>	<%= movie.getMovieId() %>
					</td>
					<td>	<%= movie.getTitle() %>
					</td>
					<td>	<a class="btn btn-primary" href="rotten1.jsp?action=select&id=<%=movie.getId()%>">Select</a>
							<a class="btn btn-danger" href="rotten1.jsp?action=delete&id=<%=movie.getId()%>">Delete</a>
					</td>
				</tr>
		<%	}
		%>
		</table>
	</form>
</body>
</html>