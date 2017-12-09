package at.fhooe.mcm.cas.warningtype;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class FuelWarning extends IWarningType {
	
	public FuelWarning() {
		try {
			image = ImageIO.read(new File("warning/fuel.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
