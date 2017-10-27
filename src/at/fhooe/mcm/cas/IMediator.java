package at.fhooe.mcm.cas;

import java.awt.Panel;

public interface IMediator {
	public void register(IComponent icomp);
	public void unregister(IComponent icomp);
	
	public void sendGeoObject(GeoObject geoObject);
	public void sendContextElement(ContextElement contextElement);
	public void sendContextSituation(ContextSituation contextSituation);
}
