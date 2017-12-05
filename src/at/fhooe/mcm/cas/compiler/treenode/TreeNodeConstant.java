package at.fhooe.mcm.cas.compiler.treenode;

import at.fhooe.mcm.cas.contexttype.ContextElement;

public class TreeNodeConstant extends TreeNode {

	String mType;
	int mValue;
	
	public TreeNodeConstant(String text) {
		mType = text;
	}

	@Override
	public Object calculate() throws NodeError {
		return mValue;
	}

	@Override
	public void setVariableParameters(Object[] _contextElements) {
		mValue = (int)_contextElements[0];
	}

	@Override
	public void clear() {

	}

	@Override
	public String toString() {
		return null;
	}

}
