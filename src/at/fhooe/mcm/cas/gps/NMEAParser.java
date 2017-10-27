package at.fhooe.mcm.cas.gps;

import java.io.IOException;
import java.sql.Time;
import java.util.Vector;

/**
 * Parses NMEA-Data got from a GPS-Receiver and notifies views.
 * 
 * @author Oliver
 *
 */
public class NMEAParser implements Runnable {

	/**
	 * Counts lines to parse.
	 */
	private int mLineCounter = 0;
	/**
	 * Simulator to get NMEA-Data.
	 */
	private GPSReceiverSim mSim;
	/**
	 * List of observers who gets notified.
	 */
	private Vector<PositionUpdateListener> mObservers;

	/**
	 * New parsed NMEA-info, will be filled when parsed
	 */
	private NMEAInfo mNewInfo;
	/**
	 * Current NMEA-info. Ready for use, will not be changed.
	 */
	private NMEAInfo mCurInfo;

	/**
	 * Constructor
	 * @param _sim GPS-Receiver
	 */
	public NMEAParser(GPSReceiverSim _sim) {
		mSim = _sim;
		mObservers = new Vector<PositionUpdateListener>();
		mNewInfo = new NMEAInfo();
		mCurInfo = new NMEAInfo();
	}

	@Override
	public void run() {
		while (true) {
			String line;
			try {
				line = mSim.readLine();
				if (line != null) {
					// System.out.println(line);

					if (!line.isEmpty()) {
						parse(line);
					}

				} else {
					// eof
					saveAndNotify();
					break;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Validates data
	 * 
	 * @param _s payload to check
	 * @param _checkSum result which should match payload
	 * @return
	 */
	private boolean CRCCheck(String _s, String _checkSum) {
		byte[] b = _s.getBytes();
		byte xor = 0;
		for( int i = 0; i < b.length; i++ ) {
			   xor ^= b[i]; // xor
		}
		
		try {
			return xor == Integer.parseInt(_checkSum, 16);
		} catch (Exception _e) {
			return false;
		}
		
	}

	/**
	 * Parses a single line.
	 * @param _line line to parse
	 */
	private void parse(String _line) {
		mLineCounter++;

		// check CRC
		int startIndex = _line.indexOf("$") + 1;
		int endIndex = _line.indexOf("*");
		if (startIndex < 0 || endIndex < 0) {
			return;
		}
		String toCheck = _line.substring(startIndex, endIndex);
		String checkSum = _line.substring(endIndex + 1);
		boolean valid = CRCCheck(toCheck, checkSum);
		
		if (valid) {
		
			String[] data = toCheck.split(",", -1);
			System.out.println("Parsing line " + mLineCounter + ", data fields " + data.length);
			

			switch (data[0]) {
			case "GPGGA": {
				saveAndNotify();
	
				// time (UTC)
				if (!data[1].isEmpty()) {
					// parse time
					
					int hours = Integer.parseInt(data[1].substring(0, 2));
					int min = Integer.parseInt(data[1].substring(2, 4));
					int sec = Integer.parseInt(data[1].substring(4, 6));
					mNewInfo.setTime(new Time(hours, min, sec));
				}
	
				// lat/lng
				double lat = -1.0;
				double lng = -1.0;
				if (!data[2].isEmpty()) {
					double degrees = Double.parseDouble(data[2].substring(0, 2)); // degree-minutes
					double minutes = Double.parseDouble(data[2].substring(2, data[2].length()));
					lat = calculateDegrees(degrees, minutes);
					if (data[3].equals("S")) {
						lat *= (-1);
					}
				}
	
				if (!data[4].isEmpty()) {
					double degrees = Double.parseDouble(data[4].substring(0, 3)); // degree-minutes
					double minutes = Double.parseDouble(data[4].substring(3, data[4].length()));
					lng = calculateDegrees(degrees, minutes);
					if (data[5].equals("W")) {
						lng *= (-1);
					}
				}
				mNewInfo.setLat(lat);
				mNewInfo.setLng(lng);
	
				if (!data[6].isEmpty()) {
					mNewInfo.setFixQual(Integer.parseInt(data[6]));
				}
				if (!data[7].isEmpty()) {
					mNewInfo.setAmountSat(Integer.parseInt(data[7]));
				}
				if (!data[9].isEmpty()) {
					mNewInfo.setHeight(Double.parseDouble(data[9]));
				}
				break;
			}
			case "GPGSA": {
				// get id of satellites which are used for calculations
				for (int i = 3; i < 15; i++) {
					if (!data[i].isEmpty()) {
						mNewInfo.addSatIdForCalc(Integer.parseInt(data[i]));
					}
				}
				
				if (!data[15].isEmpty()) {
					mNewInfo.setPDOP(Double.parseDouble(data[15]));
				}
				if (!data[16].isEmpty()) {
					mNewInfo.setHDOP(Double.parseDouble(data[16]));
				}
				if (!data[17].isEmpty()) {
					mNewInfo.setVDOP(Double.parseDouble(data[17]));
				}
	
				break;
			}
			case "GPGSV": {
				for (int i = 4; i < data.length; i += 4) {
	
					int number = 0;
					int angleVert = 0;
					int angleHor = 0;
					int snr = 0;
	
					if (!data[i].isEmpty()) {
						number = Integer.parseInt(data[i]);
					}
					if (!data[i + 1].isEmpty()) {
						angleVert = Integer.parseInt(data[i + 1]);
					}
					if (!data[i + 2].isEmpty()) {
						angleHor = Integer.parseInt(data[i + 2]);
					}
					if (!data[i + 3].isEmpty()) {
						snr = Integer.parseInt(data[i + 3]);
					}
	
					SatelliteInfo sInfo = new SatelliteInfo(number, angleVert, angleHor, snr);
					mNewInfo.addSatelliteInfo(sInfo);
				}
	
				break;
			}
			default: {
				// System.out.println("not handled type: " + data[0]);
			}
			} // end switch
		} // if valid
	}

	/**
	 * Saved info to current one and notifies views.
	 */
	private void saveAndNotify() {
		// new NMEAInfo block detected
		mCurInfo = mNewInfo;
		notifyObservers(mCurInfo);
		mNewInfo = new NMEAInfo();
	}

	/**
	 * Calculates degrees from degree minutes
	 * @param degrees
	 * @param minutes
	 * @return
	 */
	private double calculateDegrees(double degrees, double minutes) {
		return degrees + (minutes / 60);
	}

	/**
	 * Adds observer for this object.
	 * 
	 * @param _observer
	 */
	public void addObserver(PositionUpdateListener _observer) {
		mObservers.add(_observer);
	}


	/**
	 * Notifies observer
	 * @param _info ready NMEA-Info
	 */
	private void notifyObservers(NMEAInfo _info) {
		for (PositionUpdateListener o : mObservers) {
			o.updatePosition(_info);
		}
	}
}
