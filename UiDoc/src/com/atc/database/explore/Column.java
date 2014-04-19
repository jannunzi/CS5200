package com.atc.database.explore;

import java.sql.Types;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Column {
	@XmlAttribute
	public String name;
	@XmlAttribute
	public int type;
	@XmlAttribute
	public int size;
	@XmlAttribute
	public boolean isPrimaryKey = false;
	public String getTypeName() {
		if(type == Types.VARCHAR)
			return "VARCHAR";
		else if(type == Types.INTEGER)
			return "INT";
		else if(type == Types.TIMESTAMP)
			return "DATETIME";
		else if(type == 3)
			return "MONEY";
		return "TEXT";
	}
}
