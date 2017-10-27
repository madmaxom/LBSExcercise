package at.fhooe.mcm.cas.gis;

import java.awt.image.BufferedImage;

/**
 * Provides methods to update views.
 * 
 * @author Oliver
 *
 */
public interface DataObserver {
	/**
	 * Updates 
	 * @param _data new image to render
	 */
	public void update(BufferedImage _data);
	
	public void update(int _mapScale);
}
