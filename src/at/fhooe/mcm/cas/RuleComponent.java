package at.fhooe.mcm.cas;

import java.awt.Panel;


import at.fhooe.mcm.cas.aal.parser.ParserMode;
import at.fhooe.mcm.cas.contexttype.ContextElement;
import at.fhooe.mcm.cas.gis.geomodel.GeoObject;
import at.fhooe.mcm.cas.rule.RuleController;
import at.fhooe.mcm.cas.rule.RuleModel;
import at.fhooe.mcm.cas.rule.RuleModelObserver;
import at.fhooe.mcm.cas.rule.RuleView;
import at.fhooe.mcm.cas.rule.container.RuleEvaluator;

public class RuleComponent extends IComponent implements RuleModelObserver {
	
	private Panel mPanel;
	

	public RuleComponent(IMediator mediator, String name) {
		super(mediator, name);
		
		RuleModel m = new RuleModel();
		m.addObserver(this);
		m.setParserMode(ParserMode.DOM);
		RuleController c = new RuleController(m);
		RuleView v = new RuleView(c);
		mPanel = v.getPanel();
		
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
	public void onGPSPositionUpdated(GPSPosition gpsPosition) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onContextSituationUpdated(ContextSituation contextSituation) {
		// mContainer.valid(contextSituation);
	}

	@Override
	public Panel getView() {
		return mPanel;
	}

	@Override
	public void update(RuleEvaluator ruleEvaluator) {
		mMediator.notifyComponents(ruleEvaluator , this);
	}

	@Override
	public void onRuleEvaluatorUpdated(RuleEvaluator ruleEvaluator) {
		// TODO Auto-generated method stub
		
	}
}
