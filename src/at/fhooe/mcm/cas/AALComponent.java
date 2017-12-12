package at.fhooe.mcm.cas;

import java.awt.Panel;

import at.fhooe.mcm.cas.aal.AALController;
import at.fhooe.mcm.cas.aal.AALModel;
import at.fhooe.mcm.cas.aal.AALModelObserver;
import at.fhooe.mcm.cas.aal.AALView;
import at.fhooe.mcm.cas.aal.parser.ParserMode;
import at.fhooe.mcm.cas.contexttype.ContextElement;
import at.fhooe.mcm.cas.gis.geomodel.GeoObject;
import at.fhooe.mcm.cas.rule.container.RuleEvaluator;

public class AALComponent extends IComponent implements AALModelObserver {

	private Panel mPanel;
	
	
	public AALComponent(IMediator mediator, String name) {
		this(mediator, name, ParserMode.DOM);
	}
	
	public AALComponent(IMediator mediator, String name,  ParserMode mode) {
		super(mediator, name != null && "".equals(name) ? "AAL" : name);
		
		AALModel m = new AALModel();
		m.addObserver(this);
		m.setParserMode(mode);
		AALController c = new AALController(m);
		AALView v = new AALView(c);
		mPanel = v.getPanel();

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
	public Panel getView() {
		return mPanel;
	}

	@Override
	public void onContextElementUpdated(ContextElement contextElement) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(ContextElement contextElement) {
		// notify mediator
		mMediator.notifyComponents(contextElement, this);
	}

	@Override
	public void onRuleEvaluatorUpdated(RuleEvaluator ruleEvaluator) {
		// TODO Auto-generated method stub
		
	}

}
