package edu.neu.cs5200.experiment.weather.user;

public class UserService {
	private static UserService instance = null;
	protected UserService() {}
	public static UserService getInstance() {
		if(instance == null)
			instance = new UserService();
		return instance;
	}
	public boolean register(User user) {
		UserDao.getInstance().create(user);
		return true;
	}
	public boolean login(User user) {
		user = UserDao.getInstance().selectByUsernameAndPassword(user.getUsername(), user.getPassword());
		return user != null;
	}	
}
