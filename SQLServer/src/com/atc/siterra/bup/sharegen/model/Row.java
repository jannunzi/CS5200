package com.atc.siterra.bup.sharegen.model;

import java.util.List;

public class Row {
	public List<Column> fields;
	public void setFields(List<Column> fields) { this.fields = fields; }
	public List<Column> getFields() { return this.fields; }
}
