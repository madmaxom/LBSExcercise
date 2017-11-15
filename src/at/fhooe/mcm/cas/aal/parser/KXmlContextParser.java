package at.fhooe.mcm.cas.aal.parser;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.kxml2.io.KXmlParser;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import at.fhooe.mcm.cas.contexttype.ContextElement;
import at.fhooe.mcm.cas.contexttype.ContextElementType;
import at.fhooe.mcm.cas.contexttype.ContextFuel;
import at.fhooe.mcm.cas.contexttype.ContextPosition;
import at.fhooe.mcm.cas.contexttype.ContextTemperature;
import at.fhooe.mcm.cas.contexttype.ContextTimeOfTheDay;


public class KXmlContextParser implements IContextParser {

	@Override
	public List<ContextElement> parse(String context) {
		List<ContextElement> elements = new ArrayList<ContextElement>();
		
		try {
			ContextElement contextElement = null; 
			
			// setup parser
			KXmlParser parser = new KXmlParser();
			parser.setInput(new ByteArrayInputStream(context.getBytes()), "UTF-8");

			
			// start parsing
		    parser.nextTag();
		    parser.require(XmlPullParser.START_TAG, null, ContextElementType.START_TAG);

		    parser.nextTag();
		    parser.require(XmlPullParser.START_TAG, null, ContextElementType.CONTEXT_ID);
		    int cId = Integer.parseInt(parser.nextText());
		    
		    parser.nextTag();
		    parser.require(XmlPullParser.START_TAG, null, ContextElementType.CONTEXT_KEY);
		    String cKey = parser.nextText();
		    
		    parser.nextTag();
		    parser.require(XmlPullParser.START_TAG, null, ContextElementType.CONTEXT_VALUE);
		    
		    
		    // create concrete context elements
		    switch(cKey) {

        	case ContextElementType.CONTEXT_ELEMENT_FUEL: 
        		
        		ContextFuel contextFuel = new ContextFuel();
        		parser.nextTag();
        		parser.require(XmlPullParser.START_TAG, null, ContextElementType.ContextElementFuel.FUEL_STATUS);
        		contextFuel.setFuelStatus(Integer.parseInt(parser.nextText()));
     		    
        		
        		contextElement = contextFuel;
     		    break;
        	case ContextElementType.CONTEXT_ELEMENT_POSITION: 
        		
        		ContextPosition contextPosition = new ContextPosition();
        		
        		parser.nextTag();
        		parser.require(XmlPullParser.START_TAG, null, ContextElementType.ContextElementPosition.COORDINATE_TYPE);
     		    contextPosition.setCoordinateType(parser.nextText());
     		    
     		    
     		   parser.nextTag();
     		   parser.require(XmlPullParser.START_TAG, null, ContextElementType.ContextElementPosition.X_VALUE);
    		   contextPosition.setxValue(Integer.parseInt(parser.nextText()));
        		
     		   parser.nextTag();
     		   parser.require(XmlPullParser.START_TAG, null, ContextElementType.ContextElementPosition.Y_VALUE);
    		   contextPosition.setyValue(Integer.parseInt(parser.nextText()));

    		   
    		   contextElement = contextPosition;
        		break;
        	case ContextElementType.CONTEXT_ELEMENT_TEMPERATURE: 
        		
        		ContextTemperature contextTemperature = new ContextTemperature();
        		parser.nextTag();
        		parser.require(XmlPullParser.START_TAG, null, ContextElementType.ContextElementTemperature.TEMPERATURE);
        		contextTemperature.setTemperature(Integer.parseInt(parser.nextText()));
     		    
        		contextElement = contextTemperature;
     		    break;
        	case ContextElementType.CONTEXT_ELEMENT_TIME_OF_THE_DAY: 
        		
        		ContextTimeOfTheDay contextTimeOfTheDay = new ContextTimeOfTheDay();
        		parser.nextTag();
        		parser.require(XmlPullParser.START_TAG, null, ContextElementType.ContextElementTimeOfTheDay.TIME);
        		
        		parser.nextTag();
        		parser.require(XmlPullParser.START_TAG, null, ContextElementType.ContextElementTimeOfTheDay.Time.HOUR);
        		contextTimeOfTheDay.setHour(Integer.parseInt(parser.nextText()));
     		    
        		parser.nextTag();
        		parser.require(XmlPullParser.START_TAG, null, ContextElementType.ContextElementTimeOfTheDay.Time.MINUTES);
        		contextTimeOfTheDay.setMinuts(Integer.parseInt(parser.nextText()));
        		
        		
        		parser.nextTag();
        		parser.require(XmlPullParser.START_TAG, null, ContextElementType.ContextElementTimeOfTheDay.DATE);
        		
        		parser.nextTag();
        		parser.require(XmlPullParser.START_TAG, null, ContextElementType.ContextElementTimeOfTheDay.Date.YEAR);
        		contextTimeOfTheDay.setYear(Integer.parseInt(parser.nextText()));
        		
        		parser.nextTag();
        		parser.require(XmlPullParser.START_TAG, null, ContextElementType.ContextElementTimeOfTheDay.Date.MONTH);
        		contextTimeOfTheDay.setMonth(Integer.parseInt(parser.nextText()));
        		
        		parser.nextTag();
        		parser.require(XmlPullParser.START_TAG, null, ContextElementType.ContextElementTimeOfTheDay.Date.DAY);
        		contextTimeOfTheDay.setDay(Integer.parseInt(parser.nextText()));
        		
        		
        		contextElement = contextTimeOfTheDay;
     		    break;
		    
		    } // end switch
		    
		    contextElement.setId(cId);
		    contextElement.setKey(cKey);
		    
		    elements.add(contextElement);
		    
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return elements;
	}

}
