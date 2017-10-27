package at.fhooe.mcm.cas.gis.geomodel;

import java.awt.Rectangle;

/**
 * Represents an line object.
 * 
 * @author Oliver
 *
 */
public class LineObject extends ObjectPart {

	/**
	 * Line which represents this object.
	 */
	private Line mLine;
	
	public LineObject(Line _line) {
		mLine = _line;
	}

	@Override
	public Rectangle getBounds() {
		return mLine.getBounds();
	}
	
	/**
	 * Gets the line which represents this object.
	 * @return line of object
	 */
	public Line getLine() {
		return mLine;
	}
}
