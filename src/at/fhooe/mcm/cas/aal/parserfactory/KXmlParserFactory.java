package at.fhooe.mcm.cas.aal.parserfactory;

import at.fhooe.mcm.cas.aal.parser.IContextParser;
import at.fhooe.mcm.cas.aal.parser.KXmlContextParser;
import at.fhooe.mcm.cas.rule.parser.IRuleParser;

public class KXmlParserFactory implements IParserFactory {

	@Override
	public IContextParser createParser() {
		return new KXmlContextParser();
	}

	@Override
	public IRuleParser createRuleParser() {
		return null;
	}

}
