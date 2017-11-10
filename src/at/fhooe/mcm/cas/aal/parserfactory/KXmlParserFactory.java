package at.fhooe.mcm.cas.aal.parserfactory;

import at.fhooe.mcm.cas.aal.parser.DOMContextParser;
import at.fhooe.mcm.cas.aal.parser.IContextParser;

public class KXmlParserFactory implements IParserFactory {

	@Override
	public IContextParser createParser() {
		return new DOMContextParser();
	}

}
