package at.fhooe.mcm.cas.gis;

import java.awt.Point;
import java.util.Vector;

import at.fhooe.mcm.cas.gis.geomodel.*;
import de.intergis.JavaClient.comm.CgGeoConnection;
import de.intergis.JavaClient.comm.CgConnection;
import de.intergis.JavaClient.comm.CgGeoInterface;
import de.intergis.JavaClient.comm.CgResultSet;
import de.intergis.JavaClient.comm.CgStatement;
import de.intergis.JavaClient.comm.CgIGeoObject;
import de.intergis.JavaClient.comm.CgIGeoPart;

import de.intergis.JavaClient.gui.IgcConnection;

/**
 * Can connects to DummyServer and can loads objects from server.
 * 
 * @author Oliver
 *
 */
public class DummyGIS {
	// die Verbindung zum Geo-Server
	CgGeoConnection m_geoConnection = null;
	// das Anfrage-Interface des Geo-Servers
	CgGeoInterface m_geoInterface = null;

	public DummyGIS() {
	}

	public boolean init() {
		try {
			// der Geo-Server wird initialisiert
			m_geoConnection = new IgcConnection(new CgConnection("admin", "admin", "T:localhost:4949", null));
			// das Anfrage-Interface des Servers wird abgeholt
			m_geoInterface = m_geoConnection.getInterface();
			return true;
		} catch (Exception _e) {
			_e.printStackTrace();
		}
		return false;
	}

	/**
	 * Extrahiert einige Geoobjekte aus dem Server
	 */
	protected Vector<GeoObject> extractData(String _stmt) {
		try {
			CgStatement stmt = m_geoInterface.Execute(_stmt);
			CgResultSet cursor = stmt.getCursor();
			Vector<GeoObject> geoObjects = new Vector<GeoObject>();

			while (cursor.next()) {
				CgIGeoObject obj = cursor.getObject();
				System.out.println("NAME --> " + obj.getName());
				System.out.println("TYP  --> " + obj.getCategory());
				CgIGeoPart[] parts = obj.getParts();
				
				Vector<ObjectPart> objParts = new Vector<>();
				
				for (int i = 0; i < parts.length; i++) {
					System.out.println("PART " + i);
					int pointCount = parts[i].getPointCount();
					int[] xArray = parts[i].getX();
					int[] yArray = parts[i].getY();
					// Polygon poly = new Polygon(xArray, yArray, pointCount);
					
					Vector<Point> points = new Vector<>();
					for (int j = 0; j < pointCount; j++) {
						// System.out.println("[" + xArray[j] + " ; " + yArray[j] + "]");
						points.addElement(new Point(xArray[i], yArray[i]));
					} // for j
					
					
					ObjectPart objPart = new AreaObject(new Line(points));
					objParts.addElement(objPart);
					
				} // for i
				System.out.println();
				
				GeoObject geoObj = new GeoObject(obj.getName(), obj.getCategory(), objParts);
				geoObjects.addElement(geoObj);
				
				
			} // while cursor
			
			return geoObjects;
		} catch (Exception _e) {
			_e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] _argv) {
		DummyGIS server = new DummyGIS();
		if (server.init()) {
			Vector<GeoObject> objects = server.extractData("select * from data where type = 1101");
		}
	}
}
