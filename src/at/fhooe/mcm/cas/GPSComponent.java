package at.fhooe.mcm.cas;

import java.awt.Panel;

import at.fhooe.mcm.cas.gps.GPSReceiverController;
import at.fhooe.mcm.cas.gps.GPSReceiverSim;
import at.fhooe.mcm.cas.gps.GPSReceiverView;
import at.fhooe.mcm.cas.gps.NMEAParser;
import at.fhooe.mcm.cas.gps.PositionUpdateListener;

public class GPSComponent implements IComponent {

	private String mName;
	private static final String FILENAME = "logs/GPS-Log-I.log";
	private static final int SLEEP = 100; // half a second
	private static final String FILTER = "GGA";
	
	private Panel mPanel;
	
	public GPSComponent() {
		mName = "GPS";
		
		GPSReceiverController c = new GPSReceiverController();
		GPSReceiverView v = new GPSReceiverView(c);
		
		mPanel = v.getPanel();
		
		// TODO: uncomment
		/*
		try {
			// initialize receiver
			GPSReceiverSim sim = new GPSReceiverSim(FILENAME, SLEEP, FILTER);
			NMEAParser parser = new NMEAParser(sim);
			
			parser.addObserver((PositionUpdateListener) v); 
			
			// start parsing
			parser.run();
			
		} catch (Exception _e) {
			_e.printStackTrace();
		}
		*/
	}
	
	
	@Override
	public Panel getView() {
		return mPanel;
	}

	@Override
	public String getName() {
		return mName;
	}

}
