package at.fhooe.mcm.cas;

import java.awt.Panel;

import at.fhooe.mcm.cas.gis.geomodel.GeoObject;

public interface IMediator {
	public void register(IComponent icomp);
	public void unregister(IComponent icomp);
	public void notifyComponents(GeoObject geoObject, IComponent origin);
	public void notifyComponents(ContextElement contextElement, GISComponent origin);
	public void notifyComponents(ContextSituation contextSituation, GISComponent origin);

}
