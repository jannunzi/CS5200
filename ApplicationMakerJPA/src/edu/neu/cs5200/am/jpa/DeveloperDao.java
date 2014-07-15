package edu.neu.cs5200.am.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DeveloperDao {
	
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("ApplicationMakerJPA");
	EntityManager em = null;
	
	public void create(Developer developer) {
		em = factory.createEntityManager();
		try
		{
			em.getTransaction().begin();
			em.persist(developer);
			em.getTransaction().commit();
		} catch(Exception e) {
			em.getTransaction().rollback();
		}
	}

	public Developer read(int id) {
		em = factory.createEntityManager();
		Developer developer = em.find(Developer.class, id);
		return developer;
	}
	
	public List<Developer> readAll() {
		List<Developer> developers = new ArrayList<Developer>();
		
		return developers;
	}
	
	public static void main(String[] args) {
		DeveloperDao dao = new DeveloperDao();
	
		Developer developer = dao.read(2);
		System.out.println(developer.getUsername());
		
//		Developer developer = new Developer(123, "bob", "marley", "bob@gmail.com");
//		dao.create(developer);
		
	}
}
