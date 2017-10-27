package at.fhooe.mcm.cas.gis.geomodel;

import java.awt.Point;
import java.awt.Rectangle;

/**
 * Represents an point object.
 * 
 * @author Oliver
 *
 */
public class PointObject extends ObjectPart {

	/**
	 * Point representing this object.
	 */
	private Point mPoint;
	
	public PointObject(Point _point) {
		mPoint = _point;
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(mPoint.x, mPoint.y, 0, 0);
	}
	
	/**
	 * Gets the point representing this object.
	 * @return point of object
	 */
	public Point getPoint() {
		return mPoint;
	}
}
