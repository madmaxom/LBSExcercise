package at.fhooe.mcm.cas;


import java.awt.Panel;

import at.fhooe.mcm.cas.gis.GISController;
import at.fhooe.mcm.cas.gis.GISModel;
import at.fhooe.mcm.cas.gis.GISView;
import at.fhooe.mcm.cas.gis.geomodel.GeoObject;


public class GISComponent implements IComponent, CommunicationObserver {

	private Panel mPanel;
	private String mName;
	
	
	
	public GISComponent() {
		mName = "Map";
	
		GISModel m = new GISModel();
		GISController c = new GISController(m);
		GISView v = new GISView(c);
		mPanel = v.getPanel();
		c.addView(v);
		m.addObserver(v);
	}
	
	@Override
	public Panel getView() {
		return mPanel;
	}

	@Override
	public String getName() {
		return mName;
	}

	@Override
	public void onGeoObjectUpdated(GeoObject geoObject) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onContextElementUpdated(ContextElement contextElement) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onContextSituationUpdated(ContextSituation contextSituation) {
		// TODO Auto-generated method stub
	}

	

}
