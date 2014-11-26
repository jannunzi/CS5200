package edu.neu.cs5200.ide.jpa.inheritance.simple;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class VehicleDAO {

	EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpasec2");
	EntityManager em = null;
	
	public VehicleDAO() {
		em = factory.createEntityManager();
	}
	
	public Vehicle createVehicle(Vehicle vehicle) {
		em.getTransaction().begin();
		em.persist(vehicle);
		em.getTransaction().commit();
		return vehicle;
	}
	
	public static void main(String[] args) {
		VehicleDAO dao = new VehicleDAO();
		
//		Vehicle v1 = new Vehicle("V1");
//		dao.createVehicle(v1);
//		
//		Car c1 = new Car("C1", 4);
//		dao.createVehicle(c1);

		
		
	}

}
