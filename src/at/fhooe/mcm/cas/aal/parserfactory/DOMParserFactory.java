package at.fhooe.mcm.cas.aal.parserfactory;

import at.fhooe.mcm.cas.aal.parser.DOMContextParser;
import at.fhooe.mcm.cas.aal.parser.IContextParser;
import at.fhooe.mcm.cas.rule.parser.DOMRuleParser;
import at.fhooe.mcm.cas.rule.parser.IRuleParser;

public class DOMParserFactory implements IParserFactory {

	@Override
	public IContextParser createParser() {
		return new DOMContextParser();
	}

	@Override
	public IRuleParser createRuleParser() {
		return new DOMRuleParser();
	}
}
