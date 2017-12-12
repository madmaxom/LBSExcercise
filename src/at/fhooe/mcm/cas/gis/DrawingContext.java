package at.fhooe.mcm.cas.gis;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.util.Vector;
import javax.imageio.ImageIO;

import at.fhooe.mcm.cas.gis.geomodel.*;


/**
 * Helper class that provides methods to draw objects.
 * @author Oliver
 *
 */
public class DrawingContext {

	/**
	 * Absolute path to icon set for drawing POI-Objects.
	 */
//	private static final String PATH_TO_ICONS = "D:\\FH\\4Sem\\OIS\\uebungen\\ue03\\OIS-UE03\\src\\at\\fhooe\\mc\\ois\\ue03\\icons\\";
	private static final String PATH_TO_ICONS = "icons/";
	
	/**
	 * Draws object on the graphics.
	 * 
	 * @param _obj object to draw
	 * @param _g graphics to draw on
	 * @param _matrix matrix to manipulate object to draw on right position
	 * @param _POIEnabled indicates if POI should be drawn or not
	 */
	public static void drawObject(GeoObject _obj, Graphics _g, Matrix _matrix, boolean _POIEnabled) {
		// Get the right colors based on type
		Color fillColor;
		Color borderColor;
		switch (_obj.getType()) {
		// DummyServer-Types
		case 223: {
			fillColor = Color.WHITE;
			borderColor = Color.BLACK;
			break;
		}
		case 931: {
			fillColor = Color.RED;
			borderColor = Color.BLACK;
			break;
		}
		case 932: {
			fillColor = Color.ORANGE;
			borderColor = Color.RED;
			break;
		}
		case 1101: {
			fillColor = Color.MAGENTA;
			borderColor = Color.GREEN;
			break;
		}
		
		// PostGre-Types (OSM)
		// 1... highway
		case 1010: {
			// highway
			fillColor = Color.ORANGE;
			borderColor = fillColor;
			break;
		}
		// 2... waterway
		case 2001: {
			// residential
			fillColor = Color.BLUE;
			borderColor = fillColor;
			break;
		}
		// 5... landuse
		case 5001: {
			// residential
			fillColor = new Color(245, 234, 220); // beige
			borderColor = fillColor;
			break;
		}
		case 5002: {
			// industrial
			fillColor = new Color(206, 206, 224); // grey
			borderColor = Color.BLACK;
			break;
		}
		case 5003: {
			// commercial
			fillColor = new Color(230, 230, 242); // light grey/blue
			borderColor = Color.BLACK;
			break;
		}
		case 5004: {
			// forest
			fillColor = new Color(7, 99, 36); // dark green
			borderColor = fillColor;
			break;
		}
		case 5006: {
			// meadow
			fillColor = new Color(134, 247, 170); // light green
			borderColor = fillColor;
			break;
		}
		// 6... natural area
		case 6001: {
			// grassland
			fillColor = new Color(7, 219, 74); // light green
			borderColor = fillColor;
			break;
		}
		case 6002: {
			// wood
			fillColor = new Color(133, 98, 3); // brown
			borderColor = fillColor;
			break;
		}
		case 6005: {
			// water
			fillColor = Color.BLUE;
			borderColor = fillColor;
			break;
		}
		// 8... Boundaries
		case 8001: {
			// country
			fillColor = null;
			borderColor = Color.RED;
			break;
		}
		case 8005: {
			// administrative
			fillColor = null;
			borderColor = Color.PINK;
			break;
		}
		default: {
			fillColor = Color.WHITE;
			borderColor = Color.BLACK;
			break;
		}
		} // end switch type


		for (ObjectPart part : _obj.getObjectParts()) {
			if (part instanceof PointObject) {
				PointObject obj = (PointObject) part;
				Point p = _matrix.multiply(obj.getPoint());
				
				_g.setColor(fillColor);
				_g.fillOval(p.x, p.y, 2, 2);
			} else if (part instanceof LineObject) {
				LineObject obj = (LineObject) part;
				Vector<Point> points = obj.getLine().getPoints();
				for (int i = 0; i < points.size() - 1; i++) {
					Point p1 = _matrix.multiply(points.get(i));
					Point p2 = _matrix.multiply(points.get(i + 1));
					
					_g.setColor(fillColor);
					_g.drawLine(p1.x, p1.y, p2.x, p2.y);
				}	
			} else if (part instanceof AreaObject) {
				AreaObject obj = (AreaObject) part;
				Polygon poly = _matrix.multiply(obj.getPolygon());
				
				if (fillColor != null) {
					_g.setColor(fillColor);
					_g.fillPolygon(poly);
				}
				
				_g.setColor(borderColor);
				_g.drawPolygon(poly);
			}
		}
		

		if (_POIEnabled) {
			// check if it is a POI object
			if (_obj instanceof POIObject) {
				POIObject poiObj = (POIObject) _obj;
				// draw icon
				Rectangle bounds = _obj.getBounds();
				String filename = null;
				switch (poiObj.getPOIType()) {
				case 0: {
					filename = "icon1.png";
					break;
				}
				case 1: {
					filename = "icon2.png";
					break;
				}
				case 2: {
					filename = "icon3.png";
					break;
				}
				case 3: {
					filename = "icon4.png";
					break;
				}
				case 4: {
					filename = "icon5.png";
					break;
				}
				case 5: {
					filename = "icon6.png";
					break;
				}
				case 6: {
					filename = "map-pin-red.png";
					bounds.x += 1115000;
					bounds.y += 770000;
					//1706760,6096187
					break;
				}
				default: {
					filename = "icon1.png";
					break;
				}
				} // end switch POItype

				// load icon
				
				Image image = null;
				try {
					image = ImageIO.read(new File(PATH_TO_ICONS + filename));
				} catch (IOException e) {
					e.printStackTrace();
				}

				Point p = _matrix.multiply(new Point(bounds.x, bounds.y));
				_g.drawImage(image, p.x, p.y, null);
			}
		}
	}
}
