package at.fhooe.mcm.cas.warningtype;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BlackIceWarning extends IWarningType {

	public BlackIceWarning() {
		try {
			image = ImageIO.read(new File("warning/black_ice.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
