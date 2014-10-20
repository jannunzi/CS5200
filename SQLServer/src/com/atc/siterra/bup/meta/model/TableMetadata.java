package com.atc.siterra.bup.meta.model;

public class TableMetadata {}

//import java.util.List;
//
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.OneToMany;
//
//@Entity
//public class TableMetadata {
//	@Id
//	String tableName;
//	@OneToMany(mappedBy="table")
//	List<ColumnMetadata> columns;
//	String comment;
//	public String getTableName() {
//		return tableName;
//	}
//	public void setTableName(String tableName) {
//		this.tableName = tableName;
//	}
//	public String getComment() {
//		return comment;
//	}
//	public void setComment(String comment) {
//		this.comment = comment;
//	}
//	public TableMetadata(String databaseName, String tableName, String fieldName,
//			String comment) {
//		super();
//		this.tableName = tableName;
//		this.comment = comment;
//	}
//	public TableMetadata() {
//		super();
//	}
//}
