package at.fhooe.mcm.cas;


import java.awt.Panel;

import at.fhooe.mcm.cas.gis.GISController;
import at.fhooe.mcm.cas.gis.GISModel;
import at.fhooe.mcm.cas.gis.GISView;
import at.fhooe.mcm.cas.gis.geomodel.GeoObject;


public class GISComponent extends IComponent {

	private Panel mPanel;
	private GISModel mGISModel;

	public GISComponent(IMediator mediator, String name) {
		super(mediator, name);
			
		mGISModel = new GISModel();
		GISController c = new GISController(mGISModel);
		GISView v = new GISView(c);
		mPanel = v.getPanel();
		c.addView(v);
		mGISModel.addObserver(v);
	}
	
	@Override
	public Panel getView() {
		return mPanel;
	}
	
	public void updateComponents(GeoObject geoObject) {
		super.mMediator.notifyComponents(geoObject, this);
	}
	
	public void updateComponents(ContextElement contextElement) {
		super.mMediator.notifyComponents(contextElement, this);
	}
	
	public void updateComponents(ContextSituation contextSituation) {
		super.mMediator.notifyComponents(contextSituation, this);
	}
	

	@Override
	public void onGeoObjectUpdated(GeoObject geoObject) {
		
	}

	@Override
	public void onContextElementUpdated(ContextElement contextElement) {
		//location of gps component is received
	}

	@Override
	public void onContextSituationUpdated(ContextSituation contextSituation) {
		
	}

}
