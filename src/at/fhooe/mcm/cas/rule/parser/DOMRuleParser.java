package at.fhooe.mcm.cas.rule.parser;


import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import at.fhooe.mcm.cas.rule.container.Action;
import at.fhooe.mcm.cas.rule.container.Parameter;
import at.fhooe.mcm.cas.rule.container.RuleContainer;
import at.fhooe.mcm.cas.rule.container.RuleEvaluator;

public class DOMRuleParser implements IRuleParser {

	@Override
	public RuleEvaluator parse(String context) {
		List<RuleContainer> elements = new ArrayList<>();

		try {
			Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder()
					.parse(new ByteArrayInputStream(context.getBytes()), "UTF-8");

			NodeList nList = doc.getElementsByTagName("rule");

			for (int temp = 0; temp < nList.getLength(); temp++) {

				
				Action action = new Action();
				

				Node nNode = nList.item(temp);

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;
					
					
					String condition = eElement.getElementsByTagName("condition").item(0).getTextContent();

					action.setClazz(eElement.getElementsByTagName("class").item(0)
							.getTextContent());
					action.setMethod(eElement.getElementsByTagName("method").item(0)
							.getTextContent());
					
					
					NodeList nListParameters = eElement.getElementsByTagName("parameter");
					List<Parameter> parameters = new ArrayList<Parameter>();
					
					for (int temp2 = 0; temp2 < nListParameters.getLength(); temp2++) {
					
						Node nNodeP = nListParameters.item(temp2);
						
						Parameter parameter = null;
						if (nNodeP.getNodeType() == Node.ELEMENT_NODE) {
							Element eElementP = (Element) nNodeP;
							
							parameter = new Parameter();
							
							parameter.setParameterType(eElementP.getElementsByTagName("type").item(0)
									.getTextContent());
							parameter.setParameterValue(eElementP.getElementsByTagName("value").item(0)
									.getTextContent());
							
							parameters.add(parameter);
						}
					
					} // end parameters
					
					
					action.setParameter(parameters);
				    RuleContainer ruleContainer = new RuleContainer(condition, action);
				    elements.add(ruleContainer);

				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		RuleEvaluator evaluator = new RuleEvaluator();
		evaluator.setRules(elements);
		return evaluator;
		
	}

}
