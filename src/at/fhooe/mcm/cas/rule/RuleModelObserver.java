package at.fhooe.mcm.cas.rule;

import at.fhooe.mcm.cas.rule.container.RuleEvaluator;

public interface RuleModelObserver {
	void update(RuleEvaluator ruleEvaluator);
}
