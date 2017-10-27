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
		GISView v = new GISView(c);
		c.addView(v);

		m.addObserver(v);
	}
}
