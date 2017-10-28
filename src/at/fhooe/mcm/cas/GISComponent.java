package at.fhooe.mcm.cas;


import java.awt.Panel;

import at.fhooe.mcm.cas.gis.GISController;
import at.fhooe.mcm.cas.gis.GISModel;
import at.fhooe.mcm.cas.gis.GISView;
import at.fhooe.mcm.cas.gis.geomodel.GeoObject;


public class GISComponent extends IComponent implements CommunicationObserver {

	private Panel mPanel;


	public GISComponent(IMediator mediator, String name) {
		super(mediator, name);
			
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
