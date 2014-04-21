package solution;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class SignUpSheetDao {
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("SignUpSheet");
	public static void main(String[] args) {

	}
}
