package at.fhooe.mcm.cas.gis;


import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.Panel;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import at.fhooe.mcm.cas.warningtype.IWarningType;

/**
 * Custom panel to draw warnings.
 * 
 * @author Oliver
 *
 */
public class WarningPanel extends Panel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final int IMAGE_SIZE = 30;
	
	List<IWarningType> mWarningTypes;
	
	
	@Override
	public void paint(Graphics _g) {
		drawWarnings();
	}
	
	public WarningPanel(LayoutManager lm) {
		super(lm);
		mWarningTypes = new ArrayList<IWarningType>();
		setPreferredSize(new Dimension(-1, IMAGE_SIZE + 5));
	}
	

	
	public void addWarningTypes(List<IWarningType> _warningType) {
		mWarningTypes.addAll(_warningType);
	}
	
	public List<IWarningType> getWarningTypes() {
		return mWarningTypes;
	}


	public void removeWarnings() {
		mWarningTypes.clear();
		clearPanel();
	}

	private void clearPanel() {
		repaint();
	}


	public void drawWarnings(List<IWarningType> newWarningTypes) {
		addWarningTypes(newWarningTypes);
		
		drawWarnings();
	}
	
	private void drawWarnings() {
		Graphics2D g2d = (Graphics2D) getGraphics();

		// draw warnings one after another
		for (int i = 0; i < mWarningTypes.size(); i++) {
			IWarningType w = mWarningTypes.get(i);
			BufferedImage image = w.getBufferedImage();
			Image scaledImage = image.getScaledInstance(IMAGE_SIZE, IMAGE_SIZE, Image.SCALE_SMOOTH);
		    g2d.drawImage(scaledImage, IMAGE_SIZE * i, 0, null);
		    
		}
		g2d.dispose();
	}

}
