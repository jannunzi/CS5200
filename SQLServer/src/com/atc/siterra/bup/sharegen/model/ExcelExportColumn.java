package com.atc.siterra.bup.sharegen.model;

public class ExcelExportColumn {
	String columnName;
	String excelColumnName;
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String column) {
		this.columnName = column;
	}
	public String getExcelColumnName() {
		return excelColumnName;
	}
	public void setExcelColumnName(String excelColumnName) {
		this.excelColumnName = excelColumnName;
	}
	public ExcelExportColumn() {
		super();
	}
}
