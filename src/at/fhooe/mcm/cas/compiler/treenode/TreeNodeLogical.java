package at.fhooe.mcm.cas.compiler.treenode;

public class TreeNodeLogical extends TreeNode {
	
	private String mLogicalOperator;
	
	public TreeNodeLogical(String text) {
		mLogicalOperator = text;
	}

	@Override
	public Object calculate() throws NodeError {
		if(mLogicalOperator.equals("&")) {
			return (boolean)mChilds[0].calculate() && (boolean)mChilds[1].calculate();
		} else if(mLogicalOperator.equals("|")) {
			return (boolean)mChilds[0].calculate() || (boolean)mChilds[1].calculate();
		}
		return false;
	}

	@Override
	public void setVariableParameters(Object[] _contextElements) {
		if(mChilds != null) {
			mChilds[0].setVariableParameters(_contextElements);
			mChilds[1].setVariableParameters(_contextElements);
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
	
	@Override
	public Class getContextElements() {
		return TreeNodeLogical.class;
	}
}
