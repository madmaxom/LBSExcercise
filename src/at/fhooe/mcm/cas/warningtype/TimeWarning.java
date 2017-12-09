package at.fhooe.mcm.cas.warningtype;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class TimeWarning extends IWarningType {

	public TimeWarning() {
		try {
			image = ImageIO.read(new File("warning/time.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
