package at.fhooe.mcm.cas.compiler.treenode;

import java.util.ArrayList;
import java.util.List;

import at.fhooe.mcm.cas.contexttype.ContextElement;
import at.fhooe.mcm.cas.contexttype.ContextElementType;
import at.fhooe.mcm.cas.contexttype.ContextFuel;
import at.fhooe.mcm.cas.contexttype.ContextTemperature;


public class TreeNodeConstant extends TreeNode {

	String mType;
	int mValue = Integer.MAX_VALUE;
	
	public TreeNodeConstant(String text) {
		mType = text;
	}

	@Override
	public Object calculate() throws NodeError {
		if (mValue == Integer.MAX_VALUE) {
			throw new NodeError("No value for evaluation.");
		} else {
			return mValue;
		}
	}

	@Override
	public void setVariableParameters(List<ContextElement> _contextElements) {
		// leave node
		if (_contextElements != null && _contextElements.size() == 1) {
			ContextElement c = _contextElements.get(0);
			if (c instanceof ContextTemperature) {
				mValue = ((ContextTemperature) c).getTemperature();
			} else if (c instanceof ContextFuel) {
				mValue = ((ContextFuel) c).getFuelStatus();
			}
		} else {
			System.out.println(this.getClass().getSimpleName() + ": no or multiple contextElement received in leave node");
		}
	}

	@Override
	public void clear() {

	}

	@Override
	public String toString() {
		return null;
	}

	@Override
	public List<String> getContextElements() {
		List<String> l = new ArrayList<String>();
		l.add(mType);
		return l;
	}

}
