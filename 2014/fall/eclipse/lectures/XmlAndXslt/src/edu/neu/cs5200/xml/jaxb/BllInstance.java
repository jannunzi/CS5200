package edu.neu.cs5200.xml.jaxb;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="BLL-INSTANCE")
@XmlAccessorType(XmlAccessType.FIELD)
public class BllInstance {
	@XmlElement(name="ARGUMENT")
	public List<Argument> arguments;
}
