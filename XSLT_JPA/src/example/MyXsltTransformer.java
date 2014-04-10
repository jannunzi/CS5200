package example;

import java.io.File;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamSource;
import javax.xml.transform.stream.StreamResult;

public class MyXsltTransformer {
	
	public void doTx(String inputFileName, String outputFileName, String xsltFileName)
	{
		File inputFile = new File(inputFileName);
		File xsltFile = new File(xsltFileName);
		File outputFile = new File(outputFileName);
		
		StreamSource inputXml = new StreamSource(inputFile);
		StreamSource xsltXml  = new StreamSource(xsltFile);
		StreamResult outputXml = new StreamResult(outputFile);
		
		TransformerFactory factory = TransformerFactory.newInstance();
		try {
			Transformer tx = factory.newTransformer(xsltXml);
			tx.transform(inputXml, outputXml);
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		
		MyXsltTransformer tx = new MyXsltTransformer();
		tx.doTx("xml/directors.xml", "xml/actors.xml", "xml/directors2actors.xslt");
		tx.doTx("xml/directors.xml", "xml/actors.html", "xml/directors2actorsHtml.xslt");

	}

}
