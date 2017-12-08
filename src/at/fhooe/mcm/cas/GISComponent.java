package at.fhooe.mcm.cas;


import java.util.List;
import java.awt.Panel;

import at.fhooe.mcm.cas.contexttype.ContextElement;
import at.fhooe.mcm.cas.gis.GISController;
import at.fhooe.mcm.cas.gis.GISModel;
import at.fhooe.mcm.cas.gis.GISView;
import at.fhooe.mcm.cas.gis.geomodel.GeoObject;
import at.fhooe.mcm.cas.rule.container.RuleContainer;
import at.fhooe.mcm.cas.rule.container.RuleEvaluator;


public class GISComponent extends IComponent {

	private Panel mPanel;
	private GISModel mGISModel;
	private ContextSituation mContextSituation;
	private RuleEvaluator mRuleEvaluator;

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
	
	public void updateComponents(GPSPosition contextElement) {
		super.mMediator.notifyComponents(contextElement, this);
	}
	
	public void updateComponents(ContextSituation contextSituation) {
		super.mMediator.notifyComponents(contextSituation, this);
	}
	

	@Override
	public void onGeoObjectUpdated(GeoObject geoObject) {
		mGISModel.addGeoObject(geoObject);
	}

	@Override
	public void onGPSPositionUpdated(GPSPosition gpsPosition) {
		//location of gps component is received
	}

	@Override
	public void onContextSituationUpdated(ContextSituation contextSituation) {
		mContextSituation = contextSituation;
		checkRules();
	}

	@Override
	public void onContextElementUpdated(ContextElement contextElement) {
		
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
		List<RuleContainer> rules = mRuleEvaluator.getRules();
		for (RuleContainer r : rules) {
			boolean valid = r.valid(mContextSituation);
			if (valid) {
				r.execute();
			}
		}
		
	}
	


}
