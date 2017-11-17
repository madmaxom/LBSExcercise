package at.fhooe.mcm.cas;

import java.awt.Panel;
import java.awt.Point;
import java.util.Vector;

import at.fhooe.mcm.cas.contexttype.ContextElement;
import at.fhooe.mcm.cas.gis.geomodel.GeoObject;
import at.fhooe.mcm.cas.gis.geomodel.Line;
import at.fhooe.mcm.cas.gis.geomodel.ObjectPart;
import at.fhooe.mcm.cas.gis.geomodel.PointObject;
import at.fhooe.mcm.cas.poi.POIView;

public class POIComponent extends IComponent {

	private Panel mPanel;
	private GeoObject[] POIs = new GeoObject[2];
	
	public POIComponent(IMediator mediator, String name) {
		super(mediator, name);
		
		POIView v = new POIView();
		mPanel = v.getPanel();
		
		Vector<ObjectPart> parts = new Vector<ObjectPart>();
		parts.add(new PointObject(new Point(1790733,5980255)));
		POIs[0] = new GeoObject("50067",5006,parts);
		parts = new Vector<ObjectPart>();
		parts.add(new PointObject(new Point(1790707,5980240)));
		POIs[1] = new GeoObject("50067",5006,parts);
		Mediator m = (Mediator) super.mMediator;
		IComponent icomp = this;
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(10000);
					m.notifyComponents(POIs[0], icomp);
					m.notifyComponents(POIs[1], icomp);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		});
	}
	
	@Override
	public Panel getView() {
		return mPanel;
	}

	@Override
	public void onGeoObjectUpdated(GeoObject geoObject) {
		
	}

	@Override
	public void onGPSPositionUpdated(GPSPosition contextElement) {
		
	}

	@Override
	public void onContextSituationUpdated(ContextSituation contextSituation) {
		
	}

	@Override
	public void onContextElementUpdated(ContextElement contextElement) {
		// TODO Auto-generated method stub
		
	}

}
