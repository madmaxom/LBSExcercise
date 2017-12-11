package at.fhooe.mcm.cas.warningtype;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class PressureWarning extends IWarningType {

	public PressureWarning() {
		try {
			image = ImageIO.read(new File(IMAGE_BASE_PATH + "pressure.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
