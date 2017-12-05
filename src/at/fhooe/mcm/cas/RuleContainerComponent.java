package at.fhooe.mcm.cas;

import java.awt.Panel;

import at.fhooe.mcm.cas.compiler.rules.RuleContainer;
import at.fhooe.mcm.cas.contexttype.ContextElement;
import at.fhooe.mcm.cas.gis.geomodel.GeoObject;

public class RuleContainerComponent extends IComponent {
	
	private RuleContainer mContainer;

	public RuleContainerComponent(IMediator mediator, String name) {
		super(mediator, name);
		mContainer = new RuleContainer("TEMPERATURE < 4 & TEMPERATURE > 0", null);
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
		mContainer.valid(contextSituation);

	}

	@Override
	public Panel getView() {
		return null;
	}

}
