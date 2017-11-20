package at.fhooe.mcm.cas.gis;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.imageio.ImageIO;

import org.postgis.Geometry;
import org.postgis.LinearRing;
import org.postgis.PGgeometry;
import org.postgis.Polygon;
import org.postgresql.PGConnection;
import org.postgresql.util.PGobject;

import at.fhooe.mcm.cas.gis.geomodel.*;


/**
 * Model holds data and notifies observers if something changes.
 * 
 * @author Oliver
 *
 */
public class GISModel {
	
	/**
	 * One inch
	 */
	private static final double INCH_IN_CM = 2.54;
	/**
	 * Standard value for calculating map scale.
	 */
	private static final int mDotPerInch = 72;
	
	/**
	 * Default drawing panel width.
	 */
	private int mDrawingPanelWidth = 600;
	/**
	 * Default drawing panel height.
	 */
	private int mDrawingPanelHeight = 400;
	/**
	 * List of all observers of this class.
	 */
	private List<DataObserver> mObservers;
	
	/**
	 * All objects gets drawn on this image.
	 * Image will be passed to observers.
	 */
	private BufferedImage mBufImg;
	
	/**
	 * Matrix for polygon transformations.
	 */
	private Matrix mMatrix;
	
	/**
	 * Storage of all original GeoObjects.
	 */
	private Vector<GeoObject> mOrigGeoObjects;
	private Vector<GeoObject> mPOIObjects = new Vector<GeoObject>();
	
	/**
	 * Stores map scale.
	 */
	private int mMapScale;
	
	/**
	 * Enable or disable POI-icons
	 */
	private boolean mPOIEnabled = true;
	
	/**
	 * Indicator for loading data sticky
	 */
	private Rectangle mStickyBounds;
	
	/**
	 * 
	 */
	private boolean mSticky;
	
	/**
	 * Indicates the different servers.
	 * 0 = DummyServer
	 * 1 = PostGre-Server
	 */
	private int mServerNr = 1;
	
	

	
	/**
	 * Constructor, initializes member variables.
	 */
	public GISModel() {
		mObservers = new ArrayList<>();
		createBufferedImage();
		mMatrix = new Matrix();
		mOrigGeoObjects = new Vector<GeoObject>();
		mStickyBounds = new Rectangle();
	}
	
	private void createBufferedImage() {
		mBufImg = new BufferedImage(mDrawingPanelWidth, mDrawingPanelHeight, BufferedImage.TYPE_INT_RGB);
	}
	
	/**
	 * Adds an observer to notify them on changes.
	 * @param _v observer who wants to get notified
	 */
	public void addObserver(DataObserver _v) {
		mObservers.add(_v);
	}
	

	/**
	 * Recreates buffered image with the new size.
	 * @param _d new size of frame
	 */
	public void changedSize(Dimension _d) {
		mDrawingPanelWidth = (int) _d.getWidth();
		mDrawingPanelHeight = (int) _d.getHeight();
		createBufferedImage();
		drawAllGeoObjects();
		notifyObservers();
	}
	
	/**
	 * Notifies all observers.
	 */
	private void notifyObservers() {
		// notify
		for (DataObserver o : mObservers) {
			o.update(mBufImg);
			o.update(mMapScale);
		}
	}
	
	/**
	 * Draws all objects on the buffered image.
	 */
	private void drawAllGeoObjects() {
		// clear image
		createBufferedImage();
		
		// draw all geo objects
		Vector<GeoObject> gos = (Vector<GeoObject>) mOrigGeoObjects.clone();
		for (GeoObject obj : gos) {
			DrawingContext.drawObject(obj, mBufImg.getGraphics(), mMatrix, mPOIEnabled);
		}
		// draw all poi objects
		Vector<GeoObject> pos = (Vector<GeoObject>) mPOIObjects.clone();
		for (GeoObject obj : pos) {
			DrawingContext.drawObject(obj, mBufImg.getGraphics(), mMatrix, mPOIEnabled);
		}
		calculateScale();
	}
	
	public void switchPOI() {
		mPOIEnabled = !mPOIEnabled;
		drawAllGeoObjects();
		notifyObservers();
	}

	/**
	* Stellt intern eine Transformationsmatrix zur Verfuegung, die so
	* skaliert, verschiebt und spiegelt, dass die zu zeichnenden Polygone * komplett in den Anzeigebereich passen
	*/
	public void zoomToFit() {
		Rectangle win = new Rectangle(new Dimension(mDrawingPanelWidth, mDrawingPanelHeight));
		Rectangle world = getMapBounds(mOrigGeoObjects);
		mMatrix = Matrix.zoomToFit(world, win);
		drawAllGeoObjects();
		notifyObservers();
	}
	
	/**
	* Veraendert die interne Transformationsmatrix so, dass in das
	* Zentrum des Anzeigebereiches herein- bzw. herausgezoomt wird
	*
	* @param _factor Der Faktor um den herein- bzw. herausgezoomt wird 
	*/
	public void zoom(double _factor) {
		// zoom to middle of panel
		Point zoomPt = new Point(mDrawingPanelWidth / 2, mDrawingPanelHeight / 2);
		
		zoom(zoomPt, _factor);
	}
	
	/**
	* Veraendert die interne Transformationsmatrix so, dass an dem
	* uebergebenen Punkt herein- bzw. herausgezoomt wird
	* @param _pt Der Punkt an dem herein- bzw. herausgezoomt wird
	* @param _factor Der Faktor um den herein- bzw. herausgezoomt wird 
	*/
	public void zoom(Point _pt, double _factor) {
		mMatrix = Matrix.zoomPoint(mMatrix, _pt, _factor);
		drawAllGeoObjects();
		notifyObservers();
	}
	
	/** 
	* Ermittelt die gemeinsame BoundingBox der uebergebenen Polygone
	* @param _poly Die Polygone, fuer die die BoundingBox berechnet werden soll
	* @return Die BoundingBox
	*/
	public Rectangle getMapBounds(Vector<GeoObject> _objects) {
		if (_objects.size() > 0) {
			
			Rectangle boundingBox = _objects.get(0).getBounds();
			for (GeoObject obj : _objects) {
				boundingBox = boundingBox.union(obj.getBounds());
			}
			return boundingBox;
		} else {
			// default: take size of panel 
			return new Rectangle(mDrawingPanelWidth, mDrawingPanelHeight);
		}
	}
	
	/**
	 * Rotiert das gesamte Object um den Mittelpunkt des Objekts.
	 * 
	 * @param _alpha Winkel in Radiant
	 */
	public void rotate(double _alpha) {
		// translate to origin, rotate, translate back
		
		Matrix t1 = Matrix.translate(-mDrawingPanelWidth / 2, -mDrawingPanelHeight / 2);
		Matrix r = Matrix.rotate(_alpha);
		Matrix t2 = Matrix.translate(mDrawingPanelWidth / 2, mDrawingPanelHeight / 2);
		mMatrix = t2.multiply(r.multiply(t1.multiply(mMatrix)));
		drawAllGeoObjects();
		notifyObservers();
	}
	
	/**
	* Veraendert die interne Transformationsmatrix so, dass
	* die zu zeichnenden Objekt horizontal verschoben werden.
	* @param _delta Die Strecke, um die verschoben werden soll 
	*/
	public void scrollHorizontal(int _delta) {
		Matrix t = Matrix.translate(_delta, 0);
		mMatrix = t.multiply(mMatrix);
		drawAllGeoObjects();
		notifyObservers();
	}
	
	/**
	* Veraendert die interne Transformationsmatrix so, dass
	* die zu zeichnenden Objekt horizontal verschoben werden.
	* @param _delta Die Strecke, um die verschoben werden soll 
	*/
	public void scrollVertical(int _delta) {
		Matrix t = Matrix.translate(0, _delta);
		mMatrix = t.multiply(mMatrix);
		drawAllGeoObjects();
		notifyObservers();
	}
	
	/**
	 * Loads data from Dummy-Server and saved it in vector.
	 */
	public void loadData() {
		System.out.println("Loading data ...");
		switch (mServerNr) {
		case 0: {
			loadFromDummyServer();
			break;
		}
		case 1: {
			loadFromPostGre();
			break;
		}
		default: 
			
		}
		if (!mSticky) {
			// zoom only when all data is there
			zoomToFit();
		}
		

	}
	
	/**
	 * Loads data from a PostGre-Server.
	 */
	private void loadFromPostGre() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				Connection conn;
				
				try {
					// Load the JDBC driver and establish connection
					Class.forName("org.postgresql.Driver");
					String url = "jdbc:postgresql://localhost:5432/osm_austria"; // osm_hawai, osm_faroe_island, osm_austria
					conn = DriverManager.getConnection(url, "geo", "geo");
					
					// Add the geometry types to the connection
					PGConnection c = (PGConnection) conn;
					c.addDataType("geometry", (Class<? extends PGobject>) Class.forName("org.postgis.PGgeometry"));
					c.addDataType("box2d", (Class<? extends PGobject>) Class.forName("org.postgis.PGbox2d"));
			
					// create statement and execute a select query
					Statement s = conn.createStatement();

					String sqlEnvelope = "";
					if (mSticky) {
						// load only within bounds
						sqlEnvelope = " AND a.geom && ST_MakeEnvelope(" 
						+ mStickyBounds.getX()
						+ ", " + mStickyBounds.getY()
						+ ", " + (mStickyBounds.getX() + mStickyBounds.getWidth())
						+ ", " + (mStickyBounds.getY() + mStickyBounds.getHeight()) 
						+ ")";
					}
					ResultSet r;
					r = s.executeQuery("SELECT * FROM boundary_area AS a WHERE a.type IN (8000, 8001, 8002)" + sqlEnvelope + ";");
					createGeoObjects(r);
					
					r = s.executeQuery("SELECT * FROM boundary_multiarea AS a WHERE a.type IN (8000, 8001, 8002)" + sqlEnvelope + ";");
					createGeoObjects(r);
					
					
					// waterway lines (river)
					r = s.executeQuery("SELECT * FROM waterway_line AS a WHERE a.type IN (2001)" + sqlEnvelope + ";");
					createGeoObjects(r);
					
					// TODO: uncomment to get all data
//					/*
					// residential, industrial, commercial, forest, meadow
					r = s.executeQuery("SELECT * FROM landuse_area AS a WHERE a.type IN (5001, 5002, 5003, 5004, 5006)" + sqlEnvelope + ";");
					createGeoObjects(r);
					
					// grassland, wood, water
					r = s.executeQuery("SELECT * FROM natural_area AS a WHERE a.type IN (6001, 6002, 6005);" + sqlEnvelope + ";");
					createGeoObjects(r);
					
					// highway
					r = s.executeQuery("SELECT * FROM highway_line AS a WHERE a.type IN (1010)" + sqlEnvelope + ";");
					createGeoObjects(r);
					
					// natural lines
					r = s.executeQuery("SELECT * FROM natural_line AS a WHERE a.type IN (6001, 6002, 6005);" + sqlEnvelope + ";");
					createGeoObjects(r);
					
//					*/
						
					// close connections
					s.close();
					conn.close();
					zoomToFit();
					System.out.println("Loading data done!");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();
		
	}

	private void createGeoObjects(ResultSet _r) {
		try {
			while (_r.next()) {
				String id = _r.getString("id");
				int type = _r.getInt("type");
				PGgeometry geom = (PGgeometry) _r.getObject("geom");
				
				Vector<ObjectPart> objParts = new Vector<ObjectPart>();
				
				switch (geom.getGeoType()) {
				case Geometry.POLYGON: {
					String wkt = geom.toString();
					org.postgis.Polygon p = new org.postgis.Polygon(wkt);
					
					for (int r = 0; r < p.numRings(); r++) {
						
						LinearRing ring = p.getRing(0);
						Vector<Point> points = new Vector<Point>();
						for (int i = 0; i < ring.numPoints(); i++) {
							org.postgis.Point pPG = ring.getPoint(i);
							points.addElement(new Point((int)pPG.x, (int)pPG.y));
						}
						ObjectPart objPart = new AreaObject(new Line(points));
				
						if (r == 0) {
							// Ring 0 --> main polygon ... rest should be holes
							
							objParts.addElement(objPart); // container part
						} else {
							ObjectPart container = objParts.get(0); // get container part
							container.addObjectPart(objPart); // add to container part
						}
					}
					break;
				}
				case Geometry.MULTIPOLYGON: {
					String wkt = geom.toString();
					org.postgis.MultiPolygon m = new org.postgis.MultiPolygon(wkt);
					
					// go over all separate polygons
					for (int k = 0; k < m.getPolygons().length; k++) {
						org.postgis.Polygon p = m.getPolygon(k);
						
						for (int r = 0; r < p.numRings(); r++) {
							
							LinearRing ring = p.getRing(0);
							Vector<Point> points = new Vector<Point>();
							for (int i = 0; i < ring.numPoints(); i++) {
								org.postgis.Point pPG = ring.getPoint(i);
								points.addElement(new Point((int)pPG.x, (int)pPG.y));
							}
							ObjectPart objPart = new AreaObject(new Line(points));
					

							if (r == 0) {
								// Ring 0 --> main polygon ... rest should be holes
								
								objParts.addElement(objPart); // container part
							} else {
								ObjectPart container = objParts.get(0); // get container part
								container.addObjectPart(objPart); // add to container part
							}
						}
					}
					break;
				}
				case Geometry.POINT: {
					String wkt = geom.toString();
					org.postgis.Point pPG = new org.postgis.Point(wkt);
					
					ObjectPart objPart = new PointObject(new Point((int) pPG.x, (int)pPG.y));
					objParts.addElement(objPart);
					break;
				}
				case Geometry.LINESTRING: {
					String wkt = geom.toString();
					org.postgis.LineString lPG = new org.postgis.LineString(wkt);
					
					org.postgis.Point[] pointsPG = lPG.getPoints();
					Vector<Point> points = new Vector<Point>();
					for (int i = 0; i < pointsPG.length; i++) {
						points.add(new Point((int) pointsPG[i].x, (int) pointsPG[i].y));
					}
					ObjectPart objPart = new LineObject(new Line(points));
					objParts.addElement(objPart);
					break;
				}
				default:
					System.out.println("Unknown GeoType: " + geom.getGeoType());
					break;
				} // end switch GeoType
				
				
				GeoObject geoObj = new GeoObject(id, type, objParts);
				mOrigGeoObjects.addElement(geoObj);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Load data from DummyServer.
	 */
	private void loadFromDummyServer() {
		DummyGIS server = new DummyGIS();
		if (server.init()) {
	    	Vector<GeoObject> objects = server.extractData("select * from data where type in (233, 931, 932, 933, 1101)");
	    	
	    	// clear old ones
	    	mOrigGeoObjects.clear();
	    	
	    	// add to list
	    	for (GeoObject obj : objects) {
	    		mOrigGeoObjects.add(obj);
	    	}
	    	// add POIs
	    	loadPOIObjects();
		}
	}
	
	private void loadPOIObjects() {
		// create dummy POI
		for (int i = 0; i < 5; i++) {
			// take random geoObject for position
			int randIndex = (int)(Math.random() * mOrigGeoObjects.size()); 
			GeoObject randGeoObj = mOrigGeoObjects.get(randIndex);
			POIObject poiObj = new POIObject(randGeoObj.getId(), randGeoObj.getType(), randGeoObj.getObjectParts(), i);
			mOrigGeoObjects.add((GeoObject) poiObj);
			mOrigGeoObjects.remove(randGeoObj); // remove old one (redundant)
		}
		
	}

	public Vector<GeoObject> initSelection(Point _pt) {
		Point mapPoint = getMapPoint(_pt);
		Vector<GeoObject> objects = new Vector<>();
		for (GeoObject obj : mOrigGeoObjects) {
			if (obj.getBounds().contains(mapPoint)) {
				objects.add(obj);
		}
		}
		return objects;
	}
	
	public void zoomRect(Rectangle _mapBounds) {
		Rectangle win = new Rectangle(new Dimension(mDrawingPanelWidth, mDrawingPanelHeight));
		mMatrix = Matrix.zoomToFit(_mapBounds, win);
		drawAllGeoObjects();
		notifyObservers();
	}
	
	public Point getMapPoint(Point _pt) {
		return mMatrix.invers().multiply(_pt);
	}
	
	protected int calculateScale() {
		GeoDoublePoint vector = new GeoDoublePoint(0, 1.0);
		GeoDoublePoint vector_transformed = mMatrix.multiply(vector);
		double n = vector_transformed.length();
		
		double scale = 1 / n * (mDotPerInch / INCH_IN_CM);
		mMapScale = (int) scale;
		return (int) scale;
	}

	/**
	 * Setzt den Maﬂstab der Karte.
	 * @param scale der neue Maﬂstab
	 */
	public void setMapScale(int scale) {
		double ratioScale = mMapScale / (double) scale;
		zoom(ratioScale);
	}
	
	/**
	 * Switch server who loads data from database.
	 * @param _serverNr from 0 to n servers
	 */
	public void switchServer(int _serverNr) {
		mServerNr = _serverNr;
	}

	public void switchSticky(boolean sticky) {
		mSticky = sticky;
		if (sticky) {
			Point start = new Point(0, 0);
			Point end = new Point(mDrawingPanelWidth, mDrawingPanelHeight);
			start = getMapPoint(start);
			end = getMapPoint(end);
			mStickyBounds = new Rectangle(start);
			mStickyBounds.add(end);
		} 
		mOrigGeoObjects.clear();
		loadData();
	}

	public void storePicture() {
		File outputfile = new File("image.jpg");
		try {
			ImageIO.write(mBufImg, "jpg", outputfile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void addGeoObject(GeoObject geoObject) {
		if(geoObject instanceof POIObject)
			mPOIObjects.add(geoObject);
		else
			mOrigGeoObjects.addElement(geoObject);
		drawAllGeoObjects();
		notifyObservers();
	}
}
