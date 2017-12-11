package at.fhooe.mcm.cas.warningtype;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class HumidityWarning extends IWarningType {
	
	public HumidityWarning() {
		try {
			image = ImageIO.read(new File(IMAGE_BASE_PATH + "humidity.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
