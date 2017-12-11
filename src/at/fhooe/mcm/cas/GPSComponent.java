package at.fhooe.mcm.cas;

import java.awt.Panel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.postgis.PGgeometry;
import org.postgresql.PGConnection;
import org.postgresql.util.PGobject;

import at.fhooe.mcm.cas.contexttype.ContextElement;
import at.fhooe.mcm.cas.gis.geomodel.GeoObject;
import at.fhooe.mcm.cas.gps.GPSReceiverController;
import at.fhooe.mcm.cas.gps.GPSReceiverSim;
import at.fhooe.mcm.cas.gps.GPSReceiverView;
import at.fhooe.mcm.cas.gps.NMEAInfo;
import at.fhooe.mcm.cas.gps.NMEAParser;
import at.fhooe.mcm.cas.gps.PositionUpdateListener;
import at.fhooe.mcm.cas.rule.container.RuleEvaluator;

public class GPSComponent extends IComponent implements CommunicationObserver, PositionUpdateListener {

	private static final String FILENAME = "files/gpslogs/GPS-Log-I.log";
	private static final int SLEEP = 2000; // two seconds
	private static final String FILTER = "GGA";
	
	private Panel mPanel;
	
	public GPSComponent(IMediator mediator, String name) {
		super(mediator, name != null && "".equals(name) ? "GPS" : name);

		GPSReceiverController c = new GPSReceiverController();
		GPSReceiverView v = new GPSReceiverView(c);
		
		mPanel = v.getPanel();
		
		try {
			// initialize receiver
			GPSReceiverSim sim = new GPSReceiverSim(FILENAME, SLEEP, FILTER);
			NMEAParser parser = new NMEAParser(sim);
			
			parser.addObserver((PositionUpdateListener) v);
			parser.addObserver((PositionUpdateListener) this);
			
			// start parsing
			System.out.println("Parsing GPS logs: " + FILENAME + ", every " + SLEEP + " ms, Filter " + FILTER);
			new Thread(parser).start();
			
		} catch (Exception _e) {
			_e.printStackTrace();
		}
	}
	
	
	@Override
	public Panel getView() {
		return mPanel;
	}


	@Override
	public void onGeoObjectUpdated(GeoObject geoObject) {
		// TODO Auto-generated method stub
		
	}




	@Override
	public void onContextSituationUpdated(ContextSituation contextSituation) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void updatePosition(NMEAInfo _info) {
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

			StringBuffer query = new StringBuffer();
			query.append("SELECT ST_Transform(ST_SetSRID(ST_MakePoint(");
			query.append(_info.getLat());
			query.append(", ");
			query.append(_info.getLng());
			query.append("), 4326), 3857);");
			
			ResultSet r = s.executeQuery(query.toString());
			r.next();
			PGgeometry geom = (PGgeometry) r.getObject("st_transform");
			String wkt = geom.toString();
			org.postgis.Point pt = new org.postgis.Point(wkt);
			
			// close connections
			s.close();
			conn.close();
			if (super.mMediator != null)
				super.mMediator.notifyComponents(new GPSPosition(_info.getLat(), _info.getLng()), this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	@Override
	public void onContextElementUpdated(ContextElement contextElement) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onGPSPositionUpdated(GPSPosition gpsPosition) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onRuleEvaluatorUpdated(RuleEvaluator ruleEvaluator) {
		// TODO Auto-generated method stub
		
	}

}
