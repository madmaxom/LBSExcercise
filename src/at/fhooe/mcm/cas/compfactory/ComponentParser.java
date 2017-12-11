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

	public String[] parse(String input) {
		List<String> elements = new ArrayList<String>();
		try {
			Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder()
					.parse(new ByteArrayInputStream(input.getBytes()), "UTF-8");
			NodeList nList = doc.getElementsByTagName("component");
			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					String name = eElement.getAttribute("name");
					if(name != null && !"".equals(name))
						elements.add(name);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(elements.size() > 0)
			return elements.toArray(new String[] {});
		else return null;
	}
}
