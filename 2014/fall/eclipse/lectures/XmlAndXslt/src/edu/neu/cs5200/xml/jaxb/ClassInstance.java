package edu.neu.cs5200.xml.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="CLASS-INSTANCE")
@XmlAccessorType(XmlAccessType.FIELD)
public class ClassInstance {
	@XmlElement(name="BLL-INSTANCES")
	public BllInstances bllInstances;
}
