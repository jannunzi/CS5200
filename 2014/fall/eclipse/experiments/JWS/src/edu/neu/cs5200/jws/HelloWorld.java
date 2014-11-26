package edu.neu.cs5200.jws;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.sun.xml.internal.ws.developer.MemberSubmissionAddressing;

@Path("/hello")
public class HelloWorld {
	@GET
	@Path("/")
	public String sayHello() {
		return "Hello World!";
	}
	@GET
	@Path("/name/{name}")
	public String sayHello(@PathParam("name") String name) {
		return "Hello " + name;
	}
	@GET
	@Path("/to")
	public String sayHelloTo(@QueryParam("name") String name) {
		return "Hello " + name;
	}
	@GET
	@Produces("application/json")
	@Path("/json")
	public User getUser() {
		User user = new User("Alice", "Wonderland", 123.321);
		return user;
	}
	@GET
	@Produces("application/json")
	@Path("/jsonQParams")
	public User getUserQParams(@QueryParam("firstName") String first,
		@QueryParam("lastName") String last, @QueryParam("salary") double salary)
	{
		User user = new User(first, last, salary);
		return user;
	}
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	@Path("/user")
	public User postUser(User user) {
		return user;
	}
	@PUT
	@Consumes("application/json")
	@Produces("application/json")
	@Path("/user/{username}")
	public User updateUser(@PathParam("username") String username, User user) {
		user.username = username;
		return user;
	}
}
