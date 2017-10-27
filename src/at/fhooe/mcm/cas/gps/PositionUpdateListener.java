package at.fhooe.mcm.cas.gps;

/**
 * Interface to notify view.
 * @author Oliver
 *
 */
public interface PositionUpdateListener {
	/**
	 * Updates data with new NMEA-Info
	 * @param _info new data
	 */
	void updatePosition(NMEAInfo _info);
}
