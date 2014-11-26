package model;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class UserDao {
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("JWS");
	EntityManager em = null;
	public UserDao() {
		em = factory.createEntityManager();
	}
	public void createUser(User user) {
		em.getTransaction().begin();
		em.persist(user);
		em.getTransaction().commit();
	}
	public User findUserById(int id) {
		em.getTransaction().begin();
		Query query = em.createQuery("select u from User u where u.id=:id");
		query.setParameter("id", id);
		User user = (User)query.getSingleResult();
		em.getTransaction().commit();
		return user;
	}
	public List<User> findAllUsers() {
		em.getTransaction().begin();
		Query query = em.createQuery("select u from User u");
		List<User> users = (List<User>)query.getResultList();
		em.getTransaction().commit();
		return users;
	}
	public void updateUser(int id, User user) {
		em.getTransaction().begin();
		User u = em.find(User.class, id);
		u.setEmail(user.getEmail());
		u.setUsername(user.getUsername());
		em.merge(u);
		em.getTransaction().commit();
	}
	public void deleteUser(int id) {
		em.getTransaction().begin();
		User user = em.find(User.class, id);
		em.remove(user);
		em.getTransaction().commit();
	}
	public static void main(String[] args) {
		UserDao dao = new UserDao();
		User user1 = new User(123,"alice", "alice@wonderland.com");
		dao.createUser(user1);
		
		user1 = dao.findUserById(1);
		System.out.println(user1.getEmail());
		
		List<User> users = dao.findAllUsers();
		System.out.println(users.get(0).getUsername());
		
		User newUser = new User(123, "ALICE", "alice@gmail.com");
		dao.updateUser(1, newUser);
		
		User user = dao.findUserById(1);
		System.out.println(user.getEmail());
		
		dao.deleteUser(1);
	}
}
