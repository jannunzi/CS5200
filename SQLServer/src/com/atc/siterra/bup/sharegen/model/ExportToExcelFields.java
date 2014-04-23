package com.atc.siterra.bup.sharegen.model;

import java.util.List;

public class ExportToExcelFields
{
	public void exportExcelExportTables(List<ExcelExportTable> tables, TableService svc) {
		for(ExcelExportTable table : tables) {
			System.out.println(table.getTableName());
			for(ExcelExportColumn column : table.getColumns()) {
				System.out.println("\t"+column.getColumnName());
			}
		}
	}
}
