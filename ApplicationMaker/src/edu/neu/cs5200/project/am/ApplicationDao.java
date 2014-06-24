package edu.neu.cs5200.project.am;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ApplicationDao {
	Connection connection = null;
	DataSource dataSource = null;
	PreparedStatement statement = null;
	ResultSet results = null;
	
	public void create(int parentId, Application entity) {
		try {
			connection = dataSource.getConnection();
			statement = connection.prepareStatement("insert into application (name, description, developerId) values (?,?,?)");
			statement.setString(1, entity.getName());
			statement.setString(2, entity.getDescription());
			statement.setInt(3, parentId);
			statement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public List<Application> read(int parentId) {
		List<Application> entities = new ArrayList<Application>();
		try {
			connection = dataSource.getConnection();
			statement = connection.prepareStatement("select * from application where developerId=?");
			statement.setInt(1, parentId);
			results = statement.executeQuery();
			while(results.next()) {
				Application entity = new Application(results.getInt("id"), results.getString("name"), results.getString("description"), results.getInt("developerId"));
				entities.add(entity);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return entities;
	}
	
	public void delete(int id) {
		try {
			connection = dataSource.getConnection();
			statement = connection.prepareStatement("delete from application where id=?");
			statement.setInt(1, id);
			statement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void update(int id, Application newEntity) {
		try {
			connection = dataSource.getConnection();
			statement = connection.prepareStatement("update application set name=?, description=? where id=?");
			statement.setString(1, newEntity.getName());
			statement.setString(2, newEntity.getDescription());
			statement.setInt(3, id);
			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public ApplicationDao() {
		try {
			Context jndi = new InitialContext();
			dataSource = (DataSource) jndi.lookup("java:comp/env/jdbc/am");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
