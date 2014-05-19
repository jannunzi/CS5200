package com.atc.siterra.bup.sharegen.model;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExportSchema {

	public void saveWorkbook(Workbook wb, String workbookFileName) {
	    FileOutputStream fileOut;
		try {
			fileOut = new FileOutputStream(workbookFileName);
		    wb.write(fileOut);
		    fileOut.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("deprecation")
	public void createCell(Row row, String content, int col, int[] span, CellStyle style) {
		Sheet sheet = row.getSheet();
	    Cell cell = row.createCell(col);
	    cell.setCellValue(content);
	    if(span.length == 2)
		    sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(), row.getRowNum()+span[0]-1, col,col+span[1]-1));
        if(style != null)
        	cell.setCellStyle(style);
	}
	
	public void createCells(Row row, String[] content, int offset, CellStyle style, boolean fit) {
		Sheet sheet = row.getSheet();
	    Cell cell;
	    for(int i=0; i<content.length; i++) {
		    cell = row.createCell(i+offset);
		    cell.setCellValue(content[i]);
	        if(style != null)
	        	cell.setCellStyle(style);
	        if(fit)
	        	sheet.autoSizeColumn(i+offset);
	    }
	}
	
	public void setHeaders(Workbook wb, Sheet sheet) {
        CellStyle centeredBold = wb.createCellStyle();
        Font font = wb.createFont();
        font.setBoldweight(Font.BOLDWEIGHT_BOLD);
        centeredBold.setFont(font);
        centeredBold.setAlignment(CellStyle.ALIGN_CENTER);
        
		Row row;
		Cell cell;
		
		// First Row
	    row = sheet.createRow(0);
		int[] databaseHeaderSpan = {1,4};
	    createCell(row, "Database", 0, databaseHeaderSpan, centeredBold);
	    
		int[] uiHeaderSpan = {1,8};
	    createCell(row, "User Interface", 4, uiHeaderSpan, centeredBold);
	    
		int[] siterraHeaderSpan = {1,2};
	    createCell(row, "Siterra", 12, siterraHeaderSpan, centeredBold);
	    
	    // Second Row
		row = sheet.createRow(1);
		// Database Headers
		String[] databaseCols = {"Field", "Type", "Size", "Category"};
		createCells(row, databaseCols, 0, centeredBold, false);
		// UI Headers
		String[] uiCols = {"Screen", "Field", "Used", "Required", "Source", "Default", "LOVs", "Description"};
		createCells(row, uiCols, row.getLastCellNum(), centeredBold, false);
		// Siterra Headers
		String[] siterraCols = {"Screen", "Field"};
		createCells(row, siterraCols, row.getLastCellNum(), centeredBold, false);
		// General Headers
		String[] finalCols = {"Comments"};
		createCells(row, finalCols, row.getLastCellNum(), centeredBold, false);
	}
	
	public void export(Database db, String workbookFileName)
	{
		TableService svc = new TableService(db);
		
		Workbook wb = new XSSFWorkbook();
		
		Row row;
		Cell cell;

		List<Table> tables = db.tables.table;//svc.getTables(dataSourceName, tableNames);
		int fieldCount = 0;
		for(Table table : tables) {
		    Sheet sheet = wb.createSheet(table.name);
			setHeaders(wb, sheet);
			System.out.println(table.name);
		    int rowIndex = 2;
			for(Column column:table.column) {
			    row = sheet.createRow(rowIndex++);
			    cell = row.createCell(0);
			    cell.setCellValue(column.name);
			    cell = row.createCell(1);
			    cell.setCellValue(column.getTypeName());
			    cell = row.createCell(2);
			    cell.setCellValue(column.size);
			    System.out.println("\t"+column.name+"\t"+column.type);
			    fieldCount++;
			}
			sheet.autoSizeColumn(0);
			sheet.autoSizeColumn(1);
			sheet.autoSizeColumn(2);
		}
		saveWorkbook(wb, workbookFileName);
		System.out.println("Field Count: " + fieldCount);
	}
	
	public static void main(String[] args) {
		
		ExportSchema edb = new ExportSchema();
		
		Database db = Database.DATABASES.get("ORADEVDB1");
		
//		edb.export(db, "xls/dictionary2.xlsx", new String[] {"ATC_INBLD_COLO", "ATC_INBLD_OTM", "ATC_OASISTOWERLIST", "ATC_OASIS_TLAS", "ATC_REDEV_LIGHTING_TYPES", "ATC_REDEV_OASIS_EXTRACT"});
	}

}
