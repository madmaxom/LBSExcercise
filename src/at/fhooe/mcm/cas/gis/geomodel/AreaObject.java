package at.fhooe.mcm.cas.gis.geomodel;

import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.util.Vector;

/**
 * Represents an area object (kind of polygon).
 * 
 * @author Oliver
 *
 */
public class AreaObject extends ObjectPart {

	/**
	 * Line defines this area object.
	 */
	private Line mLine;
	
	public AreaObject(Line _line) {
		mLine = _line;
	}

	@Override
	public Rectangle getBounds() {
		return mLine.getBounds();
	}
	
	/**
	 * Gets the line which defines this area object.
	 * @return defining line
	 */
	public Line getLine() {
		return mLine;
	}
	
	/**
	 * Gets a polygon out of the line of this object.
	 * @return polygon representing this object
	 */
	public Polygon getPolygon() {
		Vector<Point> points = mLine.getPoints();
		Polygon poly = new Polygon();
		for (Point p : points) {
			poly.addPoint(p.x, p.y);
		}
		return poly;
	}
}