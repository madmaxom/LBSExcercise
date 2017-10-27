package at.fhooe.mcm.cas.gis.geomodel;

import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.util.Vector;

/**
 * Represents a single line.
 * 
 * @author Oliver
 *
 */
public class Line {
	/**
	 * List of points which represents a line.
	 */
	private Vector<Point> mPoints;
	
	public Line(Vector<Point> _points) {
		mPoints = _points;
	}
	
	/**
	 * Gets bounding rectangle of this object.
	 * @return bounding box
	 */
	public Rectangle getBounds() {
		int[] x = new int[mPoints.size()];
		int[] y = new int[mPoints.size()];
		for (int i = 0; i < mPoints.size(); i++) {
			x[i] = mPoints.get(i).x;
			y[i] = mPoints.get(i).y;
		}
		Polygon poly = new Polygon(x, y, mPoints.size());
		return poly.getBounds();
	}
	
	/**
	 * Gets all points which represents this object.
	 * @return points of this object
	 */
	public Vector<Point> getPoints() {
		return mPoints;
	}
}
