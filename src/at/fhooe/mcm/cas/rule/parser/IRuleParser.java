package at.fhooe.mcm.cas.rule.parser;

import at.fhooe.mcm.cas.rule.container.RuleEvaluator;


public interface IRuleParser {
	RuleEvaluator parse(String context);
}
