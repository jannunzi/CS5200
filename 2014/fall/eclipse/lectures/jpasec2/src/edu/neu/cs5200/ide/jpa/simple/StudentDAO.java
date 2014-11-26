package edu.neu.cs5200.ide.jpa.simple;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class StudentDAO {

	EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpasec2");
	EntityManager em = null;
	
	public StudentDAO() {
		em = factory.createEntityManager();
	}
	
	public Student createStudent(Student student) {
		em.getTransaction().begin();
		em.persist(student);
		em.getTransaction().commit();
		return student;
	}
	
	public Student findById(int id) {
		em.getTransaction().begin();
		Student student = em.find(Student.class, id);
		em.getTransaction().commit();
		return student;
	}
	
//	public Student findByName(String name) {
	public List<Student> findByName(String name) {
		em.getTransaction().begin();
		Query q = em.createQuery("select s from Student s where s.name = :name");
		q.setParameter("name", name);
//		Student student = (Student) q.getSingleResult();
		List<Student> students = q.getResultList();
		em.getTransaction().commit();
//		return student;
		return students;
	}
	
	public List<Student> findByNameUsingNamedQuery(String name) {
		em.getTransaction().begin();
		Query q = em.createNamedQuery("Student.findStudentById");
		q.setParameter("name", name);
		List<Student> students = q.getResultList();
		em.getTransaction().commit();
		return students;
	}
	
	public List<Student> findAll() {
		em.getTransaction().begin();
		Query q = em.createNamedQuery("Student.findAll");
		List<Student> students = q.getResultList();
		em.getTransaction().commit();
		return students;
	}
	
	public void removeStudentById(int id) {
		em.getTransaction().begin();
		Student student = em.find(Student.class, id);
		em.remove(student);
		em.getTransaction().commit();
	}
	
	public Student updateStudentNameById(int id, String newName) {
		em.getTransaction().begin();
		Student student = em.find(Student.class, id);
		student.setName(newName);
		em.merge(student);
		em.getTransaction().commit();
		return student;
	}
	
	public static void main(String[] args) {
		StudentDAO dao = new StudentDAO();
		
		Student s1 = new Student(321, "Dan", new Date());
		s1 = dao.createStudent(s1);
		System.out.println(s1.getId());
		
		Student s2 = dao.findById(1);
		System.out.println(s2);
		
		Student s3 = dao.updateStudentNameById(1, "Greg");
		System.out.println(s3);

		Student sa = new Student(321, "Greg", new Date());
		Student sb = new Student(321, "Greg", new Date());
		Student sc = new Student(321, "Charlie", new Date());
		Student sd = new Student(321, "Stephen", new Date());
		
		dao.createStudent(sa);
		dao.createStudent(sb);
		dao.createStudent(sc);
		dao.createStudent(sd);
		
//		Student s4 = dao.findByName("Greg");
//		List<Student> s4 = dao.findByName("Greg");
		List<Student> s4 = dao.findByNameUsingNamedQuery("Greg");

		System.out.println(s4);
		
		List<Student> all = dao.findAll();
		System.out.println(all);
		
//		dao.removeStudentById(1);
	}

}
