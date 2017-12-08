package at.fhooe.mcm.cas;

import java.awt.Panel;
import java.awt.Point;
import java.util.Vector;

import at.fhooe.mcm.cas.contexttype.ContextElement;
import at.fhooe.mcm.cas.gis.POIObject;
import at.fhooe.mcm.cas.gis.geomodel.GeoObject;
import at.fhooe.mcm.cas.gis.geomodel.ObjectPart;
import at.fhooe.mcm.cas.gis.geomodel.PointObject;
import at.fhooe.mcm.cas.poi.POIView;
import at.fhooe.mcm.cas.rule.container.RuleEvaluator;

public class POIComponent extends IComponent {

	private Panel mPanel;
	
	public POIComponent(IMediator mediator, String name) {
		super(mediator, name);
		
		POIView v = new POIView();
		mPanel = v.getPanel();
		
		Vector<ObjectPart> parts = new Vector<ObjectPart>();
		boolean austria = true;
//		austria = false;
		GeoObject[] POIs = new GeoObject[2];
		if(austria) {
			parts.addElement(new PointObject(new Point(1756760, 6026187)));
			POIs[0] = new POIObject("48003", 10005, parts, 0);
			parts = new Vector<ObjectPart>();
			parts.addElement(new PointObject(new Point(1706760, 6096187)));
			POIs[1] = new POIObject("48004", 10005, parts, 0);
		} else {
			parts.addElement(new PointObject(new Point(-1866059, 3297062)));
			POIs[0] = new POIObject("48003", 10005, parts, 0);
			parts = new Vector<ObjectPart>();
			parts.addElement(new PointObject(new Point(-1816059, 3257062)));
			POIs[1] = new POIObject("48004", 10005, parts, 0);
		}
		
		Mediator m = (Mediator) super.mMediator;
		IComponent icomp = this;
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(5000);
					m.notifyComponents(POIs[0], icomp);
					m.notifyComponents(POIs[1], icomp);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
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

	@Override
	public void onRuleEvaluatorUpdated(RuleEvaluator ruleEvaluator) {
		// TODO Auto-generated method stub
		
	}

}
