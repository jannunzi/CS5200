package example;

import java.io.*;
import java.util.*;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.*;
import javax.xml.transform.*;
import javax.xml.transform.stream.*;
import javax.persistence.*;
import javax.print.attribute.standard.Media;

@Path("/director")
public class SolutionDao {
	
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("XSLT_JPA");
	EntityManager em = null;
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Director findDirector(@PathParam("id") int directorId) {
		Director director = null;
		
		em = factory.createEntityManager();
		em.getTransaction().begin();
		
		director = em.find(Director.class, directorId);
		
		em.getTransaction().commit();
		em.close();
		
		return director;
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Director> removeDirector(@PathParam("id") int directorId) {
		List<Director> directors = new ArrayList<Director>();

		Director director = null;
		
		em = factory.createEntityManager();
		em.getTransaction().begin();
		
		director = em.find(Director.class, directorId);
		em.remove(director);
		
		Query query = em.createNamedQuery("findAllDirectors");
		directors = query.getResultList();
		
		em.getTransaction().commit();
		em.close();
		
		return directors;
	}
	
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Director> findAllDirectors() {
		List<Director> directors = new ArrayList<Director>();
		
		em = factory.createEntityManager();
		em.getTransaction().begin();
		
		Query query = em.createNamedQuery("findAllDirectors");
		directors = query.getResultList();
		
		em.getTransaction().commit();
		em.close();

		return directors;
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<Director> createDirector(Director director) {
		List<Director> directors = new ArrayList<Director>();

		em = factory.createEntityManager();
		em.getTransaction().begin();

		em.persist(director);
		Query query = em.createNamedQuery("findAllDirectors");
		directors = query.getResultList();
		
		em.getTransaction().commit();
		em.close();
		return directors;
	}
	
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<Director> updateDirector(@PathParam("id") int directorId, Director director) {
		List<Director> directors = new ArrayList<Director>();

		em = factory.createEntityManager();
		em.getTransaction().begin();

		director.setId(directorId);
		em.merge(director);
		
		Query query = em.createNamedQuery("findAllDirectors");
		directors = query.getResultList();
		
		em.getTransaction().commit();
		em.close();
		return directors;
	}
	
	public void exportDirectorsToXmlFile(Directors directors, String xmlFileName) {
		File xmlFile = new File(xmlFileName);
		try {
			JAXBContext jaxb = JAXBContext.newInstance(Directors.class);
			Marshaller marshaller = jaxb.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.marshal(directors, xmlFile);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void convertXmlFileToOutputFile(
			String directorsXmlFileName,
			String outputFileName,
			String xsltFileName)
	{
		File inputXmlFile = new File(directorsXmlFileName);
		File outputXmlFile = new File(outputFileName);
		File xsltFile = new File(xsltFileName);
		
		StreamSource source = new StreamSource(inputXmlFile);
		StreamSource xslt    = new StreamSource(xsltFile);
		StreamResult output = new StreamResult(outputXmlFile);
		
		TransformerFactory factory = TransformerFactory.newInstance();
		try {
			Transformer transformer = factory.newTransformer(xslt);
			transformer.transform(source, output);
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		SolutionDao dao = new SolutionDao();
		
//		Director spielberg = new Director();
//		spielberg.setFirstName("Steven");
//		spielberg.setLastName("Spielberg");
//		dao.createDirector(spielberg);
//		Director s = new Director();
//		s.setFirstName("Stephen");
//		s.setLastName("Spielbergo");
//		dao.updateDirector(5, s);
	
		dao.removeDirector(9);
		
		Director director = dao.findDirector(1);
		
//		System.out.println(director.getFirstName());
		
		List<Director> directors = dao.findAllDirectors();
		for(Director dir:directors) {
//			System.out.println(dir.getFirstName());
		}
		
		Directors theDirectors = new Directors();
		theDirectors.setDirectors(directors);
		
		dao.exportDirectorsToXmlFile(theDirectors, "xml/directors.xml");
		
//		dao.convertXmlFileToOutputFile("xml/directors.xml", "xml/directors.html", "xml/directors2html.xslt");
	//	dao.convertXmlFileToOutputFile("xml/directors.xml", "xml/movies.html", "xml/directors2movies.xslt");
	}
}
