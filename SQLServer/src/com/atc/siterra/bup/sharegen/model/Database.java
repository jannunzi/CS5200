package com.atc.siterra.bup.sharegen.model;

import java.sql.DriverManager;
import java.util.HashMap;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Database {
	@XmlAttribute
	public String name;
	@XmlAttribute
	public String driver;
	@XmlAttribute
	public String vendor;
	@XmlAttribute
	public String type;
	@XmlAttribute
	public String server;
	@XmlAttribute
	public String port;
	@XmlAttribute
	public String username;
	@XmlAttribute
	public String password;
	@XmlAttribute
	public String urlPattern;
	@XmlElement
	public Tables tables = new Tables();
	public Database(String name, String driver, String vendor, String type,
			String server, String port, String username, String password,
			Tables tables, String urlPattern) {
		super();
		this.name = name;
		this.driver = driver;
		this.vendor = vendor;
		this.type = type;
		this.server = server;
		this.port = port;
		this.username = username;
		this.password = password;
		this.tables = tables;
		this.urlPattern = urlPattern;
	}
	public Database() {
		super();
	}
	
	public static HashMap<String, Database> DATABASES = new HashMap<String, Database>();
	public static String BUPQC    = "BUPQC";
	public static String ATPRODQC = "ATPRODQC";
	
	static {
		Database bupQc = new Database(
				"ShareGen",
				"com.microsoft.jdbc.sqlserver.SQLServerDriver",
				"microsoft",
				"sqlserver",
				"QCSMN01",
				"1433",
				"semaan_app_user",
				"qcdb01",
				null,"jdbc:{vendor}:{type}://{server}:{port};databaseName={name}");
		
		Database bupProd = new Database(
				"ShareGen",
				"com.microsoft.jdbc.sqlserver.SQLServerDriver",
				"microsoft",
				"sqlserver",
				"SDMAMXTKR01",
				"1433",
				"semaan_app_user",
				"W7!smnDBrw",
				null,"jdbc:{vendor}:{type}://{server}:{port};databaseName={name}");
		
		Database atProdQc = new Database(
				"ATPRODUCTION",
				"com.microsoft.jdbc.sqlserver.SQLServerDriver",
				"microsoft",
				"sqlserver",
				"DB-QC-ATPRODUCTION",
				"1433",
				"semaan_app_user",
				"qcdb01",
				null,"jdbc:{vendor}:{type}://{server}:{port};databaseName={name}");
		
		Database oraDevDb1 = new Database(
//				"ORADEVDB1",
				"tst",
				"oracle.jdbc.OracleDriver",
				"oracle",
				"thin",
				"oradevdb1.americantower.com",
				"1521",
				"atc",
				"oracle_atc",
				null,"jdbc:{vendor}:{type}:@{server}:{port}:tst"
				);
		
		DATABASES.put("BUPQC", bupQc);
		DATABASES.put("BUPPROD", bupProd);
		DATABASES.put("ATPRODQC", atProdQc);
		DATABASES.put("ORADEVDB1", oraDevDb1);
	}
	
	private String urlTemplate = "jdbc:{vendor}:{type}://{server}:{port};databaseName={name}";
	public String getUrlString() {
		
		String url = new String(this.urlPattern);
		url = url.replace("{vendor}", this.vendor);
		url = url.replace("{type}", this.type);
		url = url.replace("{server}", this.server);
		url = url.replace("{port}", this.port);
		url = url.replace("{name}", this.name);
		return url;
	}
}


//com.microsoft.jdbc.sqlserver.SQLServerDriver").newInstance();
//connection = DriverManager.getConnection("jdbc:microsoft:sqlserver://QCSMN01:1433;databaseName=ShareGen", "semaan_app_user", "qcdb01");
