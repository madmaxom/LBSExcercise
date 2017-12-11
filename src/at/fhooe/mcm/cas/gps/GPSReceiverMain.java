package at.fhooe.mcm.cas.gps;


/**
 * Main entry point of application. Starts parser.
 * 
 * @author Oliver
 *
 */
public class GPSReceiverMain {
	
	private static final String FILENAME = "files/gpslogs/GPS-Log-I.log";
	private static final int SLEEP = 100; // half a second
	private static final String FILTER = "GGA";

	/**
	 * Main entry point of application.
	 * @param args
	 */
	public static void main(String[] args) {
		
		GPSReceiverController c = new GPSReceiverController();
		GPSReceiverView v = new GPSReceiverView(c);
		
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
	}

}
