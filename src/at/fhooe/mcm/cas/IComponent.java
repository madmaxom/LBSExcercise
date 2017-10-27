package at.fhooe.mcm.cas;

import java.awt.Panel;

public interface IComponent {
	public Panel getView();
	public String getName();
	
	public void onGeoObjectUpdated(GeoObject geoObject);
	public void onContextElementUpdated(ContextElement contextElement);
	public void onContextSituationUpdated(ContextSituation contextSituation);
}
