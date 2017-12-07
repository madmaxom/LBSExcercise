package at.fhooe.mcm.cas.aal.parserfactory;

import at.fhooe.mcm.cas.aal.parser.IContextParser;
import at.fhooe.mcm.cas.rule.parser.IRuleParser;

public interface IParserFactory {
	IContextParser createParser();
	IRuleParser createRuleParser();
}
