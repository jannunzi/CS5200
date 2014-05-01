package com.atc.siterra.bup.sharegen.model;

import java.io.File;
import java.sql.*;
import java.util.*;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.ws.WebServiceContext;

@Path("/table")
public class TableService
{
	private Database database = null;
	private Connection connection = null;
	private PreparedStatement statement = null;
	private ResultSet results = null;
	
	public Database getDatabase(String dataSourceName)
	{
		Tables tables = new Tables();
		tables.table = this.getTables(dataSourceName);
		
		for(Table table:tables.table) {
			table.column = this.getColumns(dataSourceName, table.name);
		}
		
		this.database.tables = tables;
		return this.database;
	}
	
	public void exportDataseToXml(Database database, String xmlFileName) {
		File xmlFile = new File(xmlFileName);
		try {
			JAXBContext jaxb = JAXBContext.newInstance(Database.class);
			Marshaller marshaller = jaxb.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.marshal(database, xmlFile);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@POST
	@Path("/excel/{dataSourceName}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void exportToExcel(List<ExcelExportTable> tables, @PathParam("dataSourceName") String dataSourceName) throws SQLException {
		System.out.println("Excel");
		System.out.println(tables);
		ExportToExcel edb = new ExportToExcel();
		edb.exportExcelExportTables(tables, this, dataSourceName);
	}
	
	@GET
	@Path("/{dataSourceName}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Table> getTables(@PathParam("dataSourceName") String dataSourceName)
	{
		List<Table> tables = new ArrayList<Table>();
		
		try {
			Connection connection = getConnection(dataSourceName);
			DatabaseMetaData meta = connection.getMetaData();
			results = meta.getTables(null, null, null, null);
			while(results.next()) {
				String tableName = results.getString(3);
				if(tableName.startsWith("sys") || tableName.startsWith("dt"))
					continue;
				Table table = new Table();
				table.name = results.getString(3);
				tables.add(table);
			}
			closeConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return tables;
	}
	
	public ResultSet executeQuery(String query, String dataSourceName)
	{
		ResultSet result = null;
		try {
			Connection connection = getConnection(dataSourceName);
			Statement statement = connection.createStatement();
			result = statement.executeQuery(query);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	@GET
	@Path("/{dataSourceName}/{name}/column")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Column> getColumns(@PathParam("name") String tableName,@PathParam("dataSourceName") String dataSourceName)
	{
		List<Column> columns = new ArrayList<Column>();
		
		try {
			Connection connection = getConnection(dataSourceName);
			DatabaseMetaData meta = connection.getMetaData();
			
			
			ResultSet keysRs = meta.getExportedKeys(null, null, tableName);
			ResultSetMetaData keysMetaData = keysRs.getMetaData();
/*
			String pkColumnName = null;
			while(keysRs.next()) {
				pkColumnName = keysRs.getString("PK_NAME");
			}
			System.out.println("pkColumnName: " + pkColumnName);
	*/		
			results = meta.getColumns(null, null, tableName, null);
			ResultSetMetaData meta2 = results.getMetaData();
			int columnCount = meta2.getColumnCount();
			/*
			for(int i=1; i<=columnCount; i++)
			{
				System.out.println(meta2.getColumnLabel(i));
				System.out.println(meta2.getColumnName(i));
				System.out.println(meta2.getColumnTypeName(i));
				System.out.println("------------");
			}
			*/
			while(results.next()) {
				String columnName = results.getString("COLUMN_NAME");
				int columnType = results.getInt("DATA_TYPE");
				int size = results.getInt("COLUMN_SIZE");
				Column column = new Column();
				column.name = columnName;
				column.type = columnType;
				column.size = size;
				
//				if(column.name.equals(pkColumnName))
	//				column.isPrimaryKey = true;
				
				columns.add(column);
			}
			closeConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return columns;
	}
	
	public Connection getConnection(String dataSourceName)
	{
		this.database = Database.DATABASES.get(dataSourceName);
		try {
			Driver d = (Driver)Class.forName(this.database.driver).newInstance();
			connection = DriverManager.getConnection(this.database.getUrlString(),
					this.database.username, this.database.password);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	public void closeConnection()
	{
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public TableService(Database database)
	{
		this.database = database;
	}
	
	public static void main(String[] args) {
		Database db = new Database("ShareGen",
				"com.microsoft.jdbc.sqlserver.SQLServerDriver",
				"microsoft","sqlserver","QCSMN01","1433",
				"semaan_app_user","qcdb01",null);
		TableService svc = new TableService(db);

		db = svc.getDatabase("BUPQC");
		
		List<Table> tables = db.tables.table;
		for(Table table : tables) {
			System.out.println(table.name);
			for(Column column:table.column) {
				System.out.println("\t"+column.name);
			}
		}
		
		svc.exportDataseToXml(db, "siterra/bup.xml");
	}

	@PUT
	@Path("/database/{databaseName}")
	public void setDatabase(@PathParam("databaseName") String name) {
//		ServletContext servletContext =
	//		    (ServletContext) context.getMessageContext().get(MessageContext.SERVLET_CONTEXT);
//		System.out.println(servletContext);
		this.database = Database.DATABASES.get(name);
		System.out.println(name);
	}
	
	static Database currentDatabase = Database.DATABASES.get("BUPQC");
	
	public TableService() {
		super();
		/*
		this.database = new Database(
				"ShareGen",
				"com.microsoft.jdbc.sqlserver.SQLServerDriver",
				"microsoft",
				"sqlserver",
				"QCSMN01",
				"1433",
				"semaan_app_user",
				"qcdb01",
				null);
				*/
//		this.database = currentDatabase;
//		this.database = Database.DATABASES.get(Database.ATPRODQC);
	}
}
