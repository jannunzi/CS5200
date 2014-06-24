package edu.neu.cs5200.fm1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class FieldDao {
	DataSource dataSource;
	Connection connection;
	PreparedStatement statement;
	ResultSet results;
	public FieldDao() {
		try {
			Context jndi = new InitialContext();
			dataSource = (DataSource) jndi.lookup("java:comp/env/jdbc/fm1");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	public void create(int parentId, Field field) {
		try {
			connection = dataSource.getConnection();
			statement = connection.prepareStatement("insert into field (name, defaultvalue, formid, fieldtype) values (?,?,?,?)");
			statement.setString(1, field.getName());
			statement.setString(2, field.getDefaultValue());
			statement.setInt(3, parentId);
			statement.setString(4, field.getFieldType());
			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public List<Field> readForParentId(int parentId) {
		List<Field> fields = new ArrayList<Field>();
		try {
			connection = dataSource.getConnection();
			statement = connection.prepareStatement("select * from field where formid=?");
			statement.setInt(1, parentId);
			results = statement.executeQuery();
			while(results.next()) {
				Field field = new Field(
					results.getInt("id"),
					results.getString("name"),
					results.getString("fieldtype"),
					results.getString("defaultvalue"),
					results.getInt("formid")
				);
				fields.add(field);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return fields;		
	}
}
