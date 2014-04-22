package solution;

import java.util.Date;
import java.util.List;

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
	public List<Sheet> createSheet(int organizer_id, Sheet sheet) {
		em = factory.createEntityManager();
		em.getTransaction().begin();

		Organizer organizer = em.find(Organizer.class, organizer_id);
		organizer.getSheets().add(sheet);
		sheet.setOrganizer(organizer);
		em.merge(organizer);
		List<Sheet> sheets = organizer.getSheets();

		em.getTransaction().commit();
		em.close();
		
		return sheets;
	}
	public List<Sheet> getAllSheets(int organizer_id) {
		em = factory.createEntityManager();
		em.getTransaction().begin();

		Organizer organizer = em.find(Organizer.class, organizer_id);
		List<Sheet> sheet = organizer.getSheets();

		em.getTransaction().commit();
		em.close();
		
		return sheet;
	}
	public List<TimeSlot> addTimeSlot(int sheet_id, TimeSlot slot) {
		em = factory.createEntityManager();
		em.getTransaction().begin();

		Sheet sheet = em.find(Sheet.class, sheet_id);
		sheet.getTimeSlots().add(slot);
		List<TimeSlot> slots = sheet.getTimeSlots();
		slot.setSheet(sheet);
		em.merge(sheet);

		em.getTransaction().commit();
		em.close();
		
		return slots;
	}
	public static void main(String[] args) {
		SignUpSheetDao dao = new SignUpSheetDao();

		/*
		// 1) create an organizer
		Organizer alice = new Organizer();
		alice.setFirstName("Alice");
		alice.setLastName("Wonderland");
		alice.setUsername("alice");
		alice.setPassword("queenofhearts");
		alice.setEmail("alice@wonderland.com");
		dao.register(alice);

		// 2) validate an organizer
		boolean userExists = dao.validate(1);
		if(userExists)
			System.out.println("User Exists");
		else
			System.out.println("User Does Not Exists");
		*/

		// 3) Create a sheet for an organizer ID with an address and display all sheets
		/*
		Address address = new Address();
		address.setStreet1("Las Canteras");
		address.setCity("Palm Spring");
		address.setState("CA");
		address.setZip("12344-4321");
		address.setStreet2(null);
		
		Sheet sheet = new Sheet();
		sheet.setDescription("Web Project Demo Description");
		sheet.setEventDate(new Date());
		sheet.setName("Web Project Demo");
		sheet.setWhere(address);
		
		address.setSheet(sheet);	// <-- ojo

		List<Sheet> sheets = dao.createSheet(1, sheet);
		
		for(Sheet s : sheets) {
			System.out.println(s.getName());
		}
		*/
		// 4) Get all sheets for an organizer
		/*
		List<Sheet> sheets = dao.getAllSheets(1);
		
		for(Sheet s : sheets) {
			System.out.println(s.getName());
		}
		*/
		// 5) Add TimeSlots to Sheet
		TimeSlot slot = new TimeSlot();
		slot.setNotes("My awsome project 2");
		slot.setSlotDate(new Date());
		slot.setWho("Team B");
		
		List<TimeSlot> slots = dao.addTimeSlot(3, slot);
		for(TimeSlot s : slots) {
			System.out.println(s.getNotes());
		}
	}
}
