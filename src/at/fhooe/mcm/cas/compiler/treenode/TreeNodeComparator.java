package at.fhooe.mcm.cas.compiler.treenode;


public class TreeNodeComparator extends TreeNode {
	
	String mComparator;
	
	public TreeNodeComparator(String text) {
		mComparator = text;
	}

	@Override
	public Object calculate() throws NodeError {
		
		if (mComparator == null || mComparator.isEmpty()) {
			throw new NodeError("No value for evaluation.");
		} else {
			int value1 = Integer.MAX_VALUE;
			int value2 = Integer.MAX_VALUE;
			
			
			value1 = (int)mChilds[0].calculate();
			value2 = (int)mChilds[1].calculate();
			
			if (value1 == Integer.MAX_VALUE || value2 == Integer.MAX_VALUE) {
				// default values, error
				throw new NodeError("Got no values for evaluation.");
			}
			
			switch(mComparator) {
			case "<":
				return value1 < value2;
			case ">":
				return value1 > value2;
			case "=":
				return value1 == value2;
			default:
				throw new NodeError("Unknown comparator.");
			}
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

}
