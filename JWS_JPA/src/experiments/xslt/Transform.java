package experiments.xslt;

import java.io.File;

import javax.xml.transform.*;
import javax.xml.transform.stream.StreamSource;
import javax.xml.transform.stream.StreamResult;

public class Transform {
	TransformerFactory factory = TransformerFactory.newInstance();
	
	public void transform(String inputXmlFileName, String outputXmlFileName, String xsltXmlFileName) {
		File inputFile = new File(inputXmlFileName);
		File outputFile = new File(outputXmlFileName);
		File xsltFile = new File(xsltXmlFileName);
		
		StreamSource inputXml = new StreamSource(inputFile);
		StreamSource xsltXml  = new StreamSource(xsltFile);
		StreamResult outputXml = new StreamResult(outputFile);
		
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
		Transform tx = new Transform();
		tx.transform("experiments/developers.xml", "experiments/developers.html", "experiments/developers2html.xsl");
		tx.transform("experiments/developers.xml", "experiments/applications.xml", "experiments/developers2applications.xsl");
	}

}
