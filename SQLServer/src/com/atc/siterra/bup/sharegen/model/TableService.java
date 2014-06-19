package com.atc.siterra.bup.sharegen.model;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.io.BufferedWriter;
import java.net.URL;
import java.sql.*;
import java.util.*;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.StreamingOutput;
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
	private String[] tableNames = null;
	
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
	
	public Database getDatabase(String dataSourceName, String[] tableNames)
	{
		Tables tables = new Tables();
		tables.table = this.getTables(dataSourceName, tableNames);
		
		for(Table table:tables.table) {
			table.column = this.getColumns(table.name, dataSourceName);
		}
		
		this.database.tables = tables;
		return this.database;
	}
	
	public void exportDatabaseToXml(Database database, String xmlFileName)
	{
		File xmlFile = new File(xmlFileName);
		try
		{
			JAXBContext jaxb = JAXBContext.newInstance(Database.class);
			Marshaller marshaller = jaxb.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.marshal(database, xmlFile);
		}
		catch (JAXBException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@GET
	@Path("/schema/{dataSourceName}/{tableNames}")
	public void exportSchemaToExcel(@PathParam("dataSourceName") String dataSourceStr,@PathParam("tableNames") String tableNamesStr)
	{
		URL resource = getClass().getResource("/");
		String path = resource.getPath();
		System.out.println("PATH");
		System.out.println(path);
		
		path = path.replace("WEB-INF/classes/", "");
		
		String[] tableNames = tableNamesStr.split(",");
		Database db = this.getDatabase(dataSourceStr, tableNames);
		ExportSchema es = new ExportSchema();
		es.export(db, path+"/schemaExport.xlsx");

/*
		StreamingOutput stream = new StreamingOutput() {
		    @Override
		    public void write(OutputStream os) throws IOException,
		    WebApplicationException {
		      Writer writer = new BufferedWriter(new OutputStreamWriter(os));
		      writer.write("test");
		      writer.flush();
		    }
		  };
		  */
/*
		File fileToSend = new File("/excel/schemaExport.xlsx");
		
		ResponseBuilder response = Response.ok((Object)fileToSend);
		response.header("Content-Disposition",
			"attachment; filename=schemaExport.xlsx");
		
		return response.build();
		*/
	}
	
	private static final String FILE_PATH = "C:\\excel\\dataExport.xlsx";

	
	@POST
	@Path("/excel/{dataSourceName}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void  exportToExcel(List<ExcelExportTable> tables, @PathParam("dataSourceName") String dataSourceName) throws SQLException {
		
		URL resource = getClass().getResource("/");
		String path = resource.getPath();
		System.out.println("PATH");
		System.out.println(path);
		
		path = path.replace("WEB-INF/classes/", "");

		System.out.println("Excel");
		System.out.println(tables);
		ExportToExcel edb = new ExportToExcel();
		edb.exportExcelExportTables(tables, this, dataSourceName, path);

		
		File workingDir = (new File("./"));
		System.out.println("PWD2");
		System.out.println(workingDir.getAbsolutePath());

		/*
		File file = new File(FILE_PATH);
		 
		ResponseBuilder response = Response.ok((Object) file);
		response.header("Content-Disposition","attachment; filename=new-excel-file.xlsx");
		return response.build();
		*/
	}
	
	@GET
	@Path("/{dataSourceName}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Table> getTables(@PathParam("dataSourceName") String dataSourceName)
	{
		List<Table> tables = new ArrayList<Table>();
		
		System.out.println("Getting Tables");
		
		try {
			Connection connection = getConnection(dataSourceName);
			DatabaseMetaData meta = connection.getMetaData();
			results = meta.getTables(null, null, null, new String[] {"TABLE", "VIEW"});
			while(results.next()) {
				String tableName = results.getString("TABLE_NAME");
				if(tableName.startsWith("sys") || tableName.startsWith("dt"))
					continue;
				Table table = new Table();
				table.name = tableName;
				System.out.print(".");
				tables.add(table);
			}
			closeConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("\nDone Getting Tables");
		
		return tables;
	}
	
	public List<Table> getTables(String dataSourceName, String[] tableNames)
	{
		List<Table> tables = new ArrayList<Table>();
		
		for(String tableName : tableNames)
		{
			Table table = new Table();
			table.name = tableName;
			tables.add(table);
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
				"semaan_app_user","qcdb01",null,"jdbc:{vendor}:{type}://{server}:{port};databaseName={name}");
		TableService svc = new TableService(db);

		db = svc.getDatabase("ORADEVDB1");
		
		List<Table> tables = db.tables.table;
		for(Table table : tables) {
			System.out.println(table.name);
			for(Column column:table.column) {
				System.out.println("\t"+column.name);
			}
		}
		
		svc.exportDatabaseToXml(db, "siterra/bup.xml");
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
	
	static Database currentDatabase = Database.DATABASES.get("ORADEVDB1");
	
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
