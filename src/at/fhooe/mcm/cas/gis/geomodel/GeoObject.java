package at.fhooe.mcm.cas.gis.geomodel;

import java.awt.Polygon;
import java.awt.Rectangle;
import java.util.Vector;

/**
 * Stellt ein einziges Geo-Object mit zusätzlichen Informationen bereit.
 * 
 * @author Oliver
 *
 */
public class GeoObject {

	/**
	 * Id des Objekts
	 */
	private String mId;
	/**
	 * Typ des Objekts
	 */
	private int mType;
	/**
	 * Teile des ganzen Objekts
	 */
	private Vector<ObjectPart> mObjectParts;

	/**
	 * Konstruktor
	 * 
	 * @param _id
	 *            Die Id des Objektes
	 * @param _type
	 *            Der Typ des Objektes
	 * @param _poly
	 *            Die Geometrie des Objektes
	 */
	public GeoObject(String _id, int _type, Vector<ObjectPart> _objectParts) {
		mId = _id;
		mType = _type;
		mObjectParts = _objectParts;
	}

	/**
	 * Liefert die Id des Geo-Objektes
	 * 
	 * @return Die Id des Objektes
	 * @see java.lang.String
	 */
	public String getId() {
		return mId;
	}

	/**
	 * Liefert den Typ des Geo-Objektes
	 * 
	 * @return Der Typ des Objektes
	 */
	public int getType() {
		return mType;
	}

	/**
	 * Liefert die Geometrie des Geo-Objektes
	 * 
	 * @return das Polygon des Objektes
	 */
	public Vector<ObjectPart> getObjectParts() {
		return mObjectParts;
	}

	/**
	 * Liefert die Bounding Box der Geometrie
	 * 
	 * @return die Boundin Box der Geometrie als Rechteckobjekt
	 * @see java.awt.Rectangle
	 */
	public Rectangle getBounds() {
		if (mObjectParts.size() > 0) {
			Rectangle boundingBox = mObjectParts.get(0).getBounds();
			for (int i = 1; i < mObjectParts.size(); i++) { // start index 1
				// go over all others and create bounding box
				boundingBox = boundingBox.union(mObjectParts.get(i).getBounds());
			}
			return boundingBox;
		}
		return null;
	}

	/**
	 * Gibt die internen Informationen des Geo-Objektes als * String zurueck
	 * 
	 * @return Der Inhalt des Objektes in Form eines Strings
	 */
	public String toString() {
		StringBuffer buf = new StringBuffer();
		buf.append("ID: ");
		buf.append(mId);
		buf.append("\n");
		buf.append("Type: ");
		buf.append(mType);
		buf.append("\n");
		/*
		 * buf.append("Polygon: "); for (int i = 0; i < mPoly.npoints; i++) {
		 * buf.append("["); buf.append(mPoly.xpoints[i]); buf.append(" ; ");
		 * buf.append(mPoly.ypoints[i]); buf.append("]"); buf.append("\n"); }
		 */
		return buf.toString();
	}
}
