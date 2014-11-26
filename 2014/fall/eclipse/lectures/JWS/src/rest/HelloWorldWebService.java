package rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

// /rest/hello123
@Path("/hello123")
public class HelloWorldWebService {

// /rest/hello123/sayHello
	@GET
	@Path("/sayHello")
	public String sayHello() {
		return "Hello World!!!!!!!";
	}
	// /rest/hello123/sayHello
	@GET
	@Path("/sayGoodbye")
	public String gozjdfsjhdfjh() {
		return "Hello Goodbye!!!!!!!";
	}
	
	@GET
	@Path("/sayHello/{message}")
	public String sayHello(@PathParam("message") String msg) {
		return "Hello " + msg;
	}
	
	@GET
	@Path("/say")
	public String say(
			@QueryParam("message") String msg,
			@QueryParam("to") String receiver)
	{
		return msg + " " + receiver;
	}
	
	@GET
	@Produces("application/json")
	@Path("/getMessage")
	public Message say()
	
	{
		Message msg = new Message("Hello", "Alice");
		return msg;
	}
	@GET
	@Produces("application/json")
	@Path("/getMessages")
	public List<Message> sayLotsOfTimes()
	{
		Message msg1 = new Message("Hello", "Alice");
		Message msg2 = new Message("Goodbye", "Bob");
		Message msg3 = new Message("Hello", "Charlie");
		Message msg4 = new Message("Goodbye", "Dan");
		Message msg5 = new Message("Hello", "Edward");
		
		List<Message> msgs = new ArrayList<Message>();
		
		msgs.add(msg1);
		msgs.add(msg2);
		msgs.add(msg3);
		msgs.add(msg4);
		msgs.add(msg5);

		return msgs;
	}
	
	@DELETE
	@Path("/removeMe/{id}")
	public String removeMe(@PathParam("id") int id){
		return "Removing " + id;
	}
	
	@POST
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/createMe")
	public Message createMe(Message msg) {
		msg.setReceiver("Bob");
		return msg;
	}
	
	@PUT
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/updateMe/{id}")
	public Message updateMe(@PathParam("id") int id, Message msg) {
		msg.setReceiver("Charlie " + id);
		return msg;
	}
	
}
