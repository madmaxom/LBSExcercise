package at.fhooe.mcm.cas;

import java.awt.Panel;
import java.util.ArrayList;
import java.util.List;

import at.fhooe.mcm.cas.contexttype.ContextElement;
import at.fhooe.mcm.cas.contexttype.ContextTemperature;
import at.fhooe.mcm.cas.ctx.CtxController;
import at.fhooe.mcm.cas.ctx.CtxModel;
import at.fhooe.mcm.cas.ctx.CtxView;
import at.fhooe.mcm.cas.gis.geomodel.GeoObject;
import at.fhooe.mcm.cas.rule.container.RuleEvaluator;

public class ContextMgmtComponent extends IComponent {
	
	public Panel mPanel;
	public ContextSituation mSituation;
	private CtxModel mCtxModel;
	private ContextMgmtComponent mSelf = this;

	public ContextMgmtComponent(IMediator mediator, String name) {
		super(mediator, name != null && "".equals(name) ? "CTX" : name);
		mSituation = new ContextSituation();
		
		mCtxModel = new CtxModel();
		CtxController c = new CtxController(mCtxModel);
		CtxView v = new CtxView(c);
		mPanel = v.getPanel();
		c.addView(v);
		mCtxModel.addCtxObserver(v);
		new Thread(new Runnable() {
			@Override
			public void run() {
				List<ContextElement> ces = new ArrayList<>();
				while(true) {
					try {
						Thread.sleep(mCtxModel.getUpdateFrequency() * 1000);
						ces = mCtxModel.getContext();
						for(ContextElement ce : ces) {
							mSituation.add(ce);
							if(ce instanceof ContextTemperature)
								mSituation.add(ce);
						}
						mMediator.notifyComponents(mSituation, mSelf);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}
	
	public void setDrawingContext(String s) {}

	@Override
	public void onGeoObjectUpdated(GeoObject geoObject) {
		
	}

	@Override
	public void onContextElementUpdated(ContextElement contextElement) {
		mSituation.add(contextElement);
		mCtxModel.updateContext(contextElement);
	}

	@Override
	public void onContextSituationUpdated(ContextSituation contextSituation) {
		
	}

	@Override
	public Panel getView() {
		return mPanel;
	}

	@Override
	public void onRuleEvaluatorUpdated(RuleEvaluator ruleEvaluator) {
		// TODO Auto-generated method stub
		
	}

}
