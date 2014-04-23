package com.atc.siterra.bup.sharegen.model;

import java.util.List;

public class ExcelExportTable {
	String tableName;
	List<ExcelExportColumn> columns;
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public List<ExcelExportColumn> getColumns() {
		return columns;
	}
	public void setColumns(List<ExcelExportColumn> columns) {
		this.columns = columns;
	}
	public ExcelExportTable() {
		super();
	}
}
