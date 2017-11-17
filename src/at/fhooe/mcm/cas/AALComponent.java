package at.fhooe.mcm.cas;

import java.awt.Panel;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import at.fhooe.mcm.cas.aal.parser.IContextParser;
import at.fhooe.mcm.cas.aal.parserfactory.DOMParserFactory;
import at.fhooe.mcm.cas.aal.parserfactory.GsonParserFactory;
import at.fhooe.mcm.cas.aal.parserfactory.IParserFactory;
import at.fhooe.mcm.cas.aal.parserfactory.KXmlParserFactory;
import at.fhooe.mcm.cas.contexttype.ContextElement;
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
		
		
		
		readAllFiles();
		
	}

	private void readAllFiles() {

		List<ContextElement> elements = new ArrayList<>();
		File folder = new File("files/XML");
		File[] listOfFiles = folder.listFiles();

		for (int i = 0; i < listOfFiles.length; i++) {
			File f = listOfFiles[i];
			if (f.isFile()) {

				try {
					System.out.println("Parsing file " + f.getName());
					Path path = FileSystems.getDefault().getPath(f.getAbsolutePath());
					String context = new String(Files.readAllBytes(path.toAbsolutePath()));
					elements.addAll(mParser.parse(context));

				} catch (IOException e) {
					e.printStackTrace();
				}

			} else if (listOfFiles[i].isDirectory()) {
				System.out.println("Directory " + f.getName());
			}
		}

		// notify
		for (ContextElement e : elements) {
			mMediator.notifyComponents(e, this);
		}

	}

	@Override
	public void onGeoObjectUpdated(GeoObject geoObject) {
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

	@Override
	public void onContextElementUpdated(ContextElement contextElement) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onGPSPositionUpdated(GPSPosition gpsPosition) {
		// TODO Auto-generated method stub
		
	}

}
