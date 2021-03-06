package at.fhooe.mcm.cas.compiler.treenode;

import java.util.ArrayList;
import java.util.List;

import at.fhooe.mcm.cas.contexttype.ContextElement;
import at.fhooe.mcm.cas.contexttype.ContextElementType;

public class TreeNodeContextVar extends TreeNode {

	int mContextVar = Integer.MAX_VALUE;
	
	public TreeNodeContextVar(String text) {
		mContextVar = Integer.parseInt(text);
	}
	
	@Override
	public Object calculate() throws NodeError {
		if (mContextVar == Integer.MAX_VALUE) {
			throw new NodeError("No value for evaluation.");
		} else {
			return mContextVar;
		}
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
	public List<String> getContextElements() {
		List<String> l = new ArrayList<String>();
		// add placeholder
		l.add(null);
		return l;
	}
}
