package edu.neu.db.jws.example;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/page")
public class PagesService {
	
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("Jersey");
	
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Page> getAllPages() {
		List<Page> pages = new ArrayList<Page>();
		
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		
		pages = em.createNamedQuery("findAllPages").getResultList();
		
		em.getTransaction().commit();
		em.close();
		
		return pages;
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Page getPageById(@PathParam("id") int id) {
		Page page = null;
		
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		
		page = em.find(Page.class, id);
		
		em.getTransaction().commit();
		em.close();

		return page;
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	public void createPage(Page page) {
		System.out.println(page.getName());
	}
}
