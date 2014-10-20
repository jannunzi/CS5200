package edu.neu.cs5200.experiment.weather.weather;

import java.util.ArrayList;
import java.util.List;

import edu.neu.cs5200.experiment.weather.user.User;

public class WeatherService {
	private static WeatherService instance = null;
	protected WeatherService(){}
	public static WeatherService getInstance() {
		if(instance == null)
			instance = new WeatherService();
		return instance;
	}
	public List<Weather> getWeatherForUser(User user) {
		List<Weather> ws = new ArrayList<Weather>();
		WeatherDao dao = WeatherDao.getInstance();
		ws = dao.selectWeatherForUsername(user.getUsername());
		return ws;
	}
	public Weather getWeatherForZip(String zip) {
		Weather w = new Weather();
		return w;
	}
}
