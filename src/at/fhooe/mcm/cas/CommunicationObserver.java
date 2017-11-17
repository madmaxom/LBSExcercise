package at.fhooe.mcm.cas;

import at.fhooe.mcm.cas.contexttype.ContextElement;
import at.fhooe.mcm.cas.gis.geomodel.GeoObject;

public interface CommunicationObserver {
	public void onGeoObjectUpdated(GeoObject geoObject);
	public void onContextElementUpdated(ContextElement contextElement);
	public void onGPSPositionUpdated(GPSPosition gpsPosition);
	public void onContextSituationUpdated(ContextSituation contextSituation);
}
