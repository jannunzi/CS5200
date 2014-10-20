package edu.neu.cs5200.experiment.weather.weather;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SearchZip
 */
@WebServlet(name = "searchZip", urlPatterns = { "/searchZip" })
public class SearchZip extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String zip = request.getParameter("zip");
		System.out.println("Zip: " + zip);
		WeatherWebServiceClient client = WeatherWebServiceClient.getInstance();
		Weather weather = client.getWeatherForZip(zip);
		request.setAttribute("weather", weather);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/weatherDetails.jsp");
		dispatcher.forward(request, response);
	}
}
