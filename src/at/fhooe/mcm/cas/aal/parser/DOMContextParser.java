package at.fhooe.mcm.cas.aal.parser;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;

import at.fhooe.mcm.cas.aal.ContextElementTest;

public class DOMContextParser implements IContextParser {

	
	
	@Override
	public List<ContextElementTest> parse(String context) {
		List<ContextElementTest> elements = new ArrayList<ContextElementTest>();
		
		try {
			Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new ByteArrayInputStream(context.getBytes()), "UTF-8");
			
			NodeList nList = doc.getElementsByTagName("ContextElement");

			System.out.println("----------------------------");

			for (int temp = 0; temp < nList.getLength(); temp++) {

				ContextElementTest elem = new ContextElementTest();
				Node nNode = nList.item(temp);

				System.out.println("\nCurrent Element :" + nNode.getNodeName());

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;

					System.out.println("s1 : " + eElement.getElementsByTagName("s1").item(0).getTextContent());
					System.out.println("s2 : " + eElement.getElementsByTagName("s2").item(0).getTextContent());

					
					elem.s1 = eElement.getElementsByTagName("s1").item(0).getTextContent();
					elem.s2 = eElement.getElementsByTagName("s2").item(0).getTextContent();
				}
				
				elements.add(elem);
				
				
			}
		    } catch (Exception e) {
			e.printStackTrace();
		}
			
		return elements;
	}

}
