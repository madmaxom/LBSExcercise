package at.fhooe.mcm.cas.gps;

/**
 * Stores all data about a single satellite.
 * @author Oliver
 *
 */
public class SatelliteInfo {

	private int mId;
	private int mAngleVert;
	private int mAngleHor;
	private int mSNR; // in dB
	
	/**
	 * Constructor
	 * @param _id id of satellite
	 * @param _angleVert vertical angle
	 * @param _angleHor horizontal angle
	 * @param _SNR signal to noise ratio
	 */
	public SatelliteInfo(int _id, int _angleVert, int _angleHor, int _SNR) {
		setId(_id);
		setAngleVert(_angleVert);
		setAngleHor(_angleHor);
		setSNR(_SNR);
	}

	public int getId() {
		return mId;
	}

	public void setId(int _id) {
		this.mId = _id;
	}

	public int getAngleVert() {
		return mAngleVert;
	}

	public void setAngleVert(int _angleVert) {
		this.mAngleVert = _angleVert;
	}

	public int getAngleHor() {
		return mAngleHor;
	}

	public void setAngleHor(int _angleHor) {
		this.mAngleHor = _angleHor;
	}

	public int getSNR() {
		return mSNR;
	}

	public void setSNR(int _SNR) {
		this.mSNR = _SNR;
	}
	
}
