package at.fhooe.mcm.cas.gps;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * GPS-Receiver simulates with simple text file (NMEA-file).
 * @author Oliver
 *
 */
public class GPSReceiverSim extends BufferedReader {

	/**
	 * Sleep after NMEA-block (simulates calculations)
	 */
	private int mSleep;
	/**
	 * Start/end of NMEA-block
	 */
	private String mFilter;
	
	/**
	 * Initializes receiver.
	 * @param _filename NMEA-file to read
	 * @param _sleep pause after block
	 * @param _filter start tag of NMEA-block
	 * @throws FileNotFoundException
	 */
	public GPSReceiverSim(String _filename, int _sleep, String _filter) throws FileNotFoundException {
		super(new FileReader(_filename));
		mSleep = _sleep;
		mFilter = _filter;
	}
	
	/**
	 * Reads one line. If block end is reached, it sleeps a while.
	 */
	public String readLine() throws IOException {
		try {
			String line = super.readLine();
			if (line != null) { 
				// not eof
				if (line.contains(mFilter)) {
					Thread.sleep(mSleep);	
				}
			} 
			return line;
		} catch (InterruptedException _e) {
			_e.printStackTrace();
		}
		return null;
	    
	}
}
