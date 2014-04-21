package solution;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class SignUpSheetDao {
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("SignUpSheet");
	EntityManager em = null;
	
	public static void main(String[] args) {

	}
}
