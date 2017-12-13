package at.fhooe.mcm.cas.gis;

/**
 * Class for main entry point of application.
 * @author Oliver
 *
 */
public class GISMain {

	/**
	 * Main entry point of application.
	 * @param args
	 */
	public static void main(String[] args) {
		
		GISModel m = new GISModel();
		GISController c = new GISController(m);
		GISViewPedastrian v = new GISViewPedastrian();
		v.setActionListener(c);
		v.setComponentListener(c);
		v.setItemListener(c);
		v.setKeyListener(c);
		v.setMouseListener(c);
		v.setMouseMotionListener(c);
		v.setMouseWheelListener(c);
		v.createPanel();
		c.addView(v);

		m.addObserver(v);
	}
}
