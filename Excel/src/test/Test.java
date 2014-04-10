package test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.WorkbookUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Test {
	public static void main(String[] args) throws IOException {
		
		// http://poi.apache.org/spreadsheet/quick-guide.html
		
		Workbook wb = new XSSFWorkbook();
	    FileOutputStream fileOut = new FileOutputStream("xls/workbook.xlsx");
	    CreationHelper createHelper = wb.getCreationHelper();

	    
	    Sheet sheet1 = wb.createSheet("new sheet");
	    Sheet sheet2 = wb.createSheet("second sheet");
	    String safeName = WorkbookUtil.createSafeSheetName("[O'Brien's sales*?]"); // returns " O'Brien's sales   "
	    Sheet sheet3 = wb.createSheet(safeName);
	    
	    
	    Sheet sheet = wb.createSheet("new sheet 2");
	    // Create a row and put some cells in it. Rows are 0 based.
	    Row row = sheet.createRow((short)0);
	    // Create a cell and put a value in it.
	    Cell cell = row.createCell(0);
	    cell.setCellValue(1);
	    

	    
	    // dates
	    sheet = wb.createSheet("dates");

	    // Create a row and put some cells in it. Rows are 0 based.
	    row = sheet.createRow(0);

	    // Create a cell and put a date value in it.  The first cell is not styled
	    // as a date.
	    cell = row.createCell(0);
	    cell.setCellValue(new Date());

	    // we style the second cell as a date (and time).  It is important to
	    // create a new cell style from the workbook otherwise you can end up
	    // modifying the built in style and effecting not only this cell but other cells.
	    CellStyle cellStyle = wb.createCellStyle();
	    cellStyle.setDataFormat(
	        createHelper.createDataFormat().getFormat("m/d/yy h:mm"));
	    cell = row.createCell(1);
	    cell.setCellValue(new Date());
	    cell.setCellStyle(cellStyle);

	    //you can also set date as java.util.Calendar
	    cell = row.createCell(2);
	    cell.setCellValue(Calendar.getInstance());
	    cell.setCellStyle(cellStyle);
	    
	    // different cell types
	    sheet = wb.createSheet("different types");
	    row = sheet.createRow((short)2);
	    row.createCell(0).setCellValue(1.1);
	    row.createCell(1).setCellValue(new Date());
	    row.createCell(2).setCellValue(Calendar.getInstance());
	    row.createCell(3).setCellValue("a string");
	    row.createCell(4).setCellValue(true);
	    row.createCell(5).setCellType(Cell.CELL_TYPE_ERROR);



	    
	    wb.write(fileOut);
	    fileOut.close();
	}
}
