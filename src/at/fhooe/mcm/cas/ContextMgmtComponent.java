package at.fhooe.mcm.cas;

import java.awt.Panel;

import at.fhooe.mcm.cas.contexttype.ContextElement;
import at.fhooe.mcm.cas.gis.geomodel.GeoObject;

public class ContextMgmtComponent extends IComponent {
	
	public ContextSituation mSituation;

	public ContextMgmtComponent(IMediator mediator, String name) {
		super(mediator, name);
		mSituation = new ContextSituation();
	}

	@Override
	public void onGeoObjectUpdated(GeoObject geoObject) {
		
	}

	@Override
	public void onContextElementUpdated(ContextElement contextElement) {
		boolean found = false;
		for (ContextElement ce : mSituation.mContextElements) {
			if(ce.getType().equals(contextElement.getType())) {
				ce.setContextMetaData(contextElement.getContextMetaData());
				ce.setId(contextElement.getId());
				ce.setKey(contextElement.getKey());
				found = true;
				break;
			}
		}
		if(!found)
			mSituation.mContextElements.add(contextElement);
		mMediator.notifyComponents(mSituation, this);
	}

	@Override
	public void onContextSituationUpdated(ContextSituation contextSituation) {
		
	}

	@Override
	public Panel getView() {
		return null;
	}

	@Override
	public void onGPSPositionUpdated(GPSPosition gpsPosition) {
		// TODO Auto-generated method stub
		
	}

}
