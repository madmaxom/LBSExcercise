package at.fhooe.mcm.cas.aal.parser;

import java.io.ByteArrayInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.kxml2.io.KXmlParser;
import org.kxml2.kdom.Document;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import at.fhooe.mcm.cas.aal.ContextElementTest;

public class KXmlContextParser implements IContextParser {



	@Override
	public List<ContextElementTest> parse(String context) {
		List<ContextElementTest> elements = new ArrayList<ContextElementTest>();
		
		try {
			
			KXmlParser parser = new KXmlParser();
			
			parser.setInput(new ByteArrayInputStream(context.getBytes()), "UTF-8");

		    parser.nextTag();
		    parser.require(XmlPullParser.START_TAG, null, "Elements");
		    while (parser.nextTag() == XmlPullParser.START_TAG) {
		    	ContextElementTest elem = new ContextElementTest();
		    	
		    	parser.require(XmlPullParser.START_TAG, null, "ContextElement");

			    while (parser.nextTag() == XmlPullParser.START_TAG) {
			        String n = parser.getName().toLowerCase();
			        String t = parser.nextText();
			        if ("s1".equals(n))
			            elem.s1 = t;
			        else if ("s2".equals(n))
			        	elem.s2 = t;
			        

			    }
		        elements.add(elem);
			    parser.require(XmlPullParser.END_TAG, null, "ContextElement");
		    }
		    
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
