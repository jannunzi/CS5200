package solution;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class SignUpSheetDao {
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("SignUpSheet");
	EntityManager em = null;
	public void register(Organizer organizer) {
		em = factory.createEntityManager();
		em.getTransaction().begin();
		
		em.persist(organizer);
		
		em.getTransaction().commit();
		em.close();
	}
	public boolean validate(int organizer_id) {
		em = factory.createEntityManager();
		em.getTransaction().begin();

		Organizer organizer = em.find(Organizer.class, organizer_id);

		em.getTransaction().commit();
		em.close();

		if(organizer == null)
			return false;
		return true;
	}
	public static void main(String[] args) {
		SignUpSheetDao dao = new SignUpSheetDao();

		/*
		Organizer alice = new Organizer();
		alice.setFirstName("Alice");
		alice.setLastName("Wonderland");
		alice.setUsername("alice");
		alice.setPassword("queenofhearts");
		alice.setEmail("alice@wonderland.com");
		dao.register(alice);
		*/

		boolean userExists = dao.validate(1);
		if(userExists)
			System.out.println("User Exists");
		else
			System.out.println("User Does Not Exists");

	}
}
