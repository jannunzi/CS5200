package edu.neu.cs5200.fm1;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class FormDao {
	
	DataSource dataSource = null;
	Connection connection = null;
	PreparedStatement statement = null;
	ResultSet results = null;
	
	public FormDao() {
		try {
			Context ctx = new InitialContext();
			dataSource = (DataSource)ctx.lookup("java:comp/env/jdbc/fm1");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	public List<Form> read() {
		List<Form> forms = new ArrayList<Form>();
		try {
			connection = dataSource.getConnection();
			forms = read(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return forms;
	}
	
	public List<Form> readForParentId(int parentId) {
		List<Form> forms = new ArrayList<Form>();
		try {
			connection = dataSource.getConnection();
			statement = connection.prepareStatement("select * from form where developer_id=?");
			statement.setInt(1, parentId);
			results = statement.executeQuery();
			while(results.next()) {
				Form form = new Form(
					results.getInt("id"),
					results.getString("name"),
					parentId
				);
				forms.add(form);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return forms;
	}
	
	public List<Form> update(int formId, Form newForm) {
		List<Form> forms = new ArrayList<Form>();
		try {
			connection = dataSource.getConnection();
			statement = connection.prepareStatement("update form set name=? where id=?");
			statement.setString(1, newForm.getName());
			statement.setInt(2, formId);
			statement.execute();
			forms = read(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return forms;
	}
	
	public List<Form> delete(int formId) {
		List<Form> forms = new ArrayList<Form>();
		try {
			connection = dataSource.getConnection();
			statement = connection.prepareStatement("delete from form where id=?");
			statement.setInt(1, formId);
			statement.executeUpdate();
			forms = read(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		return forms;
	}
	
	public List<Form> read(Connection connection) {
		List<Form> forms = new ArrayList<Form>();
		try {
			statement = connection.prepareStatement("select * from form");
			results = statement.executeQuery();
			Form form = null;
			while(results.next()) {
				form = new Form(
					results.getInt("id"),
					results.getString("name")
				);
				form.setDeveloperId(results.getInt("developer_id"));
				forms.add(form);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return forms;
	}
	
	public List<Form> create(int developerId, Form form) {
		List<Form> forms = new ArrayList<Form>();
		try {
			connection = dataSource.getConnection();
			statement = connection.prepareStatement("insert into form (name, developer_id) values (?,?)");
			statement.setString(1, form.getName());
			statement.setInt(2, developerId);
			statement.execute();
			
			forms = read(connection);
			
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
		
		return forms;
	}
	
	public static void main(String[] args) {
		FormDao dao = new FormDao();
		Form f1 = new Form(123, "Form 123");
		dao.create(2, f1);
	}
}
