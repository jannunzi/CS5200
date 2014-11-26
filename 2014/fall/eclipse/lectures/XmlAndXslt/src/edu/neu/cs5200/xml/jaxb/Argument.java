package edu.neu.cs5200.xml.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="ARGUMENT")
@XmlAccessorType(XmlAccessType.FIELD)
public class Argument {
	@XmlAttribute(name="NAME")
	public String name;
	@XmlAttribute(name="DATA_TYPE")
	public String dataType;
	@XmlAttribute(name="UNIQUE_IDENTIFIER")
	public String uniqueIdentifier;
	@XmlAttribute(name="REQUIRED")
	public String required;
	@XmlAttribute(name="FORMAT")
	public String format;
	@XmlAttribute(name="DESCRIPTION")
	public String description;
	@XmlAttribute(name="VALUE")
	public String value;
}
