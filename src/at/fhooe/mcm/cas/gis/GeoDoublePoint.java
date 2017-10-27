package at.fhooe.mcm.cas.gis;

/**
 * This class represents a point object with double precision.
 * 
 * @author Oliver
 *
 */
public class GeoDoublePoint {

	double mX;
	double mY;
	
	public GeoDoublePoint(double _x, double _y) {
		mX = _x;
		mY = _y;
	}
	
	/**
	 * Gets length of point.
	 * @return length of point.
	 */
	public double length() {
		return Math.hypot(mX, mY);
	}
	
	@Override
	public String toString() {
		StringBuffer buf = new StringBuffer();
		buf.append("GeoDoublePoint: ");
		buf.append("[ ");
		buf.append(mX);
		buf.append(", ");
		buf.append(mY);
		buf.append(" ]");
		return buf.toString();
	}
}
