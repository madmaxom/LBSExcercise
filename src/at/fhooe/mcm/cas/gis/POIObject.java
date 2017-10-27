package at.fhooe.mcm.cas.gis;

import java.util.Vector;

import at.fhooe.mcm.cas.gis.geomodel.*;

/**
 * Represents a Point-of-Interest-Object.
 * 
 * @author Oliver
 *
 */
public class POIObject extends GeoObject {

	/**
	 * Type of point of interest.
	 */
	private int mPOIType;
	
	public POIObject(String _id, int _type, Vector<ObjectPart> _parts, int _POIType) {
		super(_id, _type, _parts);
		mPOIType = _POIType;
	}
	
	/**
	 * Gets POI-type
	 * @return POI-type
	 */
	public int getPOIType() {
		return mPOIType;
	}
	
	/**
	 * Sets POI-type
	 * @param _POIType POI-Type to set.
	 */
	public void setPOIType(int _POIType) {
		mPOIType = _POIType;
	}
}
