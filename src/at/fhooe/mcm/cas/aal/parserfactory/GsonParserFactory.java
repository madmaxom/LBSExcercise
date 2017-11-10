package at.fhooe.mcm.cas.aal.parserfactory;

import at.fhooe.mcm.cas.aal.parser.GsonParser;
import at.fhooe.mcm.cas.aal.parser.IContextParser;

public class GsonParserFactory implements IParserFactory {

	@Override
	public IContextParser createParser() {
		return new GsonParser();
	}

}
