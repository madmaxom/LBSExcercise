package at.fhooe.mcm.cas.compfactory;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ComponentParser {

	public List<ComponentInfo> parse(String input) {
		List<ComponentInfo> elements = new ArrayList<>();
		try {
			Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder()
					.parse(new ByteArrayInputStream(input.getBytes()), "UTF-8");
			NodeList nList = doc.getElementsByTagName("component");
			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					String name = eElement.getAttribute("name");
					String drawingContext = eElement.getAttribute("drawingContext");
					if(!isEmptyString(name)) {
						elements.add(new ComponentInfo(name, drawingContext));
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return elements;
	}
	
	private static boolean isEmptyString(String s) {
		return s == null || "".equals(s);
	}
}
