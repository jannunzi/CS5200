package edu.neu.db.jws.fish;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/fish")
public class FishService {

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	public void createFish(Fish newFish) {
		System.out.println("Creating fish");
		System.out.println(newFish.id);
		System.out.println(newFish.name);
		System.out.println(newFish.length);
		System.out.println(newFish.weight);
	}
	
	@PUT
	@Path("/{id}")
	public void updateFish(@PathParam("id") int id) {
		System.out.println("Updating fish " + id);
	}
	
	@DELETE
	@Path("/{id}")
	public void deleteFish(@PathParam("id") int id) {
		System.out.println("Deleting fish " + id);
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Fish readOne(@PathParam("id") int id) {
		Fish fish = new Fish();
		
		fish.id = id;
		fish.name = "Fish " + id;
		fish.length = (float)id * 123;
		fish.weight = (float)id * 234;
		
		return fish;
	}

	
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Fish> readAll() {
		List<Fish> fishes = new ArrayList<Fish>();

		Fish f1 = new Fish();
		f1.id = 123;
		f1.name = "Fish 123";
		f1.length = 12.23f;
		f1.weight = 21.32f;
		fishes.add(f1);

		Fish f2 = new Fish();
		f2.id = 234;
		f2.name = "Fish 234";
		f2.length = 12.23f;
		f2.weight = 21.32f;
		fishes.add(f2);
		
		return fishes;
	}
}
