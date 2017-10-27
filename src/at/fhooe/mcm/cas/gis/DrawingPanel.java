package at.fhooe.mcm.cas.gis;


import java.awt.Graphics;
import java.awt.Panel;
import java.awt.image.BufferedImage;

/**
 * Custom panel to draw on.
 * 
 * @author Oliver
 *
 */
public class DrawingPanel extends Panel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Image to draw on screen.
	 */
	BufferedImage mBufImg;

	@Override
	public void paint(Graphics _g) {
		
		if (mBufImg != null) {
			_g.drawImage(mBufImg, 0, 0, null);
		}
	}

	/**
	 * Sets buffered image and repaints itself.
	 * 
	 * @param _data data to draw
	 */
	public void drawBufferedImage(BufferedImage _data) {
		mBufImg = _data;
		repaint();
	}
	

}
