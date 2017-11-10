package at.fhooe.mcm.cas.aal.parser;

import java.util.List;

import at.fhooe.mcm.cas.aal.ContextElement;

public interface IContextParser {
	List<ContextElement> parse(String context);
}
