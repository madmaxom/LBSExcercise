package at.fhooe.mcm.cas.rule.container;

import java.util.ArrayList;
import java.util.List;

public class RuleEvaluator {
	private List<RuleContainer> mRules;
	public RuleEvaluator() {
		mRules = new ArrayList<RuleContainer>();
	}
	
	public void setRules(List<RuleContainer> rules) {
		mRules = rules;
	}
	
	public List<RuleContainer> getRules() {
		return mRules;
	}
}
