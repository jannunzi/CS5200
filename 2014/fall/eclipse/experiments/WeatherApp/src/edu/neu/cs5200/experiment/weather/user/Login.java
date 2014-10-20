package edu.neu.cs5200.experiment.weather.user;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.neu.cs5200.experiment.weather.weather.Weather;
import edu.neu.cs5200.experiment.weather.weather.WeatherService;

/**
 * Servlet implementation class Login
 */
@WebServlet(name = "login", urlPatterns = { "/login" })
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Login in");
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		User user = new User(username, password);
		
		UserService service = UserService.getInstance();
		
		if(service.getInstance().login(user))
		{
			WeatherService wService = WeatherService.getInstance();
			List<Weather> weathers = wService.getWeatherForUser(user);
			request.setAttribute("weathers", weathers);
//			request.setAttribute("user", );
			RequestDispatcher dispatcher = request.getRequestDispatcher("/weatherList.jsp");
			dispatcher.forward(request, response);
		}
		else
		{
			response.sendRedirect("/WeatherApp/login.jsp");
		}
		
	}
}
