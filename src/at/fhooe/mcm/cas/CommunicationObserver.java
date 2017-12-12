package at.fhooe.mcm.cas;

import at.fhooe.mcm.cas.contexttype.ContextElement;
import at.fhooe.mcm.cas.gis.geomodel.GeoObject;
import at.fhooe.mcm.cas.rule.container.RuleEvaluator;

public interface CommunicationObserver {
	public void onGeoObjectUpdated(GeoObject geoObject);
	public void onContextElementUpdated(ContextElement contextElement);
	public void onContextSituationUpdated(ContextSituation contextSituation);
	public void onRuleEvaluatorUpdated(RuleEvaluator ruleEvaluator);
}
