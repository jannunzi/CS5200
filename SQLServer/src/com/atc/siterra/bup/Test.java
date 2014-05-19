package com.atc.siterra.bup;

import java.sql.*;

import com.atc.siterra.bup.sharegen.model.Table;

public class Test {
	
	public String hello()
	{
		return "Hello";
	}
	
	public String testQCSMN01()
	{
		Connection connection = null;
		try {
			Driver d = (Driver)Class.forName("com.microsoft.jdbc.sqlserver.SQLServerDriver").newInstance();
			connection = DriverManager.getConnection("jdbc:microsoft:sqlserver://QCSMN01:1433;databaseName=ShareGen", "semaan_app_user", "qcdb01");
			System.out.println(connection);
			String testSql = "select * from ShareGenMain";
			PreparedStatement statement = connection.prepareStatement(testSql);
			ResultSet results = statement.executeQuery();
			while(results.next())
			{
				String towerNumber = results.getString("TowerNumber");
				String siteName = results.getString("SiteName");
				System.out.println(towerNumber + " " + siteName);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
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
		return null;
	}

	public String testQCSQL12()
	{
		Connection connection = null;
		try {
			Driver d = (Driver)Class.forName("com.microsoft.jdbc.sqlserver.SQLServerDriver").newInstance();
//			connection = DriverManager.getConnection("jdbc:microsoft:sqlserver://QCSQL12:1433;databaseName=ATProduction", "AMERICANTOWER\\Jose.Annunziato", "$anBenito11");
//			connection = DriverManager.getConnection("jdbc:microsoft:sqlserver://QCSQL12:1433;domain=AMERICANTOWER;databaseName=ATProduction;integratedSecurity=true;authenticationScheme=JavaKerberos", "AMERICANTOWER\\Jose.Annunziato", "$anBenito11");
			connection = DriverManager.getConnection("jdbc:microsoft:sqlserver://DB-QC-ATPRODUCTION:1433;databaseName=ATPRODUCTION", "semaan_app_user", "qcdb01");
			System.out.println(connection);
			String testSql = "select * from ColocationProject";
			PreparedStatement statement = connection.prepareStatement(testSql);
			ResultSet results = statement.executeQuery();
			while(results.next())
			{
				int ColocationProjectID = results.getInt("ColocationProjectID");
				System.out.println(ColocationProjectID);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
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
		return null;
	}

	public String testORADEVDB1()
	{
		Connection connection = null;
		try {
			Driver d = (Driver)Class.forName("oracle.jdbc.OracleDriver").newInstance();
			connection = DriverManager.getConnection("jdbc:oracle:thin:@oradevdb1.americantower.com:1521:tst", "atc", "oracle_atc");
			System.out.println(connection);
			
			DatabaseMetaData meta = connection.getMetaData();
			System.out.println(1);
			System.out.println(meta);
			ResultSet results = meta.getTables(null, null, null, new String[] {"VIEW"});
			System.out.println(2);
			System.out.println(results);
			System.out.println(3);
			System.out.println(results.next());
			while(results.next()) {
				String tableName = results.getString("TABLE_NAME");
				System.out.println(tableName);
			}

			
/*			
			String testSql = "select * from ACS_AR_BUDGETS";
			PreparedStatement statement = connection.prepareStatement(testSql);
			ResultSet results = statement.executeQuery();
			while(results.next())
			{
				String lsProjectNumber = results.getString("LS_PROJECT_NUMBER");
				System.out.println(lsProjectNumber);
			}
			*/
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
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
		return null;
	}
	
	public static void main(String[] args) {
		Test test = new Test();
		test.testORADEVDB1();
	}
}
