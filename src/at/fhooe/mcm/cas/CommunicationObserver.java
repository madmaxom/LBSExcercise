package at.fhooe.mcm.cas;

import at.fhooe.mcm.cas.gis.geomodel.GeoObject;

public interface CommunicationObserver {
	public void onGeoObjectUpdated(GeoObject geoObject);
	public void onContextElementUpdated(ContextElement contextElement);
	public void onContextSituationUpdated(ContextSituation contextSituation);
}
