package at.fhooe.mcm.cas.warningtype;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class LifeThreateningWarning extends IWarningType {
	
	public LifeThreateningWarning() {
		try {
			image = ImageIO.read(new File("warning/life_threatening.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
