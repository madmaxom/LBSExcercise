package at.fhooe.mcm.cas.warningtype;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class PositionWarning extends IWarningType {

	public PositionWarning() {
		try {
			image = ImageIO.read(new File(IMAGE_BASE_PATH + "position.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
