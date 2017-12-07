package at.fhooe.mcm.cas.aal.parserfactory;

import at.fhooe.mcm.cas.aal.parser.GsonParser;
import at.fhooe.mcm.cas.aal.parser.IContextParser;
import at.fhooe.mcm.cas.rule.parser.IRuleParser;

public class GsonParserFactory implements IParserFactory {

	@Override
	public IContextParser createParser() {
		return new GsonParser();
	}

	@Override
	public IRuleParser createRuleParser() {
		// TODO Auto-generated method stub
		return null;
	}

}
