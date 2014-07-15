package edu.neu.cs5200.am.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ApplicationDao {
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("AMJPA");
	EntityManager em = null;
	public void create(Application entity) {
		em = factory.createEntityManager();
		em.getTransaction().begin();
		em.persist(entity);
		em.getTransaction().commit();
	}
	@SuppressWarnings("unchecked")
	public List<Application> read() {
		List<Application> entities = new ArrayList<Application>();
		em = factory.createEntityManager();
		em.getTransaction().begin();
		entities = em.createNamedQuery("findAllApplications").getResultList();
		em.getTransaction().commit();
		return entities;
	}
	public void update(Application entity) {
		em = factory.createEntityManager();
		em.getTransaction().begin();
		em.find(Application.class, entity.getId());
		em.merge(entity);
		em.getTransaction().commit();
	}
	public void delete(int id) {
		em = factory.createEntityManager();
		em.getTransaction().begin();
		Application entity = em.find(Application.class, id);
		em.remove(entity);
		em.getTransaction().commit();
	}
}
