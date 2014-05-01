package com.atc.siterra.bup.sharegen.model;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExportToExcel
{
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
		System.out.println("Save Done");
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
	
	public void createCells(Row row, Object[] content, int offset, CellStyle style, boolean fit) {
		Sheet sheet = row.getSheet();
	    Cell cell;
	    for(int i=0; i<content.length; i++) {
		    cell = row.createCell(i+offset);
		    if(content[i] != null)
		    	cell.setCellValue(content[i].toString());
	        if(style != null)
	        	cell.setCellStyle(style);
	        if(fit)
	        	sheet.autoSizeColumn(i+offset);
	    }
	}
	
	public void generateHeaders(List<ExcelExportTable> tables, Sheet sheet) {
		ArrayList<String> excelHeaders = new ArrayList<String>();
		for(ExcelExportTable table : tables)
		{
			System.out.println(table.getTableName());
			

			for(ExcelExportColumn column : table.getColumns()) {
				String excelColumnName = column.getExcelColumnName();
				if(excelColumnName == null || "".equals(excelColumnName))
				{
					excelHeaders.add(column.getColumnName());
				}
				else
				{
					excelHeaders.add(excelColumnName);
				}
			}
		}
		Row row = sheet.createRow(0);
		Workbook wb = sheet.getWorkbook();
        CellStyle centeredBold = wb.createCellStyle();
        Font font = wb.createFont();
        font.setBoldweight(Font.BOLDWEIGHT_BOLD);
        centeredBold.setFont(font);
        centeredBold.setAlignment(CellStyle.ALIGN_CENTER);
        
		String[] excelHeadersArray = excelHeaders.toArray(new String[excelHeaders.size()]);
		createCells(row, excelHeadersArray, 0, centeredBold, false);
	}
	
	public void generateResults(ResultSet results, Sheet sheet) throws SQLException {
		ResultSetMetaData rsmd = results.getMetaData();
		int columnsNumber = rsmd.getColumnCount();
		Workbook wb = sheet.getWorkbook();
		int rowIndex = 1;
		while(results.next())
		{
			ArrayList<Object> resultCell = new ArrayList<Object>();
			for(int i=1; i<=columnsNumber; i++) {
				Object obj = results.getObject(i);
				resultCell.add(obj);
			}
			Row row = sheet.createRow(rowIndex++);
			Object[] excelHeadersArray = resultCell.toArray(new Object[resultCell.size()]);
			createCells(row, excelHeadersArray, 0, null, false);
		}
	}
	public String createSQLSelectFromExportTables(List<ExcelExportTable> tables)
	{
		String select = "select ";
		String from = " from ";
		String where = " where ";

		String prevTableName = null;
		for(ExcelExportTable table : tables)
		{
			String tableName = "["+table.getTableName()+"]";
			from += tableName + ", ";
			if(prevTableName != null) {
				where += prevTableName+".[TowerNumber] = " + tableName+".[TowerNumber] AND ";
			}
			for(ExcelExportColumn column : table.getColumns())
			{
				String columnName = "["+column.getColumnName()+"]";
				select += tableName+"."+columnName+", ";
			}
			prevTableName = tableName;
		}

		from = from.substring(0, from.length() - 2);
		select = select.substring(0, select.length() - 2);
		where = where.substring(0, where.length() - 5);
		
		select += from;
		if(tables.size() > 1)
			select += where;
		return select;
	}
	
	public void exportExcelExportTables(List<ExcelExportTable> tables, TableService svc, String dataSourceName) throws SQLException {
		Workbook wb = new XSSFWorkbook();
	    Sheet sheet = wb.createSheet("Generator Asset");
	    generateHeaders(tables, sheet);
	    String select = createSQLSelectFromExportTables(tables);
	    System.out.println(select);
	    ResultSet results = svc.executeQuery(select, dataSourceName);
	    generateResults(results, sheet);
	    svc.closeConnection();
		saveWorkbook(wb, "/excel/test.xlsx");
	}
}
