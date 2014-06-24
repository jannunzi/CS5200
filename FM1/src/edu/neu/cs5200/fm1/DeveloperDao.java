package edu.neu.cs5200.fm1;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class DeveloperDao {
	
	private Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection(
			   "jdbc:postgresql://localhost:5432/fm1","postgres", "password");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}

	public Developer read(int id) {
		Developer developer = null;
		Connection connection = getConnection();
		try {
			PreparedStatement stmt = connection.prepareStatement("select * from Developer where id = ?");
			stmt.setInt(1, id);
			ResultSet results = stmt.executeQuery();
			if(results.next()) {
				developer = new Developer(
					results.getInt("id"),
					results.getString("username"),
					results.getString("email")
				);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConnection(connection);
		}
		return developer;
	}
	
	public List<Developer> read() {
		List<Developer> developers = new ArrayList<Developer>();
		Connection connection = getConnection();
		try {
			PreparedStatement stmt = connection.prepareStatement("select * from developer");
			ResultSet results = stmt.executeQuery();
			Developer developer = null;
			while(results.next()) {
				developer = new Developer(
					results.getInt("id"),
					results.getString("username"),
					results.getString("email")
				);
				developers.add(developer);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConnection(connection);
		}
		return developers;
	}
	
	public List<Developer> read(Connection connection) {
		List<Developer> developers = new ArrayList<Developer>();
		if(connection == null) {
			connection = getConnection();
		}
		try {
			PreparedStatement stmt = connection.prepareStatement("select * from developer");
			ResultSet results = stmt.executeQuery();
			Developer developer = null;
			while(results.next()) {
				developer = new Developer(
					results.getInt("id"),
					results.getString("username"),
					results.getString("email")
				);
				developers.add(developer);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return developers;
	}
	
	public List<Developer> delete(int id) {
		List<Developer> developers = new ArrayList<Developer>();
		Connection connection = getConnection();
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement("delete from developer where id=?");
			stmt.setInt(1, id);
			stmt.execute();
			
			developers = read(connection);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConnection(connection);
		}
		
		return developers;
	}
	
	public List<Developer> create(Developer developer) {
		List<Developer> developers = new ArrayList<Developer>();
		Connection connection = getConnection();
		try {
			PreparedStatement stmt = connection.prepareStatement("insert into developer (username, email) values (?, ?)");
			stmt.setString(1, developer.getUsername());
			stmt.setString(2, developer.getEmail());
			stmt.execute();

			developers = read(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(connection);
		}
		return developers;
	}
	
	public List<Developer> update(int id, Developer newDeveloper) {
		List<Developer> developers = new ArrayList<Developer>();
		Connection connection = getConnection();
		try {
			PreparedStatement stmt = connection.prepareStatement("update developer set username=?, email=? where id=?");
			stmt.setString(1, newDeveloper.getUsername());
			stmt.setString(2, newDeveloper.getEmail());
			stmt.setInt(3, id);
			stmt.execute();

			developers = read(connection);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConnection(connection);
		}
		return developers;
	}
	
	private void closeConnection(Connection connection) {
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		DeveloperDao dao = new DeveloperDao();

		dao.delete(1);
		
		Developer update = new Developer(321, "charlie", "charlie@gmail.com");
		dao.update(2, update);
		
		Developer dev = dao.read(2);
		System.out.println(dev);
		
		List<Developer> developers = dao.read();
		for(Developer developer : developers) {
			System.out.println(developer);
		}
		/*
		Developer bob = new Developer(234, "bob", "bob@gmail.com");
		List<Developer> developers = dao.createDeveloper(bob);
		for(Developer developer : developers) {
			System.out.println(developer);
		}
		*/
	}

}
