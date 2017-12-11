package at.fhooe.mcm.cas.compiler.treenode;


import org.postgis.Point;


public class TreeNodeDistance extends TreeNode {

	
	public TreeNodeDistance() {
	}

	@Override
	public Object calculate() throws NodeError {
		Point p1 = (Point) mChilds[0].calculate();
		Point p2 = (Point) mChilds[1].calculate();
		double distance = distFrom(p1.x, p1.y, p2.x, p2.y);
		return (int)distance;
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

	
	public static double distFrom(double x, double y, double x2, double y2) {
	    double earthRadius = 6371000; //meters
	    double dLat = Math.toRadians(x2-x);
	    double dLng = Math.toRadians(y2-y);
	    double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
	               Math.cos(Math.toRadians(x)) * Math.cos(Math.toRadians(x2)) *
	               Math.sin(dLng/2) * Math.sin(dLng/2);
	    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
	    double dist = (double) (earthRadius * c);

	    return dist;
	 }
}
