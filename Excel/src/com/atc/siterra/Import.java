package com.atc.siterra;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Import {

	public static void main(String[] args)
	{

//		String[] generatorTableNames = {"ShareGenMain"};
//		Import.generateImport(
////				"/siterra/Import_Schemarev.xlsx",	// schema/config file
//				"/siterra/ImportSchema-Assets-10_10_14-new-mapping.xlsx",	// schema/config file
//				"/siterra/generators-10-16.csv",	// output CSV file
//				generatorTableNames,				// ATC table name
//				null,								// join field
//				"TowerNumber",						// order by field name
//				-1,									// max row count
//				0,									// sheet index
//				0,									// siterra field row index
//				3,									// ATC field row index
//				4,									// default values row index
//				5									// format row index
//		);

		String[] switchesTableNames = {"Tenant", "ShareGenMain"};
		Import.generateImport(
				"/siterra/Import_Schemarev_with_oracle_project_number.xlsx",	// schema/config file
				"/siterra/switches-10-16.csv",			// output CSV file
				switchesTableNames,					// ATC table names
				"TowerNumber",						// join field
				"TowerNumber",						// order by field name
				-1,									// max row count
				0,									// sheet index
				0,									// siterra field row index
				6,									// ATC field row index
				7,									// default values row index
				8									// format row index
		);
	}
	public static SimpleDateFormat dateFormatMMDDYYYY = new SimpleDateFormat("MM/dd/yyyy");
	public static SimpleDateFormat dateFormatYYYYMMDD = new SimpleDateFormat("yyyy-MM-dd");
	public static void generateImport(
			String xlsxFileName,
			String csvFileName,
			String[] atcTableNames,
			String joinField,
			String atcOrderByFieldName,
			int maxRowCount,
			int sheeIndex,
			int siterraFieldRowIndex,
			int atcFieldRowIndex,
			int defaultValuesRowIndex,
			int formatRowIndex)
	{
		InputStream inp = null;
		PrintWriter out = null;
		try
		{
			out = new PrintWriter(csvFileName);
		}
		catch (FileNotFoundException e1)
		{
			e1.printStackTrace();
		}
		
		try
		{
			inp = new FileInputStream(xlsxFileName); // TODO: configurable
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		
		Workbook wb = null;
		try
		{
			wb = WorkbookFactory.create(inp);
		}
		catch (InvalidFormatException | IOException e)
		{
			e.printStackTrace();
		}
		Sheet sheet = wb.getSheetAt(sheeIndex);

		String select = "SELECT ";
	    Row atcFieldRow = sheet.getRow(atcFieldRowIndex);		// TODO: configurable
	    int fieldCount = atcFieldRow.getLastCellNum();
	    for(int i=0; i<fieldCount; i++)
	    {
		    Cell atcCell = atcFieldRow.getCell(i, Row.RETURN_BLANK_AS_NULL);
		    String atcFieldStr = null;
		    if(atcCell != null)
		    {
		    	atcFieldStr = atcCell.getStringCellValue();
		    	select += atcFieldStr + ", ";
		    }
	    }
	    select = select.substring(0, select.length()-2);
	    
	    select += " FROM ";
	    for(int t=0; t<atcTableNames.length; t++) {
	    	select += atcTableNames[t];
	    	if(t < atcTableNames.length - 1)
	    		select += ", ";
	    }
	    if(joinField != null)
	    {
	    	select += " WHERE";
		    for(int t=0; t<atcTableNames.length - 1; t++) {
		    	select += " " + atcTableNames[t]+"."+joinField + "=" + atcTableNames[t+1]+"."+joinField;
		    	if(t < atcTableNames.length - 2)
		    		select += " AND ";
		    }	    	
	    }
	    if(atcOrderByFieldName != null)
	    {
	    	select += " ORDER BY " + atcTableNames[0] + "." + atcOrderByFieldName;
	    }
	    System.out.println(select);
	    
	    Connection connection = null;
		try
		{
			Driver d = (Driver)Class.forName("com.microsoft.jdbc.sqlserver.SQLServerDriver").newInstance();
			connection = DriverManager.getConnection("jdbc:microsoft:sqlserver://SDMAMXTKR01:1433;databaseName=ShareGen",
					"semaan_app_user", "W7!smnDBrw");
			Statement statement = connection.createStatement();
			ResultSet results = statement.executeQuery(select);
			
			
		    Row siterraFieldRow = sheet.getRow(siterraFieldRowIndex);
		    Row defaultValuesRow  = sheet.getRow(defaultValuesRowIndex);
		    Row formatRow = sheet.getRow(formatRowIndex);
		    fieldCount = siterraFieldRow.getLastCellNum();
			
		    for(int i=0; i<fieldCount; i++)
		    {
			    Cell siterraCell = siterraFieldRow.getCell(i, Row.RETURN_BLANK_AS_NULL);
			    String siterraFieldStr = null;
			    if(siterraCell != null)
			    {
			    	siterraFieldStr = siterraCell.getStringCellValue();
			    	int dashIndex = siterraFieldStr.indexOf(" -");
			    	if(dashIndex >= 0 )
			    		siterraFieldStr = siterraFieldStr.substring(0, dashIndex);
			    	siterraFieldStr = siterraFieldStr.replace("'", "");
			    }
			    out.print(siterraFieldStr);
			    if(i < fieldCount - 1)
			    {
				    out.print(",");
			    }
		    }
		    out.println();		    
		    
			int count = 0;
			while(results.next())
			{
				System.out.println(count);
				if(count == 136)
				{
					System.out.println("found");
				}
				if(maxRowCount > 0 && count > maxRowCount - 1)
				{
					break;
				}
				count++;
				
			    for(int i=0; i<fieldCount; i++)
			    {
				    Cell siterraCell = siterraFieldRow.getCell(i, Row.RETURN_BLANK_AS_NULL);
				    String siterraFieldStr = null;
				    if(siterraCell != null)
				    {
				    	siterraFieldStr = siterraCell.getStringCellValue();
				    }
				    
				    Cell atcCell = atcFieldRow.getCell(i, Row.RETURN_BLANK_AS_NULL);
				    String atcFieldStr = null;
				    if(atcCell != null)
				    {
				    	atcFieldStr = atcCell.getStringCellValue();
				    }
				    
				    Cell defaultCell = defaultValuesRow.getCell(i, Row.RETURN_BLANK_AS_NULL);
				    String defaultValueStr = null;
				    if(defaultCell != null)
				    {
				    	defaultValueStr = defaultCell.getStringCellValue();
				    }
				    
				    Cell formatCell = formatRow.getCell(i, Row.RETURN_BLANK_AS_NULL);
				    String formatStr = null;
				    if(formatCell != null)
				    {
				    	formatStr = formatCell.getStringCellValue();
				    }
				    
				    Object value = null;
				    if(atcFieldStr == null)
				    {
				    	value = defaultValueStr;
				    }
				    else
				    {
				    	int periodIndex = atcFieldStr.indexOf(".");
				    	if(periodIndex > 0)
				    	{
					    	atcFieldStr = atcFieldStr.substring(periodIndex+1);
				    	}
				    	value = results.getObject(atcFieldStr);
				    }
				    
				    if(value == null)
				    {
				    	value = "";
				    }
				    else
				    {
					    if(formatStr != null)
					    {
					    	if(formatStr.equals("MM/DD/YYYY"))
					    	{
					    		String dateStr = (value.toString()).replace(" 00:00:00.0","");
					    		try {
					    			Date date = dateFormatYYYYMMDD.parse(dateStr);
					    			value = dateFormatMMDDYYYY.format(date);
								} catch (ParseException e) {
									e.printStackTrace();
								}
					    	}
					    	else
					    	{
						    	value = formatStr.replaceAll("000", value.toString());
					    	}
					    }
					    
				    	try
				    	{
				    		
					    	value = (value.toString()).replace(",", "");
				    	}
				    	catch(Exception e)
				    	{
				    		e.printStackTrace();
				    	}
				    }
				    
				    out.print(value);
				    if(i < fieldCount - 1)
				    {
					    out.print(",");
				    }
			    }
			    out.println();
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		catch (InstantiationException | IllegalAccessException | ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				out.close();
				connection.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}

}
