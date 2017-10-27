package at.fhooe.mcm.cas.gps;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Controller of views, handles user interaction.
 * @author Oliver
 *
 */
public class GPSReceiverController extends WindowAdapter {

	@Override
	public void windowClosing(WindowEvent _e) {
		System.exit(0);
	}
}
