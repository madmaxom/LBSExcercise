package at.fhooe.mcm.cas;

import java.awt.Panel;

import at.fhooe.mcm.cas.gis.geomodel.GeoObject;
import at.fhooe.mcm.cas.gps.GPSReceiverController;
import at.fhooe.mcm.cas.gps.GPSReceiverSim;
import at.fhooe.mcm.cas.gps.GPSReceiverView;
import at.fhooe.mcm.cas.gps.NMEAParser;
import at.fhooe.mcm.cas.gps.PositionUpdateListener;

public class GPSComponent extends IComponent implements CommunicationObserver {

	private static final String FILENAME = "logs/GPS-Log-I.log";
	private static final int SLEEP = 100; // half a second
	private static final String FILTER = "GGA";
	
	private Panel mPanel;
	
	public GPSComponent(IMediator mediator, String name) {
		super(mediator, name);
		
		GPSReceiverController c = new GPSReceiverController();
		GPSReceiverView v = new GPSReceiverView(c);
		
		mPanel = v.getPanel();
		
		// TODO: uncomment
		try {
			// initialize receiver
			GPSReceiverSim sim = new GPSReceiverSim(FILENAME, SLEEP, FILTER);
			NMEAParser parser = new NMEAParser(sim);
			
			parser.addObserver((PositionUpdateListener) v); 
			
			// start parsing
			new Thread(parser).start();
			
		} catch (Exception _e) {
			_e.printStackTrace();
		}
	}
	
	
	@Override
	public Panel getView() {
		return mPanel;
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

}
