package com.steelejr.eclipse.aws.marketplace.wizard;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.eclipse.ui.XMLMemento;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.steelejr.eclipse.aws.marketplace.Activator;

public final class NewFeatureXMLParser {
	
	private static final String SITE_FILE = "newFeatureUpdateSite.xml";
	
	
	Document document;
	Element element;

	/**
	 * Returns the list of update sites parsed from the update site file.
	 * 
	 * @param inputStream The file input stream.
	 * @return The list of update site URLs.
	 */
	public static List<String> getUpdateSites(InputStream inputStream) {

		List<String> sites = new ArrayList<String>();
		//Document document = null;

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//		try {
//			DocumentBuilder parser = factory.newDocumentBuilder();
//			document = parser.parse(new InputSource(inputStream));
//			NodeList nodes = document.getChildNodes();
//			
//			for (int i = 0; i < nodes.getLength(); i++) {
//				Node node = nodes.item (i);
//				if (node instanceof Element) {
//					System.out.println(node.getNodeValue());
//				}
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		return sites;
		return null;
	}
	
	
	private File getUpdateSiteFile () {		
		File file = Activator.getDefault().getStateLocation().append(SITE_FILE).toFile();		
		return file;
	}
	
	
	public void saveToFile () {
		BufferedOutputStream out = null;
		try {
			out = new BufferedOutputStream(new FileOutputStream(SITE_FILE));
			save (out);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	
	public void save (OutputStream outputStream) {
		try {
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty(OutputKeys.METHOD, "xml");
			transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			transformer.setOutputProperty("{http://xml.apache.org/xalan}indent-amount", "2");

			// Initialize StreamResult with File object to save to file
			StreamResult result = new StreamResult (new StringWriter());
			//DOMSource xmlSource = new DOMSource (doc);
			
			//transformer.transform(xmlSource, result);
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerFactoryConfigurationError e) {
			e.printStackTrace();
		}
		
	}
	
	
	/**
	 * From the monitor example from "Building admistrative apps with Eclipse".
	 */
	public void test () {
		
	}
}
