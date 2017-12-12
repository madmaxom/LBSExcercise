package at.fhooe.mcm.cas.gps;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Panel;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Vector;

/**
 * View draws position of every visible satellite.
 * @author Oliver
 *
 */
public class SatView extends Panel {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Height of a basic oval needed for satellites.
	 */
	private static final int OVAL_HEIGHT = 40;
	/**
	 * Radius of circle drawn from the middle
	 */
	private static final int RADIUS = 200;
	private Color SAT_VISIBLE = Color.orange;
	private Color SAT_USED_FOR_CALC = Color.green;
	private Color SAT_NOT_VALID = Color.red;
	private Color DRAW_COLOR = Color.black;
	
	/**
	 * Satellite position on the view.
	 */
	private Vector<Point> mSatellitePoints;
	/**
	 * All satellite info
	 */
	private Vector<SatelliteInfo> mSatelliteInfos;
	/**
	 * Current NMWA-info block.
	 */
	private NMEAInfo mNMEAInfo;
	
	/**
	 * Constructor
	 */
	public SatView() {
		mSatellitePoints = new Vector<Point>();
		mSatelliteInfos = new Vector<SatelliteInfo>();
	}
	
	@Override
	public void paint(Graphics _g) {
		_g.setColor(DRAW_COLOR);
		
		// point in the middle
		Rectangle bound = this.getBounds();
		_g.fillOval((int) bound.getCenterX(), (int) bound.getCenterY(), OVAL_HEIGHT / 2, OVAL_HEIGHT / 2);
		
		// draw oval in the middle of panel
		int diffX = (int) bound.getCenterX() - RADIUS;
		int diffY = (int) bound.getCenterY() - RADIUS;
		_g.drawOval(diffX, diffY, RADIUS * 2, RADIUS * 2);
		
		// draw N (north)
		_g.drawString("N", (int) bound.getCenterX(), diffY - 10);
		

		// draw satellites
		for (int i = 0; i < mSatelliteInfos.size(); i++) {
			Point p = mSatellitePoints.get(i);
			SatelliteInfo info = mSatelliteInfos.get(i);
			
			// change color based on satellite info
			if (info.getSNR() <= 0) {
				_g.setColor(SAT_NOT_VALID);
			} else {
				_g.setColor(SAT_VISIBLE);
				
				// check if satellite is used for calculations
				for (int id : mNMEAInfo.getSatIdsForCalc()) {
					if (info.getId() == id) {
						_g.setColor(SAT_USED_FOR_CALC);
					}
				}
			}
			
			_g.drawOval(p.x - OVAL_HEIGHT / 2, p.y - OVAL_HEIGHT / 2, OVAL_HEIGHT, OVAL_HEIGHT);
			_g.drawString(String.valueOf(info.getId()), p.x - 5, p.y + 5);	
		}
	}
	
	/**
	 * Calculates position on view and saved all info for drawing. Triggers repaint.
	 * @param _info new NMEA-info
	 */
	public void update(NMEAInfo _info) {
		// System.out.println("SAT-VIEW");
		mNMEAInfo = _info;
		mSatellitePoints.clear();
		mSatelliteInfos.clear();
		mSatelliteInfos = _info.getSatInfos();
		
		for (SatelliteInfo s : _info.getSatInfos()) {
			
			double d = Math.cos(Math.toRadians(s.getAngleVert())) * RADIUS;
			// satellite info angle starts from north clockwise
			// sin starts from 0 (East) counter-clockwise
			// correct with -90 * (-1)
			double y = Math.sin(Math.toRadians((s.getAngleHor() - 90) * (-1))) * d;
			double x = Math.cos(Math.toRadians((s.getAngleHor() - 90)  * (-1))) * d;
			
			// get and save point for drawing on screen
			Point p = getPointOnView(x, y);
			mSatellitePoints.addElement(p);
			
		}
		repaint();
	}

	/**
	 * Calculates position of point on the view.
	 * @param x x-position from middle point
	 * @param y y-position from middle point
	 * @return coordinates on view
	 */
	private Point getPointOnView(double x, double y) {
		Rectangle bound = this.getBounds();
		Point p = new Point((int)(x + bound.getCenterX()), (int)(y * (-1) + bound.getCenterY()));
		return p;
	}
	
	public void setDrawingContext(String str) {
		switch(str) {
			case "DAY":
				DRAW_COLOR = Color.black;
				break;
			case "NIGHT":
				DRAW_COLOR = Color.white;
				break;
		}
		repaint();
	}
	
}
