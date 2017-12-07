package at.fhooe.mcm.cas.compiler.treenode;

import at.fhooe.mcm.cas.contexttype.ContextElement;

public class TreeNodeContextVar extends TreeNode {

	int mContextVar;
	
	public TreeNodeContextVar(String text) {
		mContextVar = Integer.parseInt(text);
	}
	
	@Override
	public Object calculate() throws NodeError {
		return mContextVar;
	}

	@Override
	public void setVariableParameters(Object[] _contextElements) {

	}

	@Override
	public void clear() {

	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Class getContextElements() {
		return TreeNodeContextVar.class;
	}
}
