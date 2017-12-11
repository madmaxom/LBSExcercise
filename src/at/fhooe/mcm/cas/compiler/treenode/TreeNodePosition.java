package at.fhooe.mcm.cas.compiler.treenode;

import org.postgis.Point;
import java.util.ArrayList;
import java.util.List;

import at.fhooe.mcm.cas.contexttype.ContextElement;
import at.fhooe.mcm.cas.contexttype.ContextPosition;



public class TreeNodePosition extends TreeNode {

	String mType;
	Point mPoint;
	
	public TreeNodePosition(String text) {
		mType = text;
	}

	@Override
	public Object calculate() throws NodeError {
		if (mPoint == null) {
			throw new NodeError("no point delivered");
		}
		return mPoint;
	}

	@Override
	public void setVariableParameters(List<ContextElement> _contextElements) {
		// leave node
		if (_contextElements != null && _contextElements.size() == 1) {
			ContextElement c = _contextElements.get(0);
			if (c instanceof ContextPosition) {
				ContextPosition cP = ((ContextPosition) c);
				mPoint = new Point(cP.getxValue(), cP.getyValue());
			} else {
				System.out.println(this.getClass().getSimpleName() + ": unknown contextElement type received");
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
