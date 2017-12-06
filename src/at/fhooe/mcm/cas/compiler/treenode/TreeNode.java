package at.fhooe.mcm.cas.compiler.treenode;

public abstract class TreeNode {
	protected TreeNode[] mChilds = null;
	protected TreeNode mRoot = null;

	/**
	 * The method calculate triggers the calculation of the dedicated node, she
	 * returns a responding result object
	 *
	 * @return The calculation result in form of an object
	 * @throws NodeError
	 */
	public abstract Object calculate() throws NodeError;

	/**
	 * Dynamic (variable) leaf nodes get their needed values through this method.
	 * Father nodes call the function of their child nodes
	 *
	 * @param _contextElements
	 *            the currently known and needed context element values
	 * @see ContextObject
	 */
	public abstract void setVariableParameters(Object[] _contextElements);

	/**
	 * Clears all temporary data (context values inside variale tree nodes)
	 */
	public abstract void clear();

	/**
	 * Needs to be overwritten. Delivers which context element types are needed in
	 * this part of the tree.
	 * 
	 * @return an int[] with the context element types considered in this part of
	 *         the tree.
	 */
	public int[] getContextElements() {
		return new int[0];
	}

	/**
	 * Delivers the root of the processing tree
	 * 
	 * @return the root of the tree
	 */
	public TreeNode getRoot() {
		return mRoot;
	}

	/**
	 * Sets/defines the root node of the processing tree
	 * 
	 * @return the root of the tree
	 */
	public void setRoot(TreeNode _root) {
		mRoot = _root;
	}

	/**
	 * Sets the child nodes of this distinct node
	 * 
	 * @param _childs
	 *            sets the child nodes of this node
	 */
	public void setChilds(TreeNode[] _childs) {
		mChilds = _childs;
	}

	/**
	 * Delivers the child nodes of this node
	 * 
	 * @return Delivers the child nodes of this node in form of an node array
	 */
	public TreeNode[] getChilds() {
		return mChilds;
	}
	
	public abstract String toString();
} // class