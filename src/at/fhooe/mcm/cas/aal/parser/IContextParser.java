package at.fhooe.mcm.cas.aal.parser;

import java.util.List;

import at.fhooe.mcm.cas.aal.ContextElementTest;

public interface IContextParser {
	List<ContextElementTest> parse(String context);
}
