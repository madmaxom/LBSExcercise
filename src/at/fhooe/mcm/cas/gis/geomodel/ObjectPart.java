package at.fhooe.mcm.cas.gis.geomodel;

import java.awt.Rectangle;
import java.util.Vector;

public abstract class ObjectPart {
	
	/**
	 * Stores objectParts which are inside this object.
	 */
	Vector<ObjectPart> mObjectParts;
	
	public ObjectPart() {
		mObjectParts = new Vector<ObjectPart>();
	}
	
	/**
	 * Adds objectPart to this object.
	 * @param _objectPart part to add
	 */
	public void addObjectPart(ObjectPart _objectPart) {
		mObjectParts.addElement(_objectPart);
	}
		
	/**
	 * Gets the rectangle bounding box of this object.
	 * @return bounding box
	 */
	public abstract Rectangle getBounds();

}
