package at.fhooe.mcm.cas.gps;

import java.sql.Time;
import java.util.Vector;

/**
 * Holds all NMEA-info got GPS-Receiver
 * @author Oliver
 *
 */
public class NMEAInfo {

	private double mLat; // GGA
	private double mLng; // GGA
	private Time mTime; // GGA
	private int mAmountSat; // GGA
	private int mFixQual; // GGA
	private double mHeight; // GGA
	
	private double mHDOP; // GSA
	private double mPDOP; // GSA
	private double mVDOP; // GSA
	
	/**
	 * IDs of all satellites which are used for calculations.
	 */
	private Vector<Integer> mSatIdsForCalc; // GSA

	/**
	 * List of all visible satellites.
	 */
	private Vector<SatelliteInfo> mSatInfos; // GSV
	
	/**
	 * Constructor
	 */
	public NMEAInfo() {
		mSatInfos = new Vector<SatelliteInfo>();
		setSatIdsForCalc(new Vector<Integer>());
	}
	
	public void addSatelliteInfo(SatelliteInfo _info) {
		mSatInfos.addElement(_info);
	}


	public double getLat() {
		return mLat;
	}


	public void setLat(double _lat) {
		this.mLat = _lat;
	}


	public double getLng() {
		return mLng;
	}


	public void setLng(double _lng) {
		this.mLng = _lng;
	}


	public Time getTime() {
		return mTime;
	}


	public void setTime(Time _time) {
		this.mTime = _time;
	}


	public int getAmountSat() {
		return mAmountSat;
	}


	public void setAmountSat(int _amountSat) {
		this.mAmountSat = _amountSat;
	}


	public double getPDOP() {
		return mPDOP;
	}


	public void setPDOP(double _PDOP) {
		this.mPDOP = _PDOP;
	}


	public double getHDOP() {
		return mHDOP;
	}


	public void setHDOP(double _HDOP) {
		this.mHDOP = _HDOP;
	}


	public double getVDOP() {
		return mVDOP;
	}


	public void setVDOP(double _VDOP) {
		this.mVDOP = _VDOP;
	}


	public int getFixQual() {
		return mFixQual;
	}


	public void setFixQual(int _fixQual) {
		this.mFixQual = _fixQual;
	}


	public Vector<SatelliteInfo> getSatInfos() {
		return mSatInfos;
	}


	public void setSatInfos(Vector<SatelliteInfo> _satInfos) {
		this.mSatInfos = _satInfos;
	}

	public double getHeight() {
		return mHeight;
	}

	public void setHeight(double _height) {
		this.mHeight = _height;
	}

	public Vector<Integer> getSatIdsForCalc() {
		return mSatIdsForCalc;
	}
	
	public void addSatIdForCalc(int _satId) {
		mSatIdsForCalc.addElement(_satId);
	}

	public void setSatIdsForCalc(Vector<Integer> _satIdsForCalc) {
		this.mSatIdsForCalc = _satIdsForCalc;
	}
	
}
