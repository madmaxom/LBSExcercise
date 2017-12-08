package at.fhooe.mcm.cas.compiler.treenode;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import at.fhooe.mcm.cas.contexttype.ContextElement;
import at.fhooe.mcm.cas.contexttype.ContextElementType;

public class TreeNodeTime extends TreeNode {

	int mValue;
	
	public TreeNodeTime() {
		
	}

	@Override
	public Object calculate() throws NodeError {
		return new Date();
	}

	@Override
	public void setVariableParameters(List<ContextElement> _contextElements) {
		// leave node
		if (_contextElements != null && _contextElements.size() == 1) {
			mValue = new Date().getHours();
		} else {
			System.out.println(this.getClass().getSimpleName() + ": no or multiple contextElement received in leave node");
		}
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub

	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<String> getContextElements() {
		List<String> l = new ArrayList<String>();
		l.add(ContextElementType.CONTEXT_ELEMENT_TIME_OF_THE_DAY);
		return l;
	}
}
