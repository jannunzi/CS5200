package com.atc.siterra.bup.meta.model;

public class ColumnMetadata {}

//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.NamedQueries;
//import javax.persistence.NamedQuery;
//
//@Entity
//@NamedQueries(value = {
//		@NamedQuery(name = "findAllColumnMetadata", query = "select cmd from ColumnMetadata cmd"),
//		@NamedQuery(name = "findAllColumnMetadataByColumnName", query = "select cmd from ColumnMetadata cmd where cmd.columnName=:columnName")
//})
//public class ColumnMetadata {
//	@Id
//	@GeneratedValue(strategy=GenerationType.IDENTITY)
//	int id;
//	String columnName;
//	String comment;
//	String uiFieldName;
//	String uiFieldType;
//	String[] uiLov;
//	boolean uiRequired;
//	@ManyToOne
//	@JoinColumn(name="tableId")
//	TableMetadata table;
//}
