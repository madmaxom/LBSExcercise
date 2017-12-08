package at.fhooe.mcm.cas.compiler.treenode;

import java.util.ArrayList;
import java.util.List;

import at.fhooe.mcm.cas.contexttype.ContextElement;

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
	public void setVariableParameters(List<ContextElement> _contextElements) {
		if(mChilds != null) {
			int fromIndex = 0;
			int leafIndex = 0;
			for (TreeNode t : mChilds) {
				TreeNode[] c = t.getChilds();
				List<ContextElement> childParams = new ArrayList<>();
				if (c != null) {
					
					int toIndex = fromIndex + c.length;
					/*
					for (int index = fromIndex; fromIndex < toIndex; index++) {
						childParams.add(_contextElements.get(index));
					}
					*/
					childParams.addAll(_contextElements.subList(fromIndex, toIndex));
					
					t.setVariableParameters(childParams);
					fromIndex = toIndex;
				} else {
					// leave node
					childParams.addAll(_contextElements.subList(leafIndex, leafIndex + 1));
					t.setVariableParameters(childParams);
					leafIndex++;
				}
			}
		}
	}

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
	public List<String> getContextElements() {
		List<String> l = new ArrayList<String>();
		for (TreeNode t : mChilds) {
			l.addAll(t.getContextElements());
		}
		return l;
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