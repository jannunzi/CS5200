package edu.neu.cs5200.fm1;

public class Field {
	private int id;
	private String name;
	private String fieldType;
	private String defaultValue;
	private int formId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFieldType() {
		return fieldType;
	}
	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}
	public String getDefaultValue() {
		return defaultValue;
	}
	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}
	public int getFormId() {
		return formId;
	}
	public void setFormId(int formId) {
		this.formId = formId;
	}
	public Field(int id, String name, String fieldType, String defaultValue,
			int formId) {
		super();
		this.id = id;
		this.name = name;
		this.fieldType = fieldType;
		this.defaultValue = defaultValue;
		this.formId = formId;
	}
	public Field(String name, String fieldType, String defaultValue) {
		super();
		this.name = name;
		this.fieldType = fieldType;
		this.defaultValue = defaultValue;
	}
}
