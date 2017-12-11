package at.fhooe.mcm.cas.warningtype;

import java.awt.image.BufferedImage;

public abstract class IWarningType {
	
	public static final String IMAGE_BASE_PATH = "icons/warning/";
	
	protected BufferedImage image;
	
	public BufferedImage getBufferedImage() {
		return image;
	}
}
