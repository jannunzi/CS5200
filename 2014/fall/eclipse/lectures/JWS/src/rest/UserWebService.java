package rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.sun.jersey.spi.container.ParamQualifier;

import model.UserDao;
import model.User;

// /rest/user
@Path("/user")
public class UserWebService {
	
	UserDao dao = new UserDao();
	
	// /rest/user
	@GET
	@Produces("application/json")
	@Path("/")
	public List<User> getAllUsers() {
		return dao.findAllUsers();
	}
	@GET
	@Produces("application/json")
	@Path("/{id}")
	public User getUserForId(@PathParam("id") int id) {
		return dao.findUserById(id);
	}
	@DELETE
	@Path("/{id}")
	public void deleteUserForId(@PathParam("id") int id) {
		dao.deleteUser(id);
	}
	@POST
	@Path("/")
	@Consumes("application/json")
	public void createUser(User newUser) {
		dao.createUser(newUser);
	}
	@PUT
	@Path("/{id}")
	@Consumes("application/json")
	public void updateUser(@PathParam("id") int id, User newUser) {
		dao.updateUser(id, newUser);
	}
}
