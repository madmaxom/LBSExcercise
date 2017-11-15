package at.fhooe.mcm.cas;

import java.awt.Panel;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import at.fhooe.mcm.cas.aal.parser.IContextParser;
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
		
		
		// TODO: outsource
		String destinationPath = new File("src/at/fhooe/mcm/cas/XML").getAbsolutePath();
		Path path = FileSystems.getDefault().getPath(destinationPath, "position.xml");
		
		System.out.println(path.toAbsolutePath());
		String context = null;
		try {
			context = new String(Files.readAllBytes(path.toAbsolutePath()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		
		
		
		
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
		
		List<at.fhooe.mcm.cas.contexttype.ContextElement> elements = mParser.parse(context);
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
