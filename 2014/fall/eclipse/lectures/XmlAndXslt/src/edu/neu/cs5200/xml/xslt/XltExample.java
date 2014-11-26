package edu.neu.cs5200.xml.xslt;

import java.io.File;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class XltExample
{
	public static void main(String[] args)
	{
		extractAllArguments();
		extractAllDateArguments();
		extractAllArgumentsForType("Yes/No");
	}
	
	public static void extractAllArgumentsForType(String type)
	{
		File inputXmlFile = new File("/siterra/sample2.xml");
		File xsltFile = new File("/Users/jose.annunziato/Documents/Northeastern/GitHub/CS5200/2014/fall/eclipse/lectures/XmlAndXslt/src/edu/neu/cs5200/xml/xslt/extractAllArgumentsForType.xsl");
		File outputXmlFile = new File("/Users/jose.annunziato/Documents/Northeastern/GitHub/CS5200/2014/fall/eclipse/lectures/XmlAndXslt/src/edu/neu/cs5200/xml/xslt/extractAllArgumentsForType-out.xml");
		
		StreamSource inputXml = new StreamSource(inputXmlFile);
		StreamSource xslt = new StreamSource(xsltFile);
		StreamResult outputXml = new StreamResult(outputXmlFile);
		
		TransformerFactory tf = TransformerFactory.newInstance();
		try {
			Transformer tx = tf.newTransformer(xslt);
			tx.setParameter("DataType", type);
			tx.transform(inputXml, outputXml);
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void extractAllDateArguments()
	{
		File inputXmlFile = new File("/siterra/sample2.xml");
		File xsltFile = new File("/Users/jose.annunziato/Documents/Northeastern/GitHub/CS5200/2014/fall/eclipse/lectures/XmlAndXslt/src/edu/neu/cs5200/xml/xslt/extractAllDateArguments.xsl");
		File outputXmlFile = new File("/Users/jose.annunziato/Documents/Northeastern/GitHub/CS5200/2014/fall/eclipse/lectures/XmlAndXslt/src/edu/neu/cs5200/xml/xslt/extractAllDateArguments-out.xml");
		
		StreamSource inputXml = new StreamSource(inputXmlFile);
		StreamSource xslt = new StreamSource(xsltFile);
		StreamResult outputXml = new StreamResult(outputXmlFile);
		
		TransformerFactory tf = TransformerFactory.newInstance();
		try {
			Transformer tx = tf.newTransformer(xslt);
			tx.transform(inputXml, outputXml);
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void extractAllArguments()
			throws TransformerFactoryConfigurationError {
		File inputXmlFile = new File("/siterra/sample2.xml");
		File xsltFile = new File("/Users/jose.annunziato/Documents/Northeastern/GitHub/CS5200/2014/fall/eclipse/lectures/XmlAndXslt/src/edu/neu/cs5200/xml/xslt/extractAllArguments.xsl");
		File outputXmlFile = new File("/Users/jose.annunziato/Documents/Northeastern/GitHub/CS5200/2014/fall/eclipse/lectures/XmlAndXslt/src/edu/neu/cs5200/xml/xslt/extractAllArguments-out.xml");
		
		StreamSource inputXml = new StreamSource(inputXmlFile);
		StreamSource xslt = new StreamSource(xsltFile);
		StreamResult outputXml = new StreamResult(outputXmlFile);
		
		TransformerFactory tf = TransformerFactory.newInstance();
		try {
			Transformer tx = tf.newTransformer(xslt);
			tx.transform(inputXml, outputXml);
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
