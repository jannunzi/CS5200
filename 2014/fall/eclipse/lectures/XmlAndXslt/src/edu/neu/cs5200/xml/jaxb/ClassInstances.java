package edu.neu.cs5200.xml.jaxb;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="CLASS-INSTANCES")
@XmlAccessorType(XmlAccessType.FIELD)
public class ClassInstances {
	@XmlElement(name="CLASS-INSTANCE")
	public List<ClassInstance> classInstance;
}
