package at.fhooe.mcm.cas.compiler.treenode;


import java.util.ArrayList;
import java.util.List;

import org.postgis.Point;


public class TreeNodePoint extends TreeNode {

	Point mPoint;
	
	public TreeNodePoint(String x, String y) {
		mPoint = new Point(Double.parseDouble(x), Double.parseDouble(y));
	}

	@Override
	public Object calculate() throws NodeError {
		return mPoint;
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
	public List<String> getContextElements() {
		List<String> l = new ArrayList<String>();
		// add placeholder
		l.add(null);
		return l;
	}
}
