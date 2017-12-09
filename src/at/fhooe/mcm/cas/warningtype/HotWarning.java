package at.fhooe.mcm.cas.warningtype;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class HotWarning extends IWarningType {

	public HotWarning() {
		try {
			image = ImageIO.read(new File("warning/hot.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
