package at.fhooe.mcm.cas.rule.container;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import at.fhooe.mcm.cas.ContextSituation;
import at.fhooe.mcm.cas.compiler.treenode.NodeError;
import at.fhooe.mcm.cas.compiler.treenode.TreeNode;
import at.fhooe.mcm.cas.contexttype.ContextElement;
import at.fhooe.mcm.cas.contexttype.ContextTemperature;
import at.fhooe.mcm.cas.compiler.generated.*;
import at.fhooe.mcm.cas.compiler.generated.Compiler;

public class RuleContainer {
	private TreeNode mConditionRoot = null;
	private Action mAction = null;
	
	public RuleContainer(String _condition, Action _action) {
		InputStream input = new ByteArrayInputStream(_condition.getBytes());
		Compiler compiler = new Compiler(input);
		try {
			mConditionRoot = compiler.stmt();
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		mAction = _action;
	}
	
	public boolean valid(ContextSituation _sit) {
		if(_sit == null) {
			return false;
		}
		if(mConditionRoot != null) {
			for(ContextElement ce : _sit.mContextElements) {
				if(ce instanceof ContextTemperature) {
					mConditionRoot.setVariableParameters(
						new Object[] { ((ContextTemperature)ce).getTemperature()});
					System.out.println(((ContextTemperature)ce).getTemperature());
					try {
						System.out.println(mConditionRoot.calculate().toString());
					} catch (NodeError e) {
						e.printStackTrace();
					}
				}
			}
		}
		return true;
	}
	
	public void execute() {
		
	}
}