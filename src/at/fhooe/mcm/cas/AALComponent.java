package at.fhooe.mcm.cas;

import java.awt.Panel;

import at.fhooe.mcm.cas.aal.parser.IContextParser;
import at.fhooe.mcm.cas.aal.parser.KXmlContextParser;
import at.fhooe.mcm.cas.aal.parserfactory.DOMParserFactory;
import at.fhooe.mcm.cas.aal.parserfactory.GsonParserFactory;
import at.fhooe.mcm.cas.aal.parserfactory.IParserFactory;
import at.fhooe.mcm.cas.aal.parserfactory.KXmlParserFactory;
import at.fhooe.mcm.cas.gis.geomodel.GeoObject;

public class AALComponent extends IComponent {

	private IContextParser mParser;
	
	public enum ParserMode {
		DOM, STREAM, JSON
	}
	
	public AALComponent(IMediator mediator, String name) {
		this(mediator, name, ParserMode.DOM);
	}
	
	public AALComponent(IMediator mediator, String name,  ParserMode mode) {
		super(mediator, name);
		
		mode = ParserMode.JSON;
		// create a concrete factory for producing a parser
		IParserFactory factory = null;
		switch (mode) {
			case STREAM : factory = new KXmlParserFactory(); break;
			case DOM : factory = new DOMParserFactory(); break;
			case JSON : factory = new GsonParserFactory(); break;
		default:
			factory = new KXmlParserFactory();
		} // switch mode
		
		
		mParser = factory.createParser();
		
		// xml sample for testing only, providing two ContextElement objects
		String context = "<Elements><ContextElement><s1>1</s1><s2>2</s2></ContextElement><ContextElement><s1>3</s1><s2>4</s2></ContextElement></Elements>";
		mParser.parse(context);
	}

	@Override
	public void onGeoObjectUpdated(GeoObject geoObject) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onContextElementUpdated(ContextElement contextElement) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onContextSituationUpdated(ContextSituation contextSituation) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Panel getView() {
		// TODO Auto-generated method stub
		return null;
	}

}
