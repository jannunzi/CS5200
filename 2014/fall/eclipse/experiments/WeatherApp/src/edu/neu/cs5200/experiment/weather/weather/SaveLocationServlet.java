package edu.neu.cs5200.experiment.weather.weather;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "saveLocation", urlPatterns = { "/saveLocation" })
public class SaveLocationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String locationName = request.getParameter("locationName");
		String zip = request.getParameter("zip");
	}

}
