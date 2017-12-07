package at.fhooe.mcm.cas.compiler.treenode;

import java.util.Date;

import at.fhooe.mcm.cas.contexttype.ContextElement;

public class TreeNodeComparator extends TreeNode {
	
	String mComparator;
	
	public TreeNodeComparator(String text) {
		mComparator = text;
	}

	@Override
	public Object calculate() throws NodeError {
		int value1 = 0;
		int value2 = 0;
		if(mChilds[0].getContextElements().equals(TreeNodeTime.class)) {
			value1 = new Date().getHours();
			value2 = (int)mChilds[1].calculate();
		} else if(mChilds[0].getContextElements().equals(TreeNodeConstant.class)) {
			value1 = (int)mChilds[0].calculate();
			value2 = (int)mChilds[1].calculate();
		}
		switch(mComparator) {
		case "<":
			return value1 < value2;
		case ">":
			return value1 > value2;
		case "=":
			return value1 == value2;
		default:
			return null;
		}
//		switch(mComparator) {
//		case "<":
//			return (int)mChilds[0].calculate() < (int)mChilds[1].calculate();
//		case ">":
//			return (int)mChilds[0].calculate() > (int)mChilds[1].calculate();
//		case "=":
//			return (int)mChilds[0].calculate() == (int)mChilds[1].calculate();
//		default:
//			return null;
//		}
	}

	@Override
	public void setVariableParameters(Object[] _contextElements) {
		if(mChilds != null) {
			mChilds[0].setVariableParameters(_contextElements);
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
		return TreeNodeComparator.class;
	}

}
