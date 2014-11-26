package jga;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class UserDao
{
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("JWS_JPA");
	EntityManager em = null;
	
	public UserDao()
	{
		em = factory.createEntityManager();
	}
	
	public User createUser(User user)
	{
		em.getTransaction().begin();
		em.persist(user);
		em.getTransaction().commit();
		return user;
	}
	
	public void sponsor(User sponsor, User sponsored)
	{
		em.getTransaction().begin();
		sponsor   = em.find(User.class, sponsor.getId());
		sponsored = em.find(User.class, sponsored.getId());
		
		sponsor.getSponsored().add(sponsored);
		sponsored.getSponsors().add(sponsor);
		
		em.merge(sponsor);
		em.merge(sponsored);
		
		em.getTransaction().commit();
	}
	
	public static void main(String[] args)
	{
		UserDao dao = new UserDao();
		
		User sp1 = new User("sp1","sp1", "sp1@gmail.com", "sp1username", "sp1password", new Date());
		User sp2 = new User("sp2","sp2", "sp2@gmail.com", "sp2username", "sp2password", new Date());
		User sp3 = new User("sp3","sp3", "sp3@gmail.com", "sp3username", "sp3password", new Date());

		dao.createUser(sp1);
		dao.createUser(sp2);
		dao.createUser(sp3);
		
		User sp1d = new User("sp1d","sp1d", "sp1d@gmail.com", "spd1username", "spd1password", new Date());
		User sp2d = new User("sp2d","sp2d", "sp2d@gmail.com", "spd2username", "spd2password", new Date());
		User sp3d = new User("sp3d","sp3d", "sp3d@gmail.com", "spd3username", "spd3password", new Date());

		dao.createUser(sp1d);
		dao.createUser(sp2d);
		dao.createUser(sp3d);
		
		User both1 = new User("both1","both1", "both1d@gmail.com", "bothd1username", "bothd1password", new Date());
		User both2 = new User("both2","both2", "both2d@gmail.com", "bothd2username", "bothd2password", new Date());
		User both3 = new User("both3","both3", "both3d@gmail.com", "bothd3username", "bothd3password", new Date());

		dao.createUser(both1);
		dao.createUser(both2);
		dao.createUser(both3);
		
//		dao.sponsor(sp1, sp1d);
//		dao.sponsor(sp1, sp2d);
//		dao.sponsor(sp2, sp1d);
//		dao.sponsor(sp2, sp2d);
		
		dao.sponsor(both1, sp1d);
		dao.sponsor(both1, sp2d);
		dao.sponsor(both2, sp1d);
		dao.sponsor(both2, sp2d);
		
		dao.sponsor(sp1, both1);
		dao.sponsor(sp2, both1);
		dao.sponsor(sp1, both2);
		dao.sponsor(sp2, both2);
	}
}
