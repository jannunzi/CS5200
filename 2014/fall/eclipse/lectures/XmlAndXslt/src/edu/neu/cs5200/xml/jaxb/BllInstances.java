package edu.neu.cs5200.xml.jaxb;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="BLL-INSTANCES")
@XmlAccessorType(XmlAccessType.FIELD)
public class BllInstances {
	@XmlElement(name="BLL-INSTANCE")
	public List<BllInstance> bllInstance;
	@XmlAttribute(name="NAME")
	public String name;
	@XmlAttribute(name="VALUE")
	public String value;
}
