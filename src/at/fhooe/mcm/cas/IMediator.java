package at.fhooe.mcm.cas;


import at.fhooe.mcm.cas.contexttype.ContextElement;
import at.fhooe.mcm.cas.gis.geomodel.GeoObject;
import at.fhooe.mcm.cas.rule.container.RuleEvaluator;

public interface IMediator {
	public void register(IComponent icomp);
	public void unregister(IComponent icomp);
	public void notifyComponents(GeoObject geoObject, IComponent origin);
	public void notifyComponents(ContextElement contextElement, IComponent origin);
	public void notifyComponents(ContextSituation contextSituation, IComponent origin);
	public void notifyComponents(RuleEvaluator ruleEvaluator, IComponent origin);
	public void initComponents(String filename);
}
