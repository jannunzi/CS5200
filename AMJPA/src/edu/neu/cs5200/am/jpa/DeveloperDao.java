package edu.neu.cs5200.am.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DeveloperDao {
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("AMJPA");
	EntityManager em = null;
	public void create(Developer entity) {
		em = factory.createEntityManager();
		em.getTransaction().begin();
		em.persist(entity);
		em.getTransaction().commit();
	}
	@SuppressWarnings("unchecked")
	public List<Developer> read() {
		List<Developer> entities = new ArrayList<Developer>();
		em = factory.createEntityManager();
		try
		{
			em.getTransaction().begin();
			entities = em.createNamedQuery("findAll").getResultList();
			em.getTransaction().commit();
		} catch(Exception e) {
			em.getTransaction().rollback();
		}
		return entities;
	}
	public Developer read(int entityId) {
		Developer entity = null;
		em = factory.createEntityManager();
		em.getTransaction().begin();
		entity = em.find(Developer.class, entityId);
		em.getTransaction().commit();
		return entity;
	}
	public void update(Developer entity) {
		em = factory.createEntityManager();
		em.getTransaction().begin();
		em.find(Developer.class, entity.getId());
		em.merge(entity);
		em.getTransaction().commit();
	}
	public void delete(int id) {
		em = factory.createEntityManager();
		em.getTransaction().begin();
		Developer entity = em.find(Developer.class, id);
		em.remove(entity);
		em.getTransaction().commit();
	}
	public static void main(String[] args) {
		DeveloperDao dao = new DeveloperDao();
		Developer alice = new Developer("alice", "wonderland", "alice@gmail.com");
		dao.create(alice);
	}
}
