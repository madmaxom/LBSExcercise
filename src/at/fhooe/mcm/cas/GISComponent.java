package at.fhooe.mcm.cas;


import java.awt.Panel;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import at.fhooe.mcm.cas.contexttype.ContextElement;
import at.fhooe.mcm.cas.contexttype.ContextPosition;
import at.fhooe.mcm.cas.gis.GISController;
import at.fhooe.mcm.cas.gis.GISModel;
import at.fhooe.mcm.cas.gis.GISView;
import at.fhooe.mcm.cas.gis.POIObject;
import at.fhooe.mcm.cas.gis.geomodel.GeoObject;
import at.fhooe.mcm.cas.gis.geomodel.ObjectPart;
import at.fhooe.mcm.cas.gis.geomodel.PointObject;
import at.fhooe.mcm.cas.rule.container.RuleContainer;
import at.fhooe.mcm.cas.rule.container.RuleEvaluator;
import at.fhooe.mcm.cas.warningtype.IWarningType;


public class GISComponent extends IComponent {

	private Panel mPanel;
	private GISModel mGISModel;
	private ContextSituation mContextSituation;
	private RuleEvaluator mRuleEvaluator;
	
	private GISView mView;

	public GISComponent(IMediator mediator, String name) {
		super(mediator, name != null && "".equals(name) ? "GIS" : name);
			
		mGISModel = new GISModel();
		GISController c = new GISController(mGISModel);
		mView = new GISView(c);
		mPanel = mView.getPanel();
		c.addView(mView);
		mGISModel.addObserver(mView);
	}
	

	public void setDrawingContext(String s) {}
	
	@Override
	public Panel getView() {
		return mPanel;
	}
	
	public void updateComponents(GeoObject geoObject) {
		super.mMediator.notifyComponents(geoObject, this);
	}
	
	public void updateComponents(ContextSituation contextSituation) {
		super.mMediator.notifyComponents(contextSituation, this);
	}
	

	@Override
	public void onGeoObjectUpdated(GeoObject geoObject) {
		mGISModel.addGeoObject(geoObject);
	}

	@Override
	public void onContextSituationUpdated(ContextSituation contextSituation) {
		mContextSituation = contextSituation;
		checkRules();
	}

	@Override
	public void onContextElementUpdated(ContextElement contextElement) {
		if(contextElement instanceof ContextPosition) {
			ContextPosition cp = (ContextPosition) contextElement;
			CoordinateConversion cc = new CoordinateConversion();
//			String coords = cc.latLon2UTM(48.2211599, 14.3089029);
			String coords = cc.latLon2UTM(cp.getxValue(), cp.getyValue());
			String[] coordParts = coords.split(" ");
			Point p = new Point();
			p.setLocation(Integer.parseInt(coordParts[2]), Integer.parseInt(coordParts[3]));// 1756760, 6026187
			Vector<ObjectPart> parts = new Vector<ObjectPart>();
			GeoObject[] POIs = new GeoObject[2];
			parts.addElement(new PointObject(new Point((int)p.getX(), (int)p.getY())));
			POIs[0] = new POIObject("", 0, parts, 6);
			mGISModel.addGeoObject(POIs[0]);
		}
	}

	@Override
	public void onRuleEvaluatorUpdated(RuleEvaluator ruleEvaluator) {
		mRuleEvaluator = ruleEvaluator;
		checkRules();		
	}

	private void checkRules() {
		if (mRuleEvaluator == null || mContextSituation == null) {
			System.out.println("Can not evaluate rules, RuleEvaluator or ContextSituation is missing.");
			return;
		}
		
		System.out.println("Remove warnings");
		mView.removeWarnings();
		
		List<RuleContainer> rules = mRuleEvaluator.getRules();
		for (RuleContainer r : rules) {
			if (!r.getAction().getClazz().equals(this.getClass().getName())) {
				// action not for that class, skip evaluation
				continue;
			}
			boolean valid = r.valid(mContextSituation);
			if (valid) {
				r.execute(this);
			}
		}
		
	}
	
	public void setWarning(IWarningType warningType) {
		System.out.println("Set warning with type: " + warningType.getClass().getSimpleName());
		
		List<IWarningType> warningTypes = new ArrayList<IWarningType>();
		warningTypes.add(warningType);
		mView.setWarning(warningTypes);
	}
	
	public void setWarning(IWarningType warningType1, IWarningType warningType2) {
		System.out.println("Set warning with type: " + warningType1.getClass().getSimpleName() + " and " + warningType2.getClass().getSimpleName());
		
		List<IWarningType> warningTypes = new ArrayList<IWarningType>();
		warningTypes.add(warningType1);
		warningTypes.add(warningType2);
		mView.setWarning(warningTypes);
	}
	


}
