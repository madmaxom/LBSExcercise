package at.fhooe.mcm.cas.compiler.treenode;

public class NodeError extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NodeError() {
		this("");
	}

	public NodeError(String message) {
		super(message);
	}

}
