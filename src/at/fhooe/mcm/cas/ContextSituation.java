package at.fhooe.mcm.cas;

import java.util.ArrayList;
import java.util.List;

import at.fhooe.mcm.cas.contexttype.ContextElement;

public class ContextSituation {
	public List<ContextElement> mContextElements;
	
	public ContextSituation() {
		mContextElements = new ArrayList<>();
	}
	public void add(ContextElement ce) {
		boolean found = false;
		for (ContextElement temp : mContextElements) {
			if(temp.getType().equals(ce.getType())) {
				temp.setContextMetaData(ce.getContextMetaData());
				temp.setId(ce.getId());
				temp.setKey(ce.getKey());
				found = true;
				break;
			}
		}
		if(!found)
			mContextElements.add(ce);
	}
}