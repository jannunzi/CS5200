package edu.neu.cs5200.xml.jaxb;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class JaxbExample {

	public static void main(String[] args) {
		try {
			
			File file = new File("/siterra/sample2.xml");
			
			JAXBContext context = JAXBContext.newInstance(ClassInstances.class);
			
			Unmarshaller unmarshaller = context.createUnmarshaller();
			
			ClassInstances object = (ClassInstances) unmarshaller.unmarshal(file);
			
			System.out.println(object);
			System.out.println(object.classInstance);
			System.out.println(object.classInstance.get(0));
			System.out.println(object.classInstance.get(0).bllInstances);
			System.out.println(object.classInstance.get(0).bllInstances.bllInstance);
			System.out.println(object.classInstance.get(0).bllInstances.bllInstance.get(0));
			System.out.println(object.classInstance.get(0).bllInstances.bllInstance.get(0).arguments);
			System.out.println(object.classInstance.get(0).bllInstances.bllInstance.get(0).arguments.get(0));
			System.out.println(object.classInstance.get(0).bllInstances.bllInstance.get(0).arguments.get(0).description);
//			System.out.println(object.classInstance.get(0).bllInstances.bllInstance.get(0).arguments.get(0).description);
			
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
