package edu.neu.cs5200.ide.jpa;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DeveloperDao {

	EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpasec2");
	EntityManager em = null;
	
	public DeveloperDao() {
		em = factory.createEntityManager();
	}
	
	public void createDeveloper(Developer developer) {
		em.getTransaction().begin();
		em.persist(developer);
		em.getTransaction().commit();
	}
	
	public void changeDevelopersLastName(Developer developer, String newLastName) {
		em.getTransaction().begin();
		developer.setLast(newLastName);
		em.merge(developer);
		em.getTransaction().commit();		
	}
	
	public Developer findDeveloperById(int id) {
		em.getTransaction().begin();
		Developer developer = em.find(Developer.class, id);
		em.getTransaction().commit();
		return developer;
	}
	
	public void removeDeveloperById(int id) {
		em.getTransaction().begin();
		Developer developer = em.find(Developer.class, id);
		em.remove(developer);
		em.getTransaction().commit();
	}
	
	public static void main(String[] args) {
		DeveloperDao dao = new DeveloperDao();
		
		Developer d1 = new Developer("Alice", "Wonderland", "alice@wonderland.com", new Date());
		Developer d2 = new Developer("Bob", "Wonderland", "alice@wonderland.com", new Date());
		Developer d3 = new Developer("Charlie", "Wonderland", "alice@wonderland.com", new Date());
		Developer d4 = new Developer("Dan", "Wonderland", "alice@wonderland.com", new Date());

		dao.createDeveloper(d1);
		dao.createDeveloper(d2);
		dao.createDeveloper(d3);
		dao.createDeveloper(d4);
		
		dao.changeDevelopersLastName(d3, "Brown");
		
		Developer dan = dao.findDeveloperById(4);
		System.out.println(dan.getFirst());
		
		dao.removeDeveloperById(3);
	}

}
