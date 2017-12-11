package at.fhooe.mcm.cas.aal.parser;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import at.fhooe.mcm.cas.contexttype.ContextElement;
import at.fhooe.mcm.cas.contexttype.ContextElementType;
import at.fhooe.mcm.cas.contexttype.ContextFuel;
import at.fhooe.mcm.cas.contexttype.ContextPosition;
import at.fhooe.mcm.cas.contexttype.ContextTemperature;
import at.fhooe.mcm.cas.contexttype.ContextTimeOfTheDay;

public class DOMContextParser implements IContextParser {

	@Override
	public List<ContextElement> parse(String context) {
		List<ContextElement> elements = new ArrayList<ContextElement>();

		try {
			Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder()
					.parse(new ByteArrayInputStream(context.getBytes()), "UTF-8");

			NodeList nList = doc.getElementsByTagName(ContextElementType.START_TAG);

			for (int temp = 0; temp < nList.getLength(); temp++) {

				ContextElement contextElement = null;

				Node nNode = nList.item(temp);

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;

					int cId = Integer.parseInt(
							eElement.getElementsByTagName(ContextElementType.CONTEXT_ID).item(0).getTextContent());
					String cKey = eElement.getElementsByTagName(ContextElementType.CONTEXT_KEY).item(0)
							.getTextContent();

					switch (cKey) {

					case ContextElementType.CONTEXT_ELEMENT_FUEL:

						ContextFuel contextFuel = new ContextFuel();
						contextFuel.setFuelStatus(Integer.parseInt(
								eElement.getElementsByTagName(ContextElementType.ContextElementFuel.FUEL_STATUS).item(0)
										.getTextContent()));

						contextElement = contextFuel;
						break;
					case ContextElementType.CONTEXT_ELEMENT_POSITION:

						ContextPosition contextPosition = new ContextPosition();

						contextPosition.setCoordinateType(
								eElement.getElementsByTagName(ContextElementType.ContextElementPosition.COORDINATE_TYPE)
										.item(0).getTextContent());
						contextPosition.setxValue(Double.parseDouble(
								eElement.getElementsByTagName(ContextElementType.ContextElementPosition.X_VALUE).item(0)
										.getTextContent()));
						contextPosition.setyValue(Double.parseDouble(
								eElement.getElementsByTagName(ContextElementType.ContextElementPosition.Y_VALUE).item(0)
										.getTextContent()));

						contextElement = contextPosition;
						break;
					case ContextElementType.CONTEXT_ELEMENT_TEMPERATURE:

						ContextTemperature contextTemperature = new ContextTemperature();
						contextTemperature.setTemperature(Integer.parseInt(
								eElement.getElementsByTagName(ContextElementType.ContextElementTemperature.TEMPERATURE)
										.item(0).getTextContent()));

						contextElement = contextTemperature;
						break;
					case ContextElementType.CONTEXT_ELEMENT_TIME_OF_THE_DAY:

						ContextTimeOfTheDay contextTimeOfTheDay = new ContextTimeOfTheDay();

						// time
						contextTimeOfTheDay.setHour(Integer.parseInt(
								eElement.getElementsByTagName(ContextElementType.ContextElementTimeOfTheDay.Time.HOUR)
										.item(0).getTextContent()));
						contextTimeOfTheDay.setMinuts(Integer.parseInt(eElement
								.getElementsByTagName(ContextElementType.ContextElementTimeOfTheDay.Time.MINUTES)
								.item(0).getTextContent()));

						// date
						contextTimeOfTheDay.setYear(Integer.parseInt(
								eElement.getElementsByTagName(ContextElementType.ContextElementTimeOfTheDay.Date.YEAR)
										.item(0).getTextContent()));
						contextTimeOfTheDay.setMonth(Integer.parseInt(
								eElement.getElementsByTagName(ContextElementType.ContextElementTimeOfTheDay.Date.MONTH)
										.item(0).getTextContent()));
						contextTimeOfTheDay.setDay(Integer.parseInt(
								eElement.getElementsByTagName(ContextElementType.ContextElementTimeOfTheDay.Date.DAY)
										.item(0).getTextContent()));

						contextElement = contextTimeOfTheDay;
						break;

					} // end switch

					contextElement.setId(cId);
					contextElement.setKey(cKey);

					elements.add(contextElement);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return elements;
	}

}
