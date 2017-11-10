package at.fhooe.mcm.cas.aal;

import java.util.List;

public interface IContextParser {
	List<ContextElement> parse(String context);
}
