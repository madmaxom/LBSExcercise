package at.fhooe.mcm.cas.compiler.treenode;

import java.util.Date;

public class TreeNodeTime extends TreeNode {

	int mValue;
	
	public TreeNodeTime() {
		
	}

	@Override
	public Object calculate() throws NodeError {
		return new Date();
	}

	@Override
	public void setVariableParameters(Object[] _contextElements) {
		mValue = (int)_contextElements[0];
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

	@Override
	public Class getContextElements() {
		return TreeNodeTime.class;
	}
}
